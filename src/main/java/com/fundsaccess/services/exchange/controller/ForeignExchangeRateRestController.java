package com.fundsaccess.services.exchange.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;
import com.fundsaccess.services.exchange.service.ForeignExchangeRateService;

@RestController
@RequestMapping("/api/foreignExRate")
@SuppressWarnings({ "rawtypes", "unchecked" })
@Api(tags = {"API"})
public class ForeignExchangeRateRestController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ForeignExchangeRateService exchangeRateService;
	
	@RequestMapping(value = "/getAllCurrency", method = RequestMethod.GET)
	@ApiOperation("Get All the Available Currency")
	public ResponseEntity<List<String>> listAllCurrency() {
		List<ForeignExchangeRateModel> rates = exchangeRateService.getAllRates();
		List<String> allCurrency = new ArrayList<String>();
		
		for(ForeignExchangeRateModel rate: rates) {
			allCurrency.add(rate.getCurrency());
		}
		
		Set<String> set = new HashSet<>(allCurrency);
		allCurrency.clear();
		allCurrency.addAll(set);
		
		if (allCurrency == null || allCurrency.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<String>>(allCurrency, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllRates", method = RequestMethod.GET)
	@ApiOperation("Get All the Available Rates")
	public ResponseEntity<List<ForeignExchangeRateModel>> getAllRates() {
		List<ForeignExchangeRateModel> rates = exchangeRateService.getAllRates();

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ForeignExchangeRateModel>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/{date}", method = RequestMethod.GET)
	@ApiOperation("Get All Rates By Date")
	public ResponseEntity<List<ForeignExchangeRateModel>> getAllByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		List<ForeignExchangeRateModel> rates = exchangeRateService.getAllRatesByDate(date);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ForeignExchangeRateModel>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllRatesByCurrency", method = RequestMethod.GET)
	@ApiOperation("Get All Rates By Currency")
	public ResponseEntity<List<ForeignExchangeRateModel>> getAllByCurrency(@RequestParam("currency") String currency) {
		List<ForeignExchangeRateModel> rates = exchangeRateService.getAllRatesByCurrency(currency);

		if (rates == null || rates.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ForeignExchangeRateModel>>(rates, HttpStatus.OK);
	}

	@RequestMapping(value = "/{date}", method = RequestMethod.GET)
	@ApiOperation("Get all Rates By Date and Currency")
	public ResponseEntity<ForeignExchangeRateModel> getByDateAndCurrency(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("cur") String currency) {
		ForeignExchangeRateModel rate = exchangeRateService.getByDateAndCurrency(date, currency);

		if (rate == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<ForeignExchangeRateModel>(rate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/convertRatesToEuro", method = RequestMethod.GET)
	@ApiOperation("Convert By Date From Currency 1 to Euro with Amount X")
	public ResponseEntity<Double> convertByDateAndCurrencyToEuro(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("currencyFrom") String fromCurrency,
			@RequestParam("amount") Double amount) {

		Double rateValue = exchangeRateService.convertToEuro(date, fromCurrency,amount);
		
		if (rateValue == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Double>(rateValue, HttpStatus.OK);
	}

	@RequestMapping(value = "/convertRates", method = RequestMethod.GET)
	@ApiOperation("Convert By Date From Currency 1 to Currency 2 with Amount X")
	public ResponseEntity<Double> convertByDateAndCurrency(
			@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("currencyFrom") String currencyFrom,
			@RequestParam("currencyTo") String currencyTo,
			@RequestParam("amount") Double amount) {

		Double rateValue = exchangeRateService.convert(date, currencyFrom, currencyTo, amount);
		
		if (rateValue == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Double>(rateValue, HttpStatus.OK);
	}

}
