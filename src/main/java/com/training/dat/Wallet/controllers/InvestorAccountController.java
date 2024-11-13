package com.training.dat.Wallet.controllers;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountListDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountUpdateDto;
import com.training.dat.Wallet.domain.exception.ErrorResponse;
import com.training.dat.Wallet.domain.service.InvestorAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class InvestorAccountController {
    private final InvestorAccountService investorAccountService;

    public InvestorAccountController(InvestorAccountService investorAccountService) {
        this.investorAccountService = investorAccountService;
    }
    
    @GetMapping("/investor-account/{id}")
    public ResponseEntity<InvestorAccountDto> getAccountById(@PathVariable Long id) {
        InvestorAccountDto account = investorAccountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
    
    @GetMapping("/investor-account-list")
    public ResponseEntity<List<InvestorAccountListDto>> getInvestorAccounts(
            @RequestParam(required = false) String investorAccountId,
            @RequestParam(required = false) String investorAccountName,
            @RequestParam(required = false) Integer accountType) {

        List<InvestorAccountListDto> accounts = investorAccountService.getAccounts(investorAccountId, investorAccountName, accountType);
        return ResponseEntity.ok(accounts);
    }
    
    
    @PostMapping("/investor-account")
    public ResponseEntity<InvestorAccountDto> createInvestorAccount(@Validated @RequestBody InvestorAccountCreateDto dto) {
        InvestorAccountDto responseDto = investorAccountService.createAccount(dto);
        return ResponseEntity.ok(responseDto);
    }
    
    @PutMapping("/investor-account/{investorAccountId}")
    public ResponseEntity<InvestorAccountDto> updateInvestorAccount(
            @PathVariable String investorAccountId,
            @Validated @RequestBody InvestorAccountUpdateDto dto) {
        
        InvestorAccountDto responseDto = investorAccountService.updateAccount(investorAccountId, dto);
        return ResponseEntity.ok(responseDto);
    }
    
    @DeleteMapping("/investor-account/{investorAccountId}")
    public ResponseEntity<ErrorResponse> deleteInvestorAccount(@PathVariable String investorAccountId) {
        investorAccountService.deleteAccountById(Long.parseLong(investorAccountId));
        
        return ResponseEntity.ok(new ErrorResponse("I835666", "Account Information has been deleted."));
    }
    
    
    
}
