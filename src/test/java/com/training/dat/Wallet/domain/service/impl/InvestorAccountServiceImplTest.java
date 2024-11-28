package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountListDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountUpdateDto;
import com.training.dat.Wallet.domain.model.AccountType;
import com.training.dat.Wallet.domain.model.InvestorAccount;
import com.training.dat.Wallet.domain.model.WalletInfo;
import com.training.dat.Wallet.domain.repository.InvestorAccountRepository;
import com.training.dat.Wallet.domain.repository.WalletInfoRepository;
import com.training.dat.Wallet.domain.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvestorAccountServiceImplTest {

    @Mock
    private InvestorAccountRepository investorAccountRepository;

    @Mock
    private WalletInfoRepository walletInfoRepository;

    @InjectMocks
    private InvestorAccountServiceImpl investorAccountService;

    private InvestorAccountCreateDto createDto;

    private InvestorAccount account;
    private WalletInfo walletInfo;

    @Before
    public void setUp() {
        createDto = new InvestorAccountCreateDto();
        createDto.setInvestorAccountName("John Doe");
        createDto.setEmail("john.doe@example.com");
        createDto.setDateOfBirth("1990-01-01");
        createDto.setAccountType("1");

        account = new InvestorAccount();
        account.setInvestorAccountId(1L);
        account.setInvestorAccountName("John Doe");
        account.setEmail("john.doe@example.com");
        account.setDateOfBirth(LocalDate.of(1990, 1, 1));
        account.setAccountType(AccountType.SELF);

        walletInfo = new WalletInfo();
        walletInfo.setWalletInfoId(1L);
        walletInfo.setWalletAddress("123ABC");
        walletInfo.setPlatformName("Wallet1234");
        walletInfo.setInvestorAccountId(1L);
    }

    @Test
    public void testCreateAccount() {
        // Mocking the behavior of the repositories
        when(investorAccountRepository.save(any(InvestorAccount.class))).thenReturn(account);
        when(walletInfoRepository.save(any(WalletInfo.class))).thenReturn(walletInfo);

        // Call the method
        InvestorAccountDto result = investorAccountService.createAccount(createDto);

        // Validate the results
        assertNotNull(result);
        assertEquals("John Doe", result.getInvestorAccountName());
        assertEquals("SELF", result.getAccountTypeName());
        assertEquals("1990-01-01", result.getDateOfBirth());
        assertEquals(1L, Long.parseLong(result.getInvestorAccountId()));

        // Verify the interactions with the repositories
        verify(investorAccountRepository, times(1)).save(any(InvestorAccount.class));
        verify(walletInfoRepository, times(1)).save(any(WalletInfo.class));
    }

    @Test
    public void testGetAccountById() {
        // Mocking the repository
        when(investorAccountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Call the method
        InvestorAccountDto result = investorAccountService.getAccountById(1L);

        // Validate the result
        assertNotNull(result);
        assertEquals("John Doe", result.getInvestorAccountName());
        assertEquals("SELF", result.getAccountTypeName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetAccountById_throwsException() {
        // Mocking the repository to return empty
        when(investorAccountRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method, expecting an exception
        investorAccountService.getAccountById(1L);
    }

    @Test
    public void testUpdateAccount() {
        // Mock the repository to return an existing account
        when(investorAccountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(investorAccountRepository.save(any(InvestorAccount.class))).thenReturn(account);

        // Create an update DTO
        InvestorAccountUpdateDto updateDto = new InvestorAccountUpdateDto();
        updateDto.setInvestorAccountName("John Smith");
        updateDto.setEmail("john.smith@example.com");
        updateDto.setDateOfBirth("1992-02-02");
        updateDto.setAccountType("2");

        // Call the update method
        InvestorAccountDto result = investorAccountService.updateAccount("1", updateDto);

        // Validate the result
        assertNotNull(result);
        assertEquals("John Smith", result.getInvestorAccountName());
        assertEquals("CUSTOMER", result.getAccountTypeName());
        assertEquals("1992-02-02", result.getDateOfBirth());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteAccountById_throwsException() {
        // Mocking the repository to check if the account exists
        when(investorAccountRepository.existsById(1L)).thenReturn(false);

        // Call the delete method, expecting an exception
        investorAccountService.deleteAccountById(1L);
    }

    @Test
    public void testDeleteAccountById() {
        // Mocking the repository to check if the account exists
        when(investorAccountRepository.existsById(1L)).thenReturn(true);

        // Call the delete method
        investorAccountService.deleteAccountById(1L);

        // Verify if the delete method was called
        verify(investorAccountRepository, times(1)).deleteById(1L);
    }
}
