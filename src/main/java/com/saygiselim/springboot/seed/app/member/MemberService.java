package com.saygiselim.springboot.seed.app.member;

import com.saygiselim.springboot.seed.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createMember(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public Member getMember(int id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find any member with id: " + id));
    }

    public Member getMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Could not find any member with email: " + email));
    }

    public void updateMember(int id, MemberDTO memberDTO) {
        var member = getMember(id);
        member.setName(memberDTO.getName());

        memberRepository.save(member);
    }
}