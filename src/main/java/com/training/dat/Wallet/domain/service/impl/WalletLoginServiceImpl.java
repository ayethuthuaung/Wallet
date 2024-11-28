package com.training.dat.Wallet.domain.service.impl;

import com.training.dat.Wallet.domain.dto.LoginDto;
import com.training.dat.Wallet.domain.dto.LoginResponseDto;
import com.training.dat.Wallet.domain.exception.ResourceNotFoundException;
import com.training.dat.Wallet.domain.model.User;
import com.training.dat.Wallet.domain.repository.UserRepository;
import com.training.dat.Wallet.domain.service.WalletLoginService;
import com.training.dat.Wallet.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class WalletLoginServiceImpl implements WalletLoginService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public WalletLoginServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDto authenticateUser(LoginDto loginDto) {
        Optional<User> userOpt = userRepository.findByUserId(loginDto.getUserId());

        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(loginDto.getPassword())) {
            throw new ResourceNotFoundException("User Id and password do not match.");
        }

        User user = userOpt.get();
        String authToken = jwtUtil.generateToken(user.getUserId());

        LoginResponseDto response = new LoginResponseDto();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setRoleId(user.getRole().getRoleName());
        response.setAuthToken(authToken);

        return response;
    }
}
