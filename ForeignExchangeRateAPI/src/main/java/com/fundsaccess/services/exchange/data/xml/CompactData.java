package com.fundsaccess.services.exchange.data.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CompactData")
@XmlAccessorType(XmlAccessType.NONE)
public class CompactData {


	@Override
	public String toString() {
		return "CompactData [dataset=" + dataset + "]";
	}

	private Dataset dataset;

	@XmlElement(name = "DataSet")
	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

}
