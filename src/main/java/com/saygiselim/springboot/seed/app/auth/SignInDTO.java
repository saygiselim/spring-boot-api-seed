package com.saygiselim.springboot.seed.app.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class SignInDTO {
    @Email
    protected String email;

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*](?=\\S+$).{7,15}$",
            message = "must contain at least 8 characters, 1 number, 1 upper case letter, 1 lower case letter, 1 special character and no space"
    )
    protected String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
