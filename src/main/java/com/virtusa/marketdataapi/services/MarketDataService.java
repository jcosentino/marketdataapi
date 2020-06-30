package com.virtusa.marketdataapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.marketdataapi.repositories.MarketDataRepository;

@Service
public class MarketDataService {
	@Autowired
	private MarketDataRepository marketDataRepository;
}
