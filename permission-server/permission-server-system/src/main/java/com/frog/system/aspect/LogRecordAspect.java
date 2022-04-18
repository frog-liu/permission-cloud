package com.frog.system.aspect;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.frog.common.core.enums.StatusEnum;
import com.frog.system.service.IdService;
import com.frog.system.annotation.LogRecord;
import com.frog.system.domain.system.OperationLog;
import com.frog.system.service.IOperationLogService;
import com.frog.system.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author lh
 */
@Slf4j
@Aspect
@Component
public class LogRecordAspect {

    @Autowired
    private IdService idService;

    @Autowired
    private IOperationLogService operationLogService;

    @AfterReturning(pointcut = "@annotation(logRecord)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, LogRecord logRecord, Object result) {
        handleLogRecord(joinPoint, logRecord, null, result);
    }

    @AfterThrowing(value = "@annotation(logRecord)", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, LogRecord logRecord, Exception exception) {
        handleLogRecord(joinPoint, logRecord, exception, null);
    }

    protected void handleLogRecord(final JoinPoint joinPoint, LogRecord logRecord, final Exception exception, Object result) {
        try {
            Long requestId = idService.nextId();
            String ip = ServletUtils.getRealIp();
            StatusEnum statusEnum = ObjectUtils.isNotNull(exception) ? StatusEnum.INVALID : StatusEnum.VALID;
            OperationLog operationLog = OperationLog.builder()
                    .description(logRecord.description())
                    .requestId(requestId)
                    .ip(ip)
                    .status(statusEnum.getCode())
                    .build();
            if (StatusEnum.VALID.equals(statusEnum)) {
                log.info("requestId → {}, params → {}, result → {}", requestId, jsonArgs(joinPoint), JSONObject.toJSONString(result));
            } else {
                log.error("requestId → {}, params → {}, exception → {}", requestId, jsonArgs(joinPoint), exception.getMessage());
            }
            operationLogService.add(operationLog);
        } catch (Exception e) {
            log.error("记录操作日志异常: {}", e.getMessage());
        }
    }

    /**
     * 参数拼装
     */
    private String jsonArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            List<Object> argList = new ArrayList<>(args.length);
            for (Object arg : args) {
                if (!isFilterObject(arg)) {
                    argList.add(arg);
                }
            }
            return JSONObject.toJSONString(argList);
        }
        return StringPool.EMPTY;
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        if (ObjectUtils.isNull(o)) {
            return true;
        }
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
