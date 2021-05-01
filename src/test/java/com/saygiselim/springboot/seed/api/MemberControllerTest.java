package com.saygiselim.springboot.seed.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saygiselim.springboot.seed.app.member.Member;
import com.saygiselim.springboot.seed.app.member.MemberDTO;
import com.saygiselim.springboot.seed.app.member.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    public void Should_ReturnNoContent_When_UpdateMember() throws Exception {
        int id = 1;
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("TestUser");

        when(memberService.getMember(id)).thenReturn(new Member());

        mockMvc
                .perform(
                        put("/members/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberDTO))
                )
                .andExpect(status().isNoContent());
    }
}