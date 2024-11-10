package com.training.dat.Wallet.controllers;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.service.InvestorAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investorAccounts")
public class InvestorAccountController {
    private final InvestorAccountService investorAccountService;

    @Autowired
    public InvestorAccountController(InvestorAccountService investorAccountService) {
        this.investorAccountService = investorAccountService;
    }

    @Operation(summary = "Create a new investor account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Investor account created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    })
    @PostMapping
    public ResponseEntity<InvestorAccountDto> createInvestorAccount(@Validated @RequestBody InvestorAccountCreateDto dto) {
        InvestorAccountDto responseDto = investorAccountService.createAccount(dto);
        return ResponseEntity.ok(responseDto);
    }
}
