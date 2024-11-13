package com.training.dat.Wallet.domain.service;

import com.training.dat.Wallet.domain.dto.LoginDto;
import com.training.dat.Wallet.domain.dto.LoginResponseDto;

public interface WalletLoginService {
    LoginResponseDto authenticateUser(LoginDto loginDto);
}
