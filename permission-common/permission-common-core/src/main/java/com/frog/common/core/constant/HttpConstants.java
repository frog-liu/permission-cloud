package com.frog.common.core.constant;

/**
 * @author liuhuan
 * HttpConstants: http相关常量类
 */
public class HttpConstants {

    public interface HeaderIp {
        /**
         * squid代理服务ip格式：client1,proxy1,proxy2
         */
        String X_FORWARDED_FOR = "X-Forwarded-For";

        /**
         * ApacheHttp代理服务器
         */
        String PROXY_CLIENT_IP = "Proxy-Client-IP";

        /**
         * ApacheHttp代理服务器WebLogic插件
         */
        String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

        /**
         * 部分代理服务器ip
         */
        String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

        /**
         * HTTP代理或者负载均衡客户端ip
         */
        String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

        /**
         * nginx代理ip
         */
        String X_REAL_IP = "X-Real-IP";
    }

}
