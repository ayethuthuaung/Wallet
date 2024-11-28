package com.training.dat.Wallet.controllers;

import com.training.dat.Wallet.domain.dto.LoginDto;
import com.training.dat.Wallet.domain.dto.LoginResponseDto;
import com.training.dat.Wallet.domain.service.WalletLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final WalletLoginService walletLoginService;

    public LoginController(WalletLoginService walletLoginService) {
        this.walletLoginService = walletLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Validated @RequestBody LoginDto loginDto) {
        LoginResponseDto response = walletLoginService.authenticateUser(loginDto);
        return ResponseEntity.ok(response);
    }
}
