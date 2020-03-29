package com.saygiselim.springboot.seed.api;

import com.saygiselim.springboot.seed.app.auth.AuthService;
import com.saygiselim.springboot.seed.app.auth.SignUpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
@Api("Auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-up")
    @ApiOperation(value = "Signs up a new member")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody SignUpDTO signUpInfoDTO) {
        authService.signUp(signUpInfoDTO);
    }
}