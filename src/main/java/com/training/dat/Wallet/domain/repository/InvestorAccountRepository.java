package com.training.dat.Wallet.domain.repository;

import com.training.dat.Wallet.domain.model.InvestorAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorAccountRepository extends JpaRepository<InvestorAccount, Long> {
    // Additional query methods if needed
}
