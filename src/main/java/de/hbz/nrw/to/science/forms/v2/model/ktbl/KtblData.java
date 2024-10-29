package de.hbz.nrw.to.science.forms.v2.model.ktbl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KtblData {
	
	@JsonProperty("project_title")
	private String projectTitle;
	
	@JsonProperty("test_design")
	private String testDesign;

	@JsonProperty("livestock_category")
	private String livestockCategory;
	
	@JsonProperty("ventilation_system")
	private String ventilationSystem; 
	
	@JsonProperty("livestock_production")
	private String livestockProduction;
	
	@JsonProperty("housing_systems")
	private String housingSystems;
	
	@JsonProperty("additional_housing_systems")
	private List<String> additionalHousingSystems;
	
	@JsonProperty("emi_measurement_techniques")
	private List<String> emissionMeasurementTechniques;
	
	@JsonProperty("emissions")
	private List<String> emissions;
	
	@JsonProperty("emission_reduction_methods")
	private List<String> emissionReductionMethods;

}
