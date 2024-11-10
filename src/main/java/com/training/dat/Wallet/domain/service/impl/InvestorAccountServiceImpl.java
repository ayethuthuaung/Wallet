package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.model.InvestorAccount;
import com.training.dat.Wallet.domain.repository.InvestorAccountRepository;
import com.training.dat.Wallet.domain.service.InvestorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class InvestorAccountServiceImpl implements InvestorAccountService {

    private final InvestorAccountRepository investorAccountRepository;

    @Autowired
    public InvestorAccountServiceImpl(InvestorAccountRepository investorAccountRepository) {
        this.investorAccountRepository = investorAccountRepository;
    }

    @Override
    public InvestorAccountDto createAccount(InvestorAccountCreateDto dto) {
        InvestorAccount account = new InvestorAccount();
        account.setInvestorAccountName(dto.getInvestorAccountName());
        account.setAccountType(Long.parseLong(dto.getAccountType()));
        account.setEmail(dto.getEmail());
        account.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));

        InvestorAccount savedAccount = investorAccountRepository.save(account);
        return mapToResponseDto(savedAccount);
    }
    private InvestorAccountDto mapToResponseDto(InvestorAccount account) {
        InvestorAccountDto responseDto = new InvestorAccountDto();
        responseDto.setInvestorAccountId(String.valueOf(account.getInvestorAccountId()));
        responseDto.setInvestorAccountName(account.getInvestorAccountName());
        responseDto.setAccountTypeName(account.getAccountType() == 1 ? "Self" : "Customer");//
        responseDto.setCreatedDate(account.getCreatedDate().toString());
        responseDto.setEmail(account.getEmail());
        responseDto.setDateOfBirth(account.getDateOfBirth().toString());
        return responseDto;
    }
}
