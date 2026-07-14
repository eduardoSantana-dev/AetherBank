package com.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired
    lateinit var SecurityFiltro: SecurityFiltro
    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize -> authorize
                .requestMatchers("/usuarios/auth/**").permitAll()
                .anyRequest().authenticated()
            }
            .addFilterBefore(SecurityFiltro, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    }
        @Bean
        fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
            return authConfig.getAuthenticationManager()
        }
        @Bean
        fun passwordEncoder(): PasswordEncoder {
            return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
        }
}