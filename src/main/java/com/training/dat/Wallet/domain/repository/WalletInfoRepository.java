package com.training.dat.Wallet.domain.repository;

import com.training.dat.Wallet.domain.model.WalletInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletInfoRepository extends JpaRepository<WalletInfo, Long> {
	Optional<WalletInfo> findByWalletAddress(String walletAddress);
	Optional<WalletInfo> findByInvestorAccountId(Long investorAccountId);

}

