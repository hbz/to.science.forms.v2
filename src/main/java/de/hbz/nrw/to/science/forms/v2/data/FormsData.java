package de.hbz.nrw.to.science.forms.v2.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FormsData {
	
	@JsonProperty("article")
	private ArticleData articleData;
	
	@JsonProperty("researchdata")
	private ResearchdataData researchdataData;
	
	@JsonProperty("monograph")
	private MonographData monographData;
	
}
	

