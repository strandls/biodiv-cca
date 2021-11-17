package com.strandls.cca.pojo.fields.value;

import com.strandls.cca.pojo.CCAFieldValue;

public class TextAreaFieldValue extends CCAFieldValue {

	private String value;

	public TextAreaFieldValue() {
	}

	public TextAreaFieldValue(String dataValue) {
		this.value = dataValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}