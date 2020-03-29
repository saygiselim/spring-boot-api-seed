package com.saygiselim.springboot.seed.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.saygiselim.springboot.seed.ApplicationProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final JWTVerifier jwtVerifier;

    public JWTAuthorizationFilter(ApplicationProperties applicationProperties, AuthenticationManager authManager) {
        super(authManager);

        jwtVerifier = JWT.require(Algorithm.HMAC512(applicationProperties.getJwtSecret().getBytes())).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(req));
        }

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String tokenWithPrefix = request.getHeader(SecurityConstants.HEADER_STRING);

        if (tokenWithPrefix != null) {
            String token = tokenWithPrefix.replace(SecurityConstants.TOKEN_PREFIX, "");
            String user = jwtVerifier.verify(token).getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }
}