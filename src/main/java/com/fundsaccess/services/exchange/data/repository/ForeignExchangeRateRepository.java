package com.fundsaccess.services.exchange.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;

public interface ForeignExchangeRateRepository extends CrudRepository<ForeignExchangeRateModel, Long> {

	List<ForeignExchangeRateModel> findAll();

	List<ForeignExchangeRateModel> findAllByDate(Date date);

	List<ForeignExchangeRateModel> findAllByCurrency(String currency);
	
	ForeignExchangeRateModel findByCurrency(String currency);

	ForeignExchangeRateModel findByDateAndCurrency(Date date, String currency);

	void delete(ForeignExchangeRateModel exchangeRate);

}
