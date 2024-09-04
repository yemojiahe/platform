package com.example.userlogin.service;

import com.example.userlogin.Filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityConfig() {
        System.out.println("SecurityConfig is being loaded");
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(); // 自定义 JWT 过滤器
    }

    //warning显示0个用法，但有了bean不会错
    //不使用session，bimian
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Configuring security filter chain");
        http
                .csrf(AbstractHttpConfigurer::disable) // 不使用token，无须csrf保护，禁用 CSRF 保护
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll() // 允许访问 /login路径下的所有请求，无需认证
                        .requestMatchers("/create").permitAll() // 允许访问 /create路径下的所有请求，无需认证
                        .anyRequest().authenticated() // 其他所有请求必须认证
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // 添加自定义 JWT 过滤器
        return http.build(); // 构建 SecurityFilterChain
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**"); // 忽略 Swagger UI 和 API 文档的请求
    }
}

