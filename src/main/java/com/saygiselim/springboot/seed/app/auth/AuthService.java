package com.saygiselim.springboot.seed.app.auth;

import com.saygiselim.springboot.seed.app.member.Member;
import com.saygiselim.springboot.seed.app.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberService memberService;

    @Autowired
    public AuthService(MemberService memberService) {this.memberService = memberService;}

    public void signUp(SignUpDTO signUpInfoDTO) {
        Member member = new Member();
        member.setName(signUpInfoDTO.getName());
        member.setEmail(signUpInfoDTO.getEmail());
        member.setPassword(signUpInfoDTO.getPassword());

        memberService.createMember(signUpInfoDTO.toMember());
    }
}
