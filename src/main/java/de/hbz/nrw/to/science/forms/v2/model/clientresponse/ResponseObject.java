package de.hbz.nrw.to.science.forms.v2.model.clientresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class ResponseObject {

	private Integer code;
	private String text;
	private String error;
	
}

