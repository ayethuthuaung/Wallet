package com.training.dat.Wallet.domain.dto;

public class InvestorAccountDto {
    private String investorAccountId;
    private String investorAccountName;
    private String accountTypeName;
    private String createdDate;
    private String email;
    private String dateOfBirth;

    // Getters and Setters
    public String getInvestorAccountId() {
        return investorAccountId;
    }

    public void setInvestorAccountId(String investorAccountId) {
        this.investorAccountId = investorAccountId;
    }

    public String getInvestorAccountName() {
        return investorAccountName;
    }

    public void setInvestorAccountName(String investorAccountName) {
        this.investorAccountName = investorAccountName;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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
