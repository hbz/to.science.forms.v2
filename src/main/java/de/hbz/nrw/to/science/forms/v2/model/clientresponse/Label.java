package de.hbz.nrw.to.science.forms.v2.model.clientresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Label {

	private Integer id;
	private String group;
	private String labelStr;
	private JsonConf jsonConf;
	private String comment;
	
}

