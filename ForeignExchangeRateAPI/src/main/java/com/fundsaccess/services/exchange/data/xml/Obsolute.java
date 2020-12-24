package com.fundsaccess.services.exchange.data.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Obsolute {

	private String time;


	@Override
	public String toString() {
		return "Obsolute [time=" + time + ", rate=" + rate + "]";
	}

	private Double rate;

	@XmlAttribute(name = "TIME_PERIOD")
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}


	@XmlAttribute(name = "OBS_VALUE")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
