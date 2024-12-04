package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.LoginDto;
import com.training.dat.Wallet.domain.dto.LoginResponseDto;
import com.training.dat.Wallet.domain.model.Role;
import com.training.dat.Wallet.domain.model.User;
import com.training.dat.Wallet.domain.repository.UserRepository;
import com.training.dat.Wallet.util.JwtUtil;
import com.training.dat.Wallet.domain.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WalletLoginServiceImplTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private WalletLoginServiceImpl walletLoginService;

    private User mockUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Role role = new Role(1L, "USER");
        mockUser = new User("user1", "John Doe", "password123", role);
    }

    @Test
    public void testAuthenticateUser_Success() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("user1");
        loginDto.setPassword("password123");

        when(userRepository.findByUserId("user1")).thenReturn(Optional.of(mockUser));
        when(jwtUtil.generateToken("user1")).thenReturn("mockToken");

        // Act
        LoginResponseDto response = walletLoginService.authenticateUser(loginDto);

        // Assert
        assertNotNull(response);
        assertEquals("user1", response.getUserId());
        assertEquals("John Doe", response.getUserName());
        assertEquals("USER", response.getRoleId());
        assertEquals("mockToken", response.getAuthToken());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testAuthenticateUser_UserNotFound() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("nonExistentUser");
        loginDto.setPassword("password123");

        when(userRepository.findByUserId("nonExistentUser")).thenReturn(Optional.empty());

        // Act
        walletLoginService.authenticateUser(loginDto);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testAuthenticateUser_InvalidPassword() {
        // Arrange
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("user1");
        loginDto.setPassword("wrongPassword");

        when(userRepository.findByUserId("user1")).thenReturn(Optional.of(mockUser));

        // Act
        walletLoginService.authenticateUser(loginDto);
    }
}
