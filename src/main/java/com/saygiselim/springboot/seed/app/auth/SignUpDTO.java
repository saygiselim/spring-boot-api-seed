package com.saygiselim.springboot.seed.app.auth;

import com.saygiselim.springboot.seed.app.member.Member;

import javax.validation.constraints.NotBlank;

public final class SignUpDTO extends SignInDTO {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member toMember() {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);

        return member;
    }
}
