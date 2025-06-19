package com.sdgs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 配置授权请求
                .authorizeHttpRequests(auth -> auth
                        // 允许对 /test/ 下的所有请求直接放行，无需认证
                        .requestMatchers("/test/**").permitAll()
                        // 未来你还可以添加其他需要放行的公共路径
                        // .requestMatchers("/news/**", "/departments/**").permitAll()

                        // 除了上面明确放行的路径外，其他所有请求都需要身份认证
                        .anyRequest().authenticated()
                )
                // 2. 关闭 CSRF 防护（因为我们使用 Token，而非 Cookie-Session 机制）
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
