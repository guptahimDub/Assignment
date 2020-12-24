/*package com.fundsaccess.services.exchange.data.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;

/**
 * @author himan
 *
 */

/*
@RunWith(SpringRunner.class)
@DataJpaTest
public class ForeignExchangeRateRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ForeignExchangeRateRepository exchangeRateRepository;

	@Test
	public void testGetRate() {
		Double excRate = 1.0;
		entityManager.persist(new ForeignExchangeRateModel("BRL", excRate, new Date()));

		ForeignExchangeRateModel rate = exchangeRateRepository.findByCurrency("BRL");
		assertEquals(excRate, rate.getRate());
	}
	
	@Test
	public void testGettheCurrency() {
		String currency = "EURL";
		Double excRate = 1.5;
		@SuppressWarnings("deprecation")
		Date date = new Date("2020-12-18");
		entityManager.persist(new ForeignExchangeRateModel(currency, 1.5, date));

		ForeignExchangeRateModel rate = exchangeRateRepository.findByDateAndCurrency(date, currency);
		assertEquals(excRate, rate.getRate());
	}

}

*/