package com.saygiselim.springboot.seed.security;

import com.saygiselim.springboot.seed.app.member.MemberService;
import com.saygiselim.springboot.seed.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
final class JWTUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Autowired
    public JWTUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            var member = memberService.getMember(username);
            return new User(member.getEmail(), member.getPassword(), emptyList());
        } catch (ResourceNotFoundException ex) {
            throw new UsernameNotFoundException(username);
        }
    }
}
