package com.frog.security.config;

import com.frog.common.core.enums.ImageType;
import com.frog.security.enums.CaptchaType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author lh
 * 验证码配置
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class CaptchaProperties {

    /**
     * 过期时间
     */
    @Value("${validate.code.expire.time:60}")
    private Long expireTime;

    /**
     * 图片类型
     */
    private ImageType imageType = ImageType.PNG;

    /**
     * 图片宽度:px
     */
    @Value("${validate.code.width:130}")
    private Integer width;

    /**
     * 图片高度:px
     */
    @Value("${validate.code.height:48}")
    private Integer height;

    /**
     * 验证码长度
     */
    @Value("${validate.code.length:4}")
    private Integer length;

    /**
     * 验证码类型
     */
    private CaptchaType type = CaptchaType.CHARACTER;

}
