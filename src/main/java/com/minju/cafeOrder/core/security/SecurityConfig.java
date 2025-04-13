package com.minju.cafeOrder.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minju.cafeOrder.core.api.ApiResponse;

import com.minju.cafeOrder.core.api.ResponseService;
import com.minju.cafeOrder.core.jwt.CustomUserDetailsService;
import com.minju.cafeOrder.core.jwt.JwtAuthFilter;
import com.minju.cafeOrder.core.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityConfig {


//    JwtAuthFilter jwtAuthFilter;

    @Autowired
    ResponseService responseService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    JwtProvider jwtProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(configurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/order/**", "/api/user/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling.authenticationEntryPoint((request, response, authException) -> {
                    ApiResponse<String> apiResponse = (ApiResponse<String>) responseService.getUnauthorizedResult("인증되지 않았습니다.");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType("application/json; charset=utf-8");
                    ObjectMapper om = new ObjectMapper();
                    response.getWriter().println(om.writeValueAsString(apiResponse));
                }))
                .addFilterBefore(jwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS 설정
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration(); // cors 설정 객체 생성
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.addAllowedMethod("*"); // 모든 http 메소드 허용
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowCredentials(true); // 쿠키 허용
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 cors 설정
        return source;
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtProvider jwtProvider) {
        return new JwtAuthFilter(jwtProvider);
    }
}


