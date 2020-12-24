package com.fundsaccess.services.exchange.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;
import com.fundsaccess.services.exchange.data.repository.ForeignExchangeRateRepository;

@Service
public class ForeignExchangeRateService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ForeignExchangeRateRepository fxRateRepository;

	@Transactional
	public void updateExchangeRates(Collection<ForeignExchangeRateModel> rates) {
		log.debug("Removing all rates");
		fxRateRepository.findAll().forEach(rate -> fxRateRepository.delete(rate));
		entityManager.flush();

		log.debug("Adding new rates" + rates );
		rates.removeIf(Objects::isNull);
		log.debug("Removed Null rows" + rates );
		rates.stream().forEach(rate -> entityManager.persist(rate));
	}

	public List<ForeignExchangeRateModel> getAllRates() {
		return fxRateRepository.findAll();
	}

	public List<ForeignExchangeRateModel> getAllRatesByDate(Date date) {
		return fxRateRepository.findAllByDate(date);
	}

	public List<ForeignExchangeRateModel> getAllRatesByCurrency(String currency) {
		return fxRateRepository.findAllByCurrency(currency);
	}

	public ForeignExchangeRateModel getByDateAndCurrency(Date date, String currency) {
		return fxRateRepository.findByDateAndCurrency(date, currency);
	}

	public Double convert(Date date, String currencyFrom, String currencyTo, Double amount) {
		if (date == null) {
			date = new Date();
		}

		double exchangeRateValFrom = 1f;
		double exchangeRateValTo = 1f;

		try {
			exchangeRateValFrom = getForeignExRateValue(date, currencyFrom, exchangeRateValFrom);
			exchangeRateValTo = getForeignExRateValue(date, currencyTo, exchangeRateValTo);

			return (amount * exchangeRateValTo) / exchangeRateValFrom;
		} catch (Exception e) {
			log.error("Error on Rate Conversion", e);
			return null;
		}
	}

	public Double convertToEuro(Date date, String currencyFrom, Double amount) {
		if (date == null) {
			date = new Date();
		}

		double exchangeRateValFrom = 1f;
		double exchangeRateValTo = 1f;

		try {
			exchangeRateValFrom = getForeignExRateValue(date, currencyFrom, exchangeRateValFrom);

			return (amount * exchangeRateValTo) / exchangeRateValFrom;
		} catch (Exception e) {
			log.error("Error on Rate Conversion", e);
			return null;
		}
	}

	private double getForeignExRateValue(Date date, String currency, double fExchangeRateVal) {
		if (!"EUR".equalsIgnoreCase(currency)) {
			ForeignExchangeRateModel fExchangeRate = fxRateRepository.findByDateAndCurrency(date, currency);
			fExchangeRateVal = fExchangeRate.getRate();
		}

		return fExchangeRateVal;
	}

}
