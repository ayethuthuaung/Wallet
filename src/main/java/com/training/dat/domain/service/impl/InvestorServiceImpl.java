package com.training.dat.domain.service.impl;

import com.training.dat.domain.dto.InvestorDto;
import com.training.dat.domain.model.Investor;
import com.training.dat.domain.repository.InvestorRepository;
import com.training.dat.domain.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class InvestorServiceImpl implements InvestorService {

    @Autowired
    private InvestorRepository investorRepository;

    @Override
    public Investor registerInvestor(InvestorDto investorDto) {
        // Convert InvestorDto to Investor entity
        Investor investor = new Investor();
        investor.setInvestorAccountId(UUID.randomUUID().toString());
        investor.setInvestorAccountName(investorDto.getInvestorAccountName());
        investor.setCreatedDate(LocalDate.now());
        investor.setEmail(investorDto.getEmail());
        investor.setDateOfBirth(LocalDate.parse(investorDto.getDateOfBirth()));

        // Save and return the newly created investor
        return investorRepository.save(investor);
    }
}
