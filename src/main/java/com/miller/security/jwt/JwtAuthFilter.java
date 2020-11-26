package com.miller.security.jwt;

import com.miller.service.implementation.UserServiceImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserServiceImplementation userServiceImplementation;

    public JwtAuthFilter(JwtService jwtService, UserServiceImplementation userServiceImplementation) {
        this.jwtService = jwtService;
        this.userServiceImplementation = userServiceImplementation;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.isTokenValid(token);

            if(isValid) {
                String userName = jwtService.getUserName(token);

                UserDetails userDetails = userServiceImplementation.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                user.setDetails(
                        new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
