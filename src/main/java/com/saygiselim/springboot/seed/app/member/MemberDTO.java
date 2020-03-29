package com.saygiselim.springboot.seed.app.member;

import javax.validation.constraints.NotBlank;

public class MemberDTO {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}