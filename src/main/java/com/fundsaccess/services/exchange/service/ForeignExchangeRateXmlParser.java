package com.fundsaccess.services.exchange.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fundsaccess.services.exchange.data.domain.ForeignExchangeRateModel;
import com.fundsaccess.services.exchange.data.xml.Obsolute;
import com.fundsaccess.services.exchange.data.xml.CompactData;
import com.fundsaccess.services.exchange.data.xml.Series;


@Service
public class ForeignExchangeRateXmlParser {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	//	@Value("${url}")
	//	private String url;

	private final RestTemplate restTemplate;

	public ForeignExchangeRateXmlParser(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.TEXT_HTML);
		jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
		messageConverters.add(jaxbMessageConverter);
		this.restTemplate.setMessageConverters(messageConverters);
		////		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		////        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_OCTET_STREAM,MediaType.TEXT_HTML));
		////        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	@Async
	public Collection<ForeignExchangeRateModel> getUpdatedRates() throws Exception {
		log.info("Updated Foreign exchange rates");

		List<ForeignExchangeRateModel> rates1 = new LinkedList<>();
		Collection<CompactData> envelopes = getCompactDataList();

		for(CompactData env: envelopes) {
			Collection<ForeignExchangeRateModel> rates = transformCompactDataToExchangeRates(env);
			rates1.addAll(rates);
		}
		rates1 = rates1.stream().filter(t -> t.getRate()!= null).collect(Collectors.toList());
		log.info("Removed Null Check Rates" + rates1);

		return rates1;
	}

	private Collection<ForeignExchangeRateModel> transformCompactDataToExchangeRates(CompactData envelope) throws Exception {
		LinkedList<ForeignExchangeRateModel> rates = new LinkedList<>();

		Series series = envelope.getDataset().getSeries(); //for currency
		series.getObs().stream().forEach(cube -> transformObsolute(series, cube, rates));

		return rates;
	}

	private Date parseDate(Obsolute cube) {
		String timeString = cube.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = simpleDateFormat.parse(timeString);
		} catch (ParseException e) {
			log.error("Cannot parse the date: " + timeString, e);
			return null;
		}
		return date;
	}

	private void transformObsolute(Series series, Obsolute cube, LinkedList<ForeignExchangeRateModel> rates) {
		Date date = parseDate(cube);
		ForeignExchangeRateModel fExchangeRate = new ForeignExchangeRateModel(series.getCurrency(), cube.getRate(), date);
		rates.add(fExchangeRate);
	}

	private CompactData getCompactData(String str) throws JAXBException {
		//CompactData compactData = restTemplate.getForObject(url, CompactData.class); // for REST call
		File file = new File(str);  
		JAXBContext jaxbContext = JAXBContext.newInstance(CompactData.class);  
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
		CompactData compactData= (CompactData) jaxbUnmarshaller.unmarshal(file); 
		System.out.println("CompactData :"+ compactData);
		return compactData;

	}

	private Collection<CompactData> getCompactDataList() throws JAXBException {
		Collection<CompactData> envList = new ArrayList<CompactData>();
		envList.add(getCompactData("/Users/himan/BBEX3.D.BRL.EUR.BB.AC.000.xml"));
		envList.add(getCompactData("/Users/himan/BBEX3.D.AUD.EUR.BB.AC.000.xml"));
		envList.add(getCompactData("/Users/himan/BBEX3.D.NZD.EUR.BB.AC.000.xml"));	
		envList.add(getCompactData("/Users/himan/BBEX3.D.INR.EUR.BB.AC.000.xml"));	
		envList.add(getCompactData("/Users/himan/BBEX3.D.CAD.EUR.BB.AC.000.xml"));
		envList.add(getCompactData("/Users/himan/BBEX3.D.USD.EUR.BB.AC.000.xml"));	
		return envList;

	}

}
