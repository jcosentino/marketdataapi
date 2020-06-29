package com.virtusa.marketdataapi.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

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
	private final VaultConfiguration vaultConfiguration;
	
	public MarketDataGenerator(VaultConfiguration configuration) {
		this.vaultConfiguration = configuration;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static String getMarketDataFetch(String symbol, String date){
		String url = "http://api.marketstack.com/v1/eod";
		String token = "8aefe27bba3f434b598e57b2da0ef305";
//		String token = vaultConfiguration.
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("access_key", token)
		        .queryParam("symbols", symbol)
		        .queryParam("date_from", date);
		try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
            return response.getBody().toString();
        } catch (Exception ex) {
           return ex.getStackTrace().toString();
        }
	}
	
	private static Object getMarketDataObj(String symbol, String date) {
		String jsonString = getMarketDataFetch(symbol, date);
		System.out.println("John");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println("try");
			JsonNode data = mapper.readTree(new StringReader(jsonString));
			data = data.get("data").get(0);
//			MarketData marketData = mapper.readValue(data.asText(), MarketData.class);
			return data.get("open");
		} catch(IOException e) {
			return "Error data";
		}
	}
	
	public static void init() {
		System.out.println("beginning");
		System.out.println("\n\n\n\n\n");
		String symbol = "MSFT";
		String date = "2020-06-26";
		System.out.println(getMarketDataObj(symbol, date));
		System.out.println("\n\n\n\n\n");
		System.out.println("end");
	}

}
