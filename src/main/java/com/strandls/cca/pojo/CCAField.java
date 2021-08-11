package com.strandls.cca.pojo;

import java.util.List;

//@MongoCollection(name = "cca_field")
public class CCAField {

	// @Id
	// @ObjectId
	private String fieldId;

	private String name;
	private Boolean isRequired;
	private String question;
	private String type;
	private List<String> valueOptions;
	private List<CCAField> childrens;

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getValueOptions() {
		return valueOptions;
	}

	public void setValueOptions(List<String> valueOptions) {
		this.valueOptions = valueOptions;
	}

	public List<CCAField> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<CCAField> childrens) {
		this.childrens = childrens;
	}

}