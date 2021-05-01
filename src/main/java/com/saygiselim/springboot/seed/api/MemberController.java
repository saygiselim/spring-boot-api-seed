package com.saygiselim.springboot.seed.api;

import com.saygiselim.springboot.seed.app.member.MemberDTO;
import com.saygiselim.springboot.seed.app.member.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@Api("Member")
final class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService authService) { this.memberService = authService; }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates a member by given id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMember(@PathVariable int id, @Valid @RequestBody MemberDTO memberDTO) {
        memberService.updateMember(id, memberDTO);
    }
}