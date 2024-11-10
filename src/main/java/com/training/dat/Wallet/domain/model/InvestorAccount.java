package com.training.dat.Wallet.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class InvestorAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investorAccountId;

    @NotBlank(message = "Investor Account Name is required")
    @Size(max = 50, message = "Investor Account Name must be less than 50")
    @Column(nullable = false, length = 50)
    private String investorAccountName;

    @NotNull(message = "Account Type is required")
    @Column(nullable = false)
    private Long accountType;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email Address must be less than 50")
    @Email(message = "Format of Email Address is not correct")
    @Column(nullable = false, length = 50)
    private String email;

    @NotNull(message = "Date of Birth is required")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate createdDate = LocalDate.now();

    // Getters and Setters
    public Long getInvestorAccountId() {
        return investorAccountId;
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

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
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
