package com.example.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.configuration.Mapper;
import com.example.demo.data.AwardData;
import com.example.demo.dto.AwardRequest;
import com.example.demo.model.Award;
import com.example.demo.repository.AwardRepository;
import java.time.LocalDate;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AwardServiceTest {

    private AwardService awardService;
    @Mock private AwardRepository awardRepository;
    @Spy Mapper mapper;

    @BeforeEach
    void init() {
        awardService = new AwardService(mapper, awardRepository);
    }

    @Test
    @DisplayName("Test Success")
    public void testAward_Success() {
        // Given
        AwardRequest request = AwardData.awardRequest().build();
        // When
        Award details =
                Award.builder()
                        .id(100)
                        .description(request.getDescription())
                        .recipient(request.getRecipient())
                        .awardDate(LocalDate.parse(request.getAwardDate()))
                        .title(request.getTitle())
                        .build();
        when(awardRepository.save(any())).thenReturn(details);
        awardService.saveAward(request);
        Award expected =
                Award.builder()
                        .id(100)
                        .description(request.getDescription())
                        .recipient(request.getRecipient())
                        .awardDate(LocalDate.parse(request.getAwardDate()))
                        .title(request.getTitle())
                        .build();
        ArgumentCaptor<Award> awardArgumentCaptor = ArgumentCaptor.forClass(Award.class);
        verify(awardRepository).save(awardArgumentCaptor.capture());
        assertThat(
                awardArgumentCaptor.getValue(),
                allOf(
                        HasPropertyWithValue.hasProperty("title", equalTo(expected.getTitle())),
                        HasPropertyWithValue.hasProperty(
                                "description", equalTo(expected.getDescription())),
                        HasPropertyWithValue.hasProperty(
                                "recipient", equalTo(expected.getRecipient())),
                        HasPropertyWithValue.hasProperty(
                                "awardDate", equalTo(expected.getAwardDate()))));
    }

    @Test
    @DisplayName("Test create Award Failure")
    public void testCreate_Failure() {
        AwardRequest request = AwardData.awardRequest().withTitle(null).build();
        assertThrows(
                NullPointerException.class,
                () -> {
                    // When
                    awardService.saveAward(request);
                });
    }
}
