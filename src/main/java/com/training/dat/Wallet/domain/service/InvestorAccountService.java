package com.training.dat.Wallet.domain.service;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountListDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountUpdateDto;

import java.util.Optional;
import java.util.List;

public interface InvestorAccountService {
	InvestorAccountDto getAccountById(Long id);    
	InvestorAccountDto createAccount(InvestorAccountCreateDto dto);
    //List<InvestorAccountListDto> getAccounts(String investorAccountId, String investorAccountName, Integer accountType);
	//InvestorAccountDto updateAccount(Long id, InvestorAccountCreateDto dto);
	InvestorAccountDto updateAccount(String investorAccountId, InvestorAccountUpdateDto dto);
	List<InvestorAccountListDto> getAccounts(String investorAccountId, String investorAccountName, Integer accountType);
	void deleteAccountById(Long investorAccountId);
}
