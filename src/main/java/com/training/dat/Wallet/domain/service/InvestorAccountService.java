package com.training.dat.Wallet.domain.service;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;

public interface InvestorAccountService {
    InvestorAccountDto createAccount(InvestorAccountCreateDto dto);
}
