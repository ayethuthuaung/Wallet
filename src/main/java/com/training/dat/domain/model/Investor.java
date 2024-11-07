package com.training.dat.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Embedded;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "investors")
public class Investor {

    @Id
    private String investorAccountId;

    private String investorAccountName;

    private LocalDate createdDate;

    private String email;

    private LocalDate dateOfBirth;

    @Embedded
    private Account account;

	public void setInvestorAccountId(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setInvestorAccountName(Object investorAccountName2) {
		// TODO Auto-generated method stub
		
	}
}
