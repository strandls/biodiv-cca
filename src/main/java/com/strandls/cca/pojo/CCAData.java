package com.strandls.cca.pojo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.cca.service.impl.LogActivities;

public class CCAData extends BaseEntity {

	private String shortName;

	private Map<String, CCAFieldValue> ccaFieldValues;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Map<String, CCAFieldValue> getCcaFieldValues() {
		return ccaFieldValues;
	}

	public void setCcaFieldValues(Map<String, CCAFieldValue> ccaFieldValues) {
		this.ccaFieldValues = ccaFieldValues;
	}

	public CCAData overrideFieldData(HttpServletRequest request, CCAData ccaData, ObjectMapper objectMapper,
			LogActivities logActivities) throws JsonProcessingException {

		Map<String, CCAFieldValue> fieldsMap = ccaData.getCcaFieldValues();

		for (Map.Entry<String, CCAFieldValue> e : ccaData.getCcaFieldValues().entrySet()) {
			if (fieldsMap.containsKey(e.getKey())) {
				String inputValue = objectMapper.writeValueAsString(e.getValue());
				String dbValue = objectMapper.writeValueAsString(this.ccaFieldValues.get(e.getKey()));
				if (!inputValue.equals(dbValue)) {
					this.ccaFieldValues.put(e.getKey(), e.getValue());

					// Log the difference
					String desc = "Data updated with template : " + dbValue + " to : " + inputValue;
					logActivities.logCCAActivities(request.getHeader(HttpHeaders.AUTHORIZATION), desc, ccaData.getId(),
							ccaData.getId(), "ccaData", ccaData.getId(), "Data updated");
				}
			} else {
				this.ccaFieldValues.put(e.getKey(), e.getValue());

				// Log newly added data entries
				String desc = "Data updated with template : " + ccaData.getShortName();
				logActivities.logCCAActivities(request.getHeader(HttpHeaders.AUTHORIZATION), desc, ccaData.getId(),
						ccaData.getId(), "ccaData", ccaData.getId(), "Data created");
			}
		}
		return this;
	}

}
