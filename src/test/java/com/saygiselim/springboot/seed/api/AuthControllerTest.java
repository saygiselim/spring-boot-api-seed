package com.saygiselim.springboot.seed.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saygiselim.springboot.seed.app.auth.SignUpDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Should_Return_Created_When_Sign_Up() throws Exception {
        SignUpDTO signUpInfoDTO = new SignUpDTO();
        signUpInfoDTO.setName("TestUser");
        signUpInfoDTO.setEmail("test@email.com");
        signUpInfoDTO.setPassword("09@AZaz8");

        mockMvc
                .perform(
                        post("/api/auth/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signUpInfoDTO))
                )
                .andExpect(status().isCreated());
    }
}