package com.training.dat.Wallet.domain.dto;

public class WalletInfoDto {
    private String platformName;
    private String walletAddress;

    // Getters and Setters
    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
