package com.saygiselim.springboot.seed.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saygiselim.springboot.seed.ApplicationProperties;
import com.saygiselim.springboot.seed.app.auth.SignInDTO;
import com.saygiselim.springboot.seed.exception.InternalServerErrorException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ApplicationProperties applicationProperties;
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(ApplicationProperties applicationProperties, AuthenticationManager authenticationManager) {
        this.applicationProperties = applicationProperties;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            SignInDTO signInDTO = new ObjectMapper().readValue(req.getInputStream(), SignInDTO.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword(), Collections.emptyList());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new InternalServerErrorException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(Date.from(Instant.now().plus(SecurityConstants.EXPIRATION_TIME)))
                .sign(Algorithm.HMAC512(applicationProperties.getJwtSecret().getBytes()));
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }
}
