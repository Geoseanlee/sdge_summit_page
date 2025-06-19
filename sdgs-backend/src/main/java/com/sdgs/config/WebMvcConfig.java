package com.sdgs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 对所有路径都进行跨域处理
        registry.addMapping("/**")
                // 允许任何来源，但在生产环境中应指定具体域名
                .allowedOriginPatterns("*")
                // 允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许携带凭证（如 cookie）
                .allowCredentials(true)
                // 预检请求的有效期
                .maxAge(3600)
                // 允许所有请求头
                .allowedHeaders("*");
    }
}
