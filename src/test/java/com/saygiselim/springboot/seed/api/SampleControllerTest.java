package com.saygiselim.springboot.seed.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saygiselim.springboot.seed.app.sample.SampleDTO;
import com.saygiselim.springboot.seed.app.sample.SampleService;
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
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles("test")
public class SampleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SampleService sampleService;

    @Test
    public void Should_Return_Created_When_Post() throws Exception {
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setTitle("Sample Title");
        sampleDTO.setContent("Sample Content");

        RequestBuilder requestBuilder = post("/samples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO));

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    public void Should_Return_No_Content_When_Put() throws Exception {
        int id = 1;
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setId(id);
        sampleDTO.setTitle("Sample Title");
        sampleDTO.setContent("Sample Content");

        RequestBuilder requestBuilder = put("/samples/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO));

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

    @Test
    public void Should_Return_Samples_When_Get() throws Exception {
        when(sampleService.getSampleDTOs()).thenReturn(Arrays.asList(new SampleDTO(), new SampleDTO()));

        RequestBuilder requestBuilder = get("/samples").contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void Should_Return_Sample_When_Get() throws Exception {
        int id = 1;
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setId(id);

        when(sampleService.getSampleDTO(eq(id))).thenReturn(sampleDTO);

        RequestBuilder requestBuilder = get("/samples/{id}", id).contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)));
    }

    @Test
    public void Should_Return_No_Content_When_Delete() throws Exception {
        int id = 1;

        RequestBuilder requestBuilder = delete("/samples/{id}", id).contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}