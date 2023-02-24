package com.example.demo.controller;

import com.example.demo.dto.AwardRequest;
import com.example.demo.dto.Response;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Award;
import com.example.demo.service.AwardService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class AwardController {
    @Autowired private AwardService awardService;

    @PostMapping("/awards")
    public ResponseEntity<Response> saveAwardDetails(
            @Valid @RequestBody AwardRequest awardRequest) {
        try {
            Award award = awardService.saveAward(awardRequest);
            return new ResponseEntity<>(
                    Response.builder()
                            .success(award.getId())
                            .message(HttpStatus.CREATED.name())
                            .build(),
                    HttpStatus.CREATED);
        } catch (CustomException e) {
            return new ResponseEntity<>(
                    Response.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
