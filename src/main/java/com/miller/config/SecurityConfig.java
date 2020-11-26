package com.miller.config;

import com.miller.security.jwt.JwtAuthFilter;
import com.miller.security.jwt.JwtService;
import com.miller.service.implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImplementation userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userServiceImplementation);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clients/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/products/**")
                        .hasRole("ADMIN")
                    .antMatchers("/api/solicitations/**")
                        .hasAnyRole("USER", "ADMIN")
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}