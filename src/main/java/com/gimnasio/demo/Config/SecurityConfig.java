package com.gimnasio.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // HABILITA LA CONFIGURACIÓN DE SEGURIDAD DE MANERA PERSONALIZADA
@EnableMethodSecurity

public class SecurityConfig {
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Seleccion de encriptador
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers("/admin/records/top10").permitAll()//esto deberia permitir ver los records a cualquiera
                        .requestMatchers("/usuario/**").hasAnyAuthority("USER", "CLIENT")
                        .requestMatchers("/clients/crearCliente").hasAnyAuthority("USER", "CLIENT")
                        .requestMatchers("/admin/rutina", "/admin/rutina/buscar/**").hasAnyAuthority("CLIENT", "ROLE_ADMIN")
                        .requestMatchers("/admin/**", "/clients/**").hasRole("ADMIN")
                        .requestMatchers("usuario/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());//estaba puesto ".httpBasic()" y tiraba error, sino funciona, cambiarlo
        return http.build();
    }
}
