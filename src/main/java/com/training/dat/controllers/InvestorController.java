package com.training.dat.controllers;

import com.training.dat.domain.dto.InvestorDto;
import com.training.dat.domain.model.Investor;
import com.training.dat.domain.repository.InvestorRepository;
import com.training.dat.domain.services.InvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

	@Autowired
    private InvestorService investorService;

    @PostMapping("/register")
    public ResponseEntity<String> registerInvestor(@RequestBody InvestorDto investorDto) {

        Investor investor = investorService.registerInvestor(investorDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Investor registered successfully with ID: " + investor.getInvestorAccountId());
    }
}
