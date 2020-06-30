package com.virtusa.marketdataapi.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "marketdata")
public class MarketData {
	@Id
	@Column(name="Symbol", length = 50)
	private String symbol;
	
	@Column(name="Open")
	private float open;
	
	@Column(name="Close")
	private float close;
	
	@Column(name="High")
	private float high;
	
	@Column(name="Low")
	private float low;
	
	@Column(name="Volume")
	private float volume;

	@JsonProperty("adj_high")
	@Column(name="Adjusted_High")
	private float adjHigh;
    
	@JsonProperty("adj_low")
	@Column(name="Adjusted_Low")
	private float adjLow;

	@JsonProperty("adj_close")
	@Column(name="Adjusted_Close")
	private float adjClose;
    
	@JsonProperty("adj_open")
	@Column(name="Adjusted_Open")
	private float adjOpen;
    
	@JsonProperty("adj_vol")
	@Column(name="Adjusted_Volume")
	private float adjVol;
	
	@Column(name="Selected_Date", nullable = false)
	private LocalDate date;
}
