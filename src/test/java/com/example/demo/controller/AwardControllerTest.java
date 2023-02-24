package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.data.AwardData;
import com.example.demo.dto.AwardRequest;
import com.example.demo.repository.AwardRepository;
import com.example.demo.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AwardControllerTest {
    private MockMvc mockMvc;

    @Autowired private WebApplicationContext wac;

    @Autowired private AwardRepository awardRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @SneakyThrows
    @Test
    @DisplayName("POST /save award Details")
    public void testAward_Success() {

        // Given
        AwardRequest request = AwardData.awardRequest().build();

        // When
        mockMvc.perform(
                        post("/api/awards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.jsonToString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    @DisplayName("POST /Title is null")
    void testTitle_Null_Failure() {

        // Given
        AwardRequest request = AwardData.awardRequest().withTitle(null).build();

        // When
        mockMvc.perform(
                        post("/api/awards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.jsonToString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    @DisplayName("POST /Recipient is null")
    void testRecipient_Null_Failure() {

        // Given
        AwardRequest request = AwardData.awardRequest().withRecipient(null).build();

        // When
        mockMvc.perform(
                        post("/api/awards")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.jsonToString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }
}
