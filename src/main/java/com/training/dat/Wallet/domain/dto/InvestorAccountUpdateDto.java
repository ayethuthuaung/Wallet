package com.training.dat.Wallet.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InvestorAccountUpdateDto {

    @Size(max = 50, message = "{E100001:Investor Account Name must be less than 50.}")
    private String investorAccountName;

    @Pattern(regexp = "1|2", message = "{E100002:Format of Account Type is not correct.}")
    private String accountType;

    @Size(max = 50, message = "{E100001:Email Address must be less than 50.}")
    @Email(message = "{E100002:Format of Email Address is not correct.}")
    private String email;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{E100002:Format of Date of Birth is not correct.}")
    private String dateOfBirth;

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
