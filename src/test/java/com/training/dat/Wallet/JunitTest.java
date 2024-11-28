package com.training.dat.Wallet;
import org.junit.runner.RunWith;		
import org.junit.runners.Suite;

import com.training.dat.Wallet.domain.service.impl.InvestorAccountServiceImplTest;
import com.training.dat.Wallet.domain.service.impl.WalletLoginServiceImplTest;		

@RunWith(Suite.class)				
@Suite.SuiteClasses({				
		WalletLoginServiceImplTest.class,
		InvestorAccountServiceImplTest.class
})		

public class JunitTest {				
			// This class remains empty, it is used only as a holder for the above annotations		
}