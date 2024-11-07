package com.training.dat.domain.services;

import com.training.dat.domain.dto.InvestorDto;
import com.training.dat.domain.model.Investor;

public interface InvestorService {
    Investor registerInvestor(InvestorDto investorDto);
}
