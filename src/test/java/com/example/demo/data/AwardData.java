package com.example.demo.data;

import com.example.demo.dto.AwardRequest;

public class AwardData {
    private int id = 1;
    private String title = "Award Ceremony";

    private String description = "Mathew's Award Ceremony";
    private String awardDate = "2023-06-12";
    private String recipient = "Mathew";

    public AwardData() {}

    public static AwardData awardRequest() {
        return new AwardData();
    }

    public AwardData withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public AwardData withAwardDate(String awardDate) {
        this.awardDate = awardDate;
        return this;
    }

    public AwardData withTitle(String title) {
        this.title = title;
        return this;
    }

    public AwardRequest build() {
        return AwardRequest.builder()
                .title(title)
                .description(description)
                .awardDate(awardDate)
                .recipient(recipient)
                .build();
    }
}
