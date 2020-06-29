package com.virtusa.marketdataapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.marketdataapi.models.MarketData;

public interface MarketDataRepository extends JpaRepository<MarketData, String> {

}
