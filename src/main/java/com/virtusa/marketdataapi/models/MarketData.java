package com.virtusa.marketdataapi.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	@Column(name="Adjusted_High")
	private float adjHigh;
    
	@Column(name="Adjusted_Low")
	private float adjLow;
    
	@Column(name="Adjusted_Close")
	private float adjClose;
    
	@Column(name="Adjusted_Open")
	private float adjOpen;
    
	@Column(name="Adjusted_Volume")
	private float adjVol;
	
	@Column(name="FiftyTwo_Week_High")
	private float fiftyTwoWeekHigh;
	
	@Column(name="FiftyTwo_Week_Low")
	private float fiftyTwoWeekLow;
	
	@Column(name="Selected_Date", nullable = false)
	private Date date;
}
