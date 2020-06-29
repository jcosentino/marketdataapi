package com.virtusa.marketdataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.virtusa.marketdataapi.util.MarketDataGenerator;

@SpringBootApplication
public class MarketdataapiApplication {

	public static void main(String[] args) {
		MarketDataGenerator.init();
		SpringApplication.run(MarketdataapiApplication.class, args);
	}

}
