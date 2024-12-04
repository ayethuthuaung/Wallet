package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountUpdateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountListDto;
import com.training.dat.Wallet.domain.dto.WalletInfoDto;
import com.training.dat.Wallet.domain.exception.ResourceNotFoundException;
import com.training.dat.Wallet.domain.model.AccountType;

import com.training.dat.Wallet.domain.model.InvestorAccount;
import com.training.dat.Wallet.domain.model.WalletInfo;
import com.training.dat.Wallet.domain.repository.InvestorAccountRepository;
import com.training.dat.Wallet.domain.repository.WalletInfoRepository;
import com.training.dat.Wallet.domain.service.InvestorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestorAccountServiceImpl implements InvestorAccountService {

    private final InvestorAccountRepository investorAccountRepository;
    private final WalletInfoRepository walletInfoRepository;

    @Autowired
    public InvestorAccountServiceImpl(InvestorAccountRepository investorAccountRepository,
                                      WalletInfoRepository walletInfoRepository) {
        this.investorAccountRepository = investorAccountRepository;
        this.walletInfoRepository = walletInfoRepository;
    }

    @Override
    public InvestorAccountDto getAccountById(Long id) {
        InvestorAccount account = investorAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor Account does not exist."));
        
        InvestorAccountDto dto = new InvestorAccountDto();
        dto.setInvestorAccountId(String.valueOf(account.getInvestorAccountId()));
        dto.setInvestorAccountName(account.getInvestorAccountName());
        dto.setAccountTypeName(account.getAccountType().name());
        dto.setCreatedDate(account.getCreatedDate().toString());
        dto.setEmail(account.getEmail());
        dto.setDateOfBirth(account.getDateOfBirth().toString());

        Optional<WalletInfo> walletInfoOpt = walletInfoRepository.findByInvestorAccountId(account.getInvestorAccountId());
        WalletInfoDto walletDto = walletInfoOpt.map(this::mapWalletInfoToDto).orElse(null);
        dto.setWallet(walletDto == null ? Collections.emptyList() : Collections.singletonList(walletDto));

        return dto;
    }


    @Override
    public InvestorAccountDto createAccount(InvestorAccountCreateDto dto) {
        AccountType accountType = AccountType.fromId(dto.getAccountType());

        InvestorAccount account = new InvestorAccount();
        account.setInvestorAccountName(dto.getInvestorAccountName());
        account.setEmail(dto.getEmail());
        account.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        account.setCreatedDate(LocalDate.now());
        account.setAccountType(accountType);

        InvestorAccount savedAccount = investorAccountRepository.save(account);

        String walletUuid = UUID.randomUUID().toString();
        String platformPrefix = "Wallet" + walletUuid.substring(0, 4);

        WalletInfo walletInfo = new WalletInfo();
        walletInfo.setPlatformName(platformPrefix);
        walletInfo.setWalletAddress(walletUuid);
        walletInfo.setInvestorAccountId(savedAccount.getInvestorAccountId());
        //walletInfo.setInvestorAccount(savedAccount);
        walletInfoRepository.save(walletInfo);

        InvestorAccountDto responseDto = new InvestorAccountDto();
        responseDto.setInvestorAccountId(String.valueOf(savedAccount.getInvestorAccountId()));
        responseDto.setInvestorAccountName(savedAccount.getInvestorAccountName());
        responseDto.setAccountTypeName(savedAccount.getAccountType().name());
        responseDto.setCreatedDate(savedAccount.getCreatedDate().toString());
        responseDto.setEmail(savedAccount.getEmail());
        responseDto.setDateOfBirth(savedAccount.getDateOfBirth().toString());

        WalletInfoDto walletDto = mapWalletInfoToDto(walletInfo);
        responseDto.setWallet(Collections.singletonList(walletDto));

        return responseDto;
    }


    private WalletInfoDto mapWalletInfoToDto(WalletInfo walletInfo) {
        WalletInfoDto walletDto = new WalletInfoDto();
        walletDto.setPlatformName(walletInfo.getPlatformName());
        walletDto.setWalletAddress(walletInfo.getWalletAddress());
        return walletDto;
    }

    @Override
    public List<InvestorAccountListDto> getAccounts(String investorAccountId, String investorAccountName, Integer accountType) {
        // Validate accountType format (only allow 1 or 2)
        if (accountType != null && accountType != 1 && accountType != 2) {
            throw new IllegalArgumentException("E100002:Format of Account Type is not correct.");
        }

        List<InvestorAccount> accounts = investorAccountRepository.findAll();

        return accounts.stream()
                .filter(account -> (investorAccountId == null || account.getInvestorAccountId().toString().equals(investorAccountId)) &&
                        (investorAccountName == null || account.getInvestorAccountName().contains(investorAccountName)) &&
                        (accountType == null || account.getAccountType().ordinal() + 1 == accountType))
                .map(this::mapToListDto)
                .collect(Collectors.toList());
    }

    public InvestorAccountListDto mapToListDto(InvestorAccount account) {
        InvestorAccountListDto dto = new InvestorAccountListDto();
        dto.setInvestorAccountId(account.getInvestorAccountId().toString());
        dto.setInvestorAccountName(account.getInvestorAccountName());
        dto.setAccountTypeName(account.getAccountType().name());
        dto.setCreatedDate(account.getCreatedDate().toString());
        return dto;
    }
    
    @Override
    public InvestorAccountDto updateAccount(String investorAccountId, InvestorAccountUpdateDto updateDto) {
        // Fetch and update the InvestorAccount
        InvestorAccount account = investorAccountRepository.findById(Long.parseLong(investorAccountId))
            .orElseThrow(() -> new ResourceNotFoundException("Investor Account does not exist."));

        account.setInvestorAccountName(updateDto.getInvestorAccountName());
        account.setEmail(updateDto.getEmail());
        account.setDateOfBirth(LocalDate.parse(updateDto.getDateOfBirth()));
        account.setAccountType(AccountType.fromId(updateDto.getAccountType()));
        
        InvestorAccount savedAccount = investorAccountRepository.save(account);

        // Map InvestorAccount to response DTO
        InvestorAccountDto responseDto = mapToResponseDto(savedAccount);

        // Manually fetch WalletInfo records and map them to WalletInfoDto
        List<WalletInfoDto> walletDtos = walletInfoRepository.findByInvestorAccountId(savedAccount.getInvestorAccountId())
                .stream()
                .map(this::mapWalletInfoToDto)
                .collect(Collectors.toList());

        responseDto.setWallet(walletDtos); // Set the wallet info in the response
        return responseDto;
    }

    public InvestorAccountDto mapToResponseDto(InvestorAccount account) {
        InvestorAccountDto responseDto = new InvestorAccountDto();
        responseDto.setInvestorAccountId(String.valueOf(account.getInvestorAccountId()));
        responseDto.setInvestorAccountName(account.getInvestorAccountName());
        responseDto.setAccountTypeName(account.getAccountType().name());
        responseDto.setCreatedDate(account.getCreatedDate().toString());
        responseDto.setEmail(account.getEmail());
        responseDto.setDateOfBirth(account.getDateOfBirth().toString());

        // Fetch WalletInfo details by investorAccountId
        List<WalletInfoDto> walletDtos = walletInfoRepository.findByInvestorAccountId(account.getInvestorAccountId())
                .stream()
                .map(this::mapWalletInfoToDto)
                .collect(Collectors.toList());
        responseDto.setWallet(walletDtos);

        return responseDto;
    }
    
    @Override
    public void deleteAccountById(Long investorAccountId) {
        if (!investorAccountRepository.existsById(investorAccountId)) {
            throw new ResourceNotFoundException("Investor Account does not exist.");
        }
        investorAccountRepository.deleteById(investorAccountId);
    }
}
