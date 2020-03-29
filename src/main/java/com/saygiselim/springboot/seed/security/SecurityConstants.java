package com.saygiselim.springboot.seed.security;

import java.time.Duration;

public final class SecurityConstants {
    public static final Duration EXPIRATION_TIME = Duration.ofDays(1);
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/api/auth/sign-up";
    public static final String SIGN_IN_URL = "/api/auth/sign-in";

    private SecurityConstants() {}
}
