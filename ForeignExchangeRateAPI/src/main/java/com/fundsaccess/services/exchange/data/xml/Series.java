package com.fundsaccess.services.exchange.data.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Series {

	private List<Obsolute> obs;
	private String currency;

	@Override
	public String toString() {
		return "Series [Obsolute=" + obs + ", currency=" + currency + "]";
	}

	@XmlElement(name = "Obs")
	public List<Obsolute> getObs() {
		return obs;
	}

	public void setObs(List<Obsolute> obs) {
		this.obs = obs;
	}
	
	@XmlAttribute(name = "UNIT")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
