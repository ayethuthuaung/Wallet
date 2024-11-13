package com.training.dat.Wallet.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class InvestorAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investorAccountId;

    @Column(nullable = false, length = 50)
    private String investorAccountName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate createdDate = LocalDate.now();
    
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public Long getInvestorAccountId() {
        return investorAccountId;
    }

    public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void setInvestorAccountId(Long investorAccountId) {
        this.investorAccountId = investorAccountId;
    }

    public String getInvestorAccountName() {
        return investorAccountName;
    }

    public void setInvestorAccountName(String investorAccountName) {
        this.investorAccountName = investorAccountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
