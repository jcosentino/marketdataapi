package com.virtusa.marketdataapi.util;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.marketdataapi.configurations.VaultConfiguration;
import com.virtusa.marketdataapi.models.MarketData;

@EnableConfigurationProperties(VaultConfiguration.class)
public class MarketDataGenerator {
	private static final Logger logger = LoggerFactory.getLogger(MarketDataGenerator.class);
	
//	private static final VaultConfiguration vaultConfiguration;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	public MarketDataGenerator(VaultConfiguration configuration) {
//		this.vaultConfiguration = configuration;
//	}

	private static String getMarketDataFetch(String symbol, String date){
		String url = "http://api.marketstack.com/v1/eod";
		String token = "8aefe27bba3f434b598e57b2da0ef305";
//		String token = vaultConfiguration.getApiKey();
		logger.info("Symbol: {sym}".format(symbol));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("access_key", token)
		        .queryParam("symbols", symbol)
		        .queryParam("date_from", date);
		try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            String userAgentString =
            	"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
            headers.add("user-agent", userAgentString);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
            return response.getBody().toString();
        } catch (Exception ex) {
           return ex.getStackTrace().toString();
        }
	}
	
	public static MarketData getMarketDataObj(String symbol, String date) {
		String jsonString = getMarketDataFetch(symbol, date);
		ObjectMapper mapper = new ObjectMapper();
		MarketData marketData = (new MarketData());
		try {
			JsonNode data = mapper.readTree(new StringReader(jsonString));
			data = data.get("data").get(0);
			if(data == null) {
				return marketData;
			}
			marketData.setSymbol(symbol);
			float open = Float.parseFloat(data.get("open").toString());
			marketData.setOpen(open);
			float close = Float.parseFloat(data.get("close").toString());
			marketData.setClose(close);
			float high = Float.parseFloat(data.get("high").toString());
			marketData.setHigh(high);
			float low = Float.parseFloat(data.get("low").toString());
			marketData.setLow(low);
			float volume = Float.parseFloat(data.get("volume").toString());
			marketData.setVolume(volume);
			float adjHigh = Float.parseFloat(data.get("adj_high").toString());
			marketData.setAdjHigh(adjHigh);
			float adjLow = Float.parseFloat(data.get("adj_low").toString());
			marketData.setAdjLow(adjLow);
			float adjClose = Float.parseFloat(data.get("adj_close").toString());
			marketData.setAdjClose(adjClose);
			float adjOpen = Float.parseFloat(data.get("adj_open").toString());
			marketData.setAdjOpen(adjOpen);
			float adjVol = Float.parseFloat(data.get("adj_volume").toString());
			marketData.setAdjVol(adjVol);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate apiDate = LocalDate.parse(date, formatter);
			marketData.setDate(apiDate);
		} catch(IOException e) {
			e.printStackTrace();
			marketData.setDate(LocalDate.now());
		}
		return marketData;
	}

}
