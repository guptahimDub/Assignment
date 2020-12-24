package com.fundsaccess.services.exchange.task;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;
import com.fundsaccess.services.exchange.service.ForeignExchangeRateXmlParser;
import com.fundsaccess.services.exchange.service.ForeignExchangeRateService;

@Component
public class ForeignExchangeUpdateRatesTask {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ForeignExchangeRateXmlParser xmlParserService;

	@Autowired
	private ForeignExchangeRateService fExchangeRateService;

	@Scheduled(fixedRateString = "${service.rate}")
	public void updateForeignExchangeRates() {
		log.info("Updating the Foreign Exchange Rates");
		try {
			Collection<ForeignExchangeRateModel> updatedfExRates = xmlParserService.getUpdatedRates();
			fExchangeRateService.updateExchangeRates(updatedfExRates);
			log.info("Exchange Rates updated");
		} catch (Exception e) {
			log.error("Cannot update Exchange Rates", e);
		}
	}

}
