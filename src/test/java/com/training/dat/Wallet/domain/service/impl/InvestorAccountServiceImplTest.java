package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.InvestorAccountCreateDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountListDto;
import com.training.dat.Wallet.domain.dto.InvestorAccountUpdateDto;
import com.training.dat.Wallet.domain.dto.WalletInfoDto;
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
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.List;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class InvestorAccountServiceImplTest {

    @Mock
    private InvestorAccountRepository investorAccountRepository;

    @Mock
    private WalletInfoRepository walletInfoRepository;

    @InjectMocks
    private InvestorAccountServiceImpl investorAccountService;


    private InvestorAccountCreateDto createDto;
    private InvestorAccountListDto listDto;
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
    
//    @Test
//    public void testMapToResponseDto() {
//        // Prepare mock InvestorAccount object
//        InvestorAccount account = new InvestorAccount();
//        account.setInvestorAccountId(1L);
//        account.setInvestorAccountName("John Doe");
//        account.setAccountType(AccountType.SELF);
//        account.setCreatedDate(LocalDate.of(2024, 12, 1));
//        account.setEmail("john.doe@example.com");
//        account.setDateOfBirth(LocalDate.of(1990, 1, 1));
//
//        // Prepare mock WalletInfo list
//        WalletInfo wallet1 = new WalletInfo();
//        wallet1.setWalletInfoId(1L);
//        wallet1.setWalletAddress("123ABC");
//        wallet1.setPlatformName("PlatformA");
//        wallet1.setInvestorAccountId(1L);
//
//        WalletInfo wallet2 = new WalletInfo();
//        wallet2.setWalletInfoId(2L);
//        wallet2.setWalletAddress("456DEF");
//        wallet2.setPlatformName("PlatformB");
//        wallet2.setInvestorAccountId(1L);
//
//        List<WalletInfo> walletInfoList = new ArrayList<>();
//        walletInfoList.add(wallet1);
//        walletInfoList.add(wallet2);
//
//        Optional<WalletInfo> optionalWallets = Optional.empty();
//        when(walletInfoRepository.findByInvestorAccountId(1L)).thenReturn(optionalWallets);
//
//
//
//        // Call the method
//        InvestorAccountDto result = investorAccountService.mapToResponseDto(account);
//
//        // Validate the result
//        assertNotNull(result);
//        assertEquals("1", result.getInvestorAccountId());
//        assertEquals("John Doe", result.getInvestorAccountName());
//        assertEquals("SELF", result.getAccountTypeName());
//        assertEquals("2024-12-01", result.getCreatedDate());
//        assertEquals("john.doe@example.com", result.getEmail());
//        assertEquals("1990-01-01", result.getDateOfBirth());
//        assertNotNull(result.getWallet());
//        assertEquals(2, result.getWallet().size());
//
//        // Validate WalletInfoDto mapping
//        WalletInfoDto walletDto1 = result.getWallet().get(0);
//        assertEquals("123ABC", walletDto1.getWalletAddress());
//        assertEquals("PlatformA", walletDto1.getPlatformName());
//
//        WalletInfoDto walletDto2 = result.getWallet().get(1);
//        assertEquals("456DEF", walletDto2.getWalletAddress());
//        assertEquals("PlatformB", walletDto2.getPlatformName());
//
//        // Verify interactions with the repository
//        verify(walletInfoRepository, times(1)).findByInvestorAccountId(1L);
//    }


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
    
    @Test
    public void testGetAccounts() {
        List<InvestorAccount> mockAccounts = new ArrayList<>();
        InvestorAccount account1 = new InvestorAccount();
        account1.setInvestorAccountId(1L);
        account1.setInvestorAccountName("Alice");
        account1.setAccountType(AccountType.SELF);
        account1.setCreatedDate(LocalDate.now());

        InvestorAccount account2 = new InvestorAccount();
        account2.setInvestorAccountId(2L);
        account2.setInvestorAccountName("Bob");
        account2.setAccountType(AccountType.CUSTOMER);
        account2.setCreatedDate(LocalDate.now());

        mockAccounts.add(account1);
        mockAccounts.add(account2);

        when(investorAccountRepository.findAll()).thenReturn(mockAccounts);

        // Call the service method
        List<InvestorAccountListDto> result = investorAccountService.getAccounts(null, "Alice", 1);

        // Validate the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getInvestorAccountName());
        assertEquals("SELF", result.get(0).getAccountTypeName());

        // Verify interactions with the repository
        verify(investorAccountRepository, times(1)).findAll();
    }
    
    @Test
    public void testMapToListDto() {
        // Prepare mock InvestorAccount object
        InvestorAccount account = new InvestorAccount();
        account.setInvestorAccountId(1L);
        account.setInvestorAccountName("John Doe");
        account.setAccountType(AccountType.SELF);
        account.setCreatedDate(LocalDate.of(2024, 12, 1));

        // Call the method directly
        InvestorAccountListDto dto = investorAccountService.mapToListDto(account);

        // Validate the result
        assertNotNull(dto);
        assertEquals("1", dto.getInvestorAccountId());
        assertEquals("John Doe", dto.getInvestorAccountName());
        assertEquals("SELF", dto.getAccountTypeName());
        assertEquals("2024-12-01", dto.getCreatedDate());
    }


    
}
