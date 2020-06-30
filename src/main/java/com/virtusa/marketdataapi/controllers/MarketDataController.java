package com.virtusa.marketdataapi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.marketdataapi.models.MarketData;
import com.virtusa.marketdataapi.util.MarketDataGenerator;

@RestController
public class MarketDataController {
	@CrossOrigin("*")
	@GetMapping("/marketdata")
	public MarketData getMarketData(@RequestParam String symbol, @RequestParam String date) {
		return MarketDataGenerator.getMarketDataObj();
	}
}
