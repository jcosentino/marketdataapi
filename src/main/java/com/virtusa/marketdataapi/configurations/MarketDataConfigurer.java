package com.virtusa.marketdataapi.configurations;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;

import com.virtusa.marketdataapi.models.MarketData;
import com.virtusa.marketdataapi.util.MarketDataGenerator;

@Configuration
@EnableBinding(Source.class)
public class MarketDataConfigurer {
	@Autowired
	private MarketDataGenerator marketDataGenerator;
	private MarketData response;
	private static final Logger logger = LoggerFactory.getLogger(MarketDataConfigurer.class);
	
	@PostConstruct
	public void init() {
//		response=marketDataGenerator.getLIBOR().values().stream().limit(50).collect(Collectors.toList());
		response = this.marketDataGenerator.getMarketDataObj();
	}
//	
	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "100000", 
	maxMessagesPerPoll = "1"))
	public MessageSource<MarketData>  sendMessage(){	
				
		return ()->MessageBuilder.withPayload(response).build();
	}
}
