package com.training.dat.Wallet.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InvestorAccountCreateDto {
    @NotBlank(message = "Investor Account Name is required")
    @Size(max = 50, message = "Investor Account Name must be less than 50")
    private String investorAccountName;

    @NotBlank(message = "Account Type is required")
    private String accountType;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email Address must be less than 50")
    @Email(message = "Format of Email Address is not correct")
    private String email;

    @NotBlank(message = "Date of Birth is required")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Format of Date of Birth is not correct")
    private String dateOfBirth;

    // Getters and Setters
    public String getInvestorAccountName() {
        return investorAccountName;
    }

    public void setInvestorAccountName(String investorAccountName) {
        this.investorAccountName = investorAccountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
