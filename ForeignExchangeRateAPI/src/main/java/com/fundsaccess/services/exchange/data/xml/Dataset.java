package com.fundsaccess.services.exchange.data.xml;
import javax.xml.bind.annotation.XmlElement;

public class Dataset {


	private Series series;

	@Override
	public String toString() {
		return "Dataset [series=" + series + "]";
	}

	@XmlElement(name = "Series")
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}


}
