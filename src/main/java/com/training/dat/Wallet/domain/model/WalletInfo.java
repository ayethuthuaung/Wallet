package com.training.dat.Wallet.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class WalletInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletInfoId;

    @NotBlank(message = "Platform Name is required")
    @Column(nullable = false)
    private String platformName;

    @NotBlank(message = "Wallet Address is required")
    @Column(nullable = false)
    private String walletAddress;
    
    @Column(nullable = true) // Optional if association is only needed for retrieval
    private Long investorAccountId;


    public Long getInvestorAccountId() {
		return investorAccountId;
	}

	public void setInvestorAccountId(Long investorAccountId) {
		this.investorAccountId = investorAccountId;
	}

    public Long getWalletInfoId() {
        return walletInfoId;
    }

    public void setWalletInfoId(Long walletInfoId) {
        this.walletInfoId = walletInfoId;
    }

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
