package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = AwardRequest.AwardRequestBuilder.class)
public class AwardRequest {
    @JsonPOJOBuilder(withPrefix = "")
    public static class AwardRequestBuilder {}

    @NotEmpty(message = "Title cannot be empty")
    @ApiModelProperty(notes = "Award title is mandatory", required = true)
    @Column(name = "title")
    private final String title;

    @ApiModelProperty(notes = "Description can be added")
    private final String description;

    @ApiModelProperty(
            notes =
                    "Award Date is mandatory,valid and pattern should be yyyy-MM-dd (eg. 2000-12-01)",
            required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Award Date should not be empty")
    private final String awardDate;

    @NotEmpty(message = "Recipient cannot be empty")
    @ApiModelProperty(notes = "Recipient is mandatory", required = true)
    @Column(name = "recipient")
    private final String recipient;
}
