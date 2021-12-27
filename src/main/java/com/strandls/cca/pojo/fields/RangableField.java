package com.strandls.cca.pojo.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.conversions.Bson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Facet;
import com.strandls.cca.dao.CCATemplateDao;
import com.strandls.cca.pojo.CCAField;
import com.strandls.cca.util.CCAFilterUtil;

public abstract class RangableField<T extends Comparable<T>> extends CCAField {

	private List<T> minMax = new ArrayList<>();

	@Override
	@BsonIgnore
	@JsonIgnore
	public Facet getGroupAggregation(MultivaluedMap<String, String> queryParameter, CCATemplateDao templateDao,
			ObjectMapper objectMapper, String userId) throws JsonProcessingException {
		String fieldHierarchy = "$" + getFieldHierarchy();
		Bson match = Aggregates.match(CCAFilterUtil.getAllFilters(queryParameter, templateDao, objectMapper, userId,
				new HashSet<>(Arrays.asList(getFieldId()))));
		Bson group = Aggregates.group(null, Accumulators.min("min", fieldHierarchy),
				Accumulators.max("max", fieldHierarchy));
		return new Facet(getFieldId(), match, group);
	}

	/**
	 * This method is for getting minimum and maximum for the generic type T Used
	 * only for the validation purpose.
	 * 
	 * @return
	 */
	public abstract T fetchMaxRange();

	public abstract T fetchMinRange();

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj, String language) {
		if (!super.equals(obj, language))
			return false;

		if (!(obj instanceof RangableField<?>))
			return false;

		RangableField<T> field = (RangableField<T>) obj;
		return getMinMax().equals(field.getMinMax());
	}

	@JsonIgnore
	public boolean isMinMaxSet() {
		return minMax != null && !minMax.isEmpty();
	}

	public List<T> getMinMax() {
		return minMax;
	}

	public void setMinMax(List<T> minMax) {
		this.minMax = minMax;
	}

}
