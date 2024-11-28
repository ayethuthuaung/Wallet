package com.training.dat.Wallet.domain.controllers;

import com.training.dat.Wallet.controllers.LoginController;
import com.training.dat.Wallet.domain.dto.LoginDto;
import com.training.dat.Wallet.domain.dto.LoginResponseDto;
import com.training.dat.Wallet.domain.service.WalletLoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private WalletLoginService walletLoginService;

    @InjectMocks
    private LoginController loginController;

    private LoginDto loginDto;

    @Before
    public void setUp() {
        loginDto = new LoginDto();
        loginDto.setUserId("user1");
        loginDto.setPassword("password123");
    }

    @Test
    public void testLogin_Success() {
        // Arrange
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUserId("user1");
        loginResponseDto.setUserName("John Doe");
        loginResponseDto.setRoleId("USER");
        loginResponseDto.setAuthToken("mockToken");

        when(walletLoginService.authenticateUser(loginDto)).thenReturn(loginResponseDto);

        // Act
        ResponseEntity<LoginResponseDto> response = loginController.login(loginDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("user1", response.getBody().getUserId());
        assertEquals("mockToken", response.getBody().getAuthToken());
    }

    @Test
    public void testLogin_Failure() {
        // Arrange
        when(walletLoginService.authenticateUser(loginDto)).thenThrow(new RuntimeException("Authentication Failed"));

        // Act
        ResponseEntity<LoginResponseDto> response = loginController.login(loginDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
