package com.example.demo.service;

import com.example.demo.configuration.Mapper;
import com.example.demo.dto.AwardRequest;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Award;
import com.example.demo.repository.AwardRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class AwardService {
    private final Mapper mapper;

    private final AwardRepository awardRepository;

    @Autowired
    public AwardService(Mapper mapper, AwardRepository awardRepository) {
        this.mapper = mapper;
        this.awardRepository = awardRepository;
    }

    @SneakyThrows
    @Transactional
    public Award saveAward(AwardRequest awardRequest) {
        LocalDate dobRequest =
                LocalDate.parse(
                        awardRequest.getAwardDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (dobRequest.isBefore(LocalDate.now())) {
            throw new CustomException("Award Date cannot be a Past date.");
        }
        Award award = mapper.setModelMapper().map(awardRequest, Award.class);
        award = awardRepository.save(award);
        log.info("Award Details are saved in DB successfully with id: {}", award.getId());
        return award;
    }
}
