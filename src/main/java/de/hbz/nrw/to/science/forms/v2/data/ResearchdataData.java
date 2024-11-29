package de.hbz.nrw.to.science.forms.v2.data;

import java.util.Map;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class ResearchdataData {
	
	private Map<String, String> ddc;
	private Map<String, String> dataOrigin;
	private Map<String, String> medium;
	private Map<String, String> license;
	private Map<String, String> language;
	private Map<String, String> role;
	private Map<String, String> personLookupEndpoints;
	private Map<String, String> subjectLookupEndpoints;
	private Map<String, String> fundingLookupEndpoints;
	private Map<String, String> institutionLookupEndpoints;
	private Map<String, String> livestockCategory;
	private Map<String, String> livestockProduction;
	private Map<String, String> housingSystems;
	private Map<String, String> additionalHousingSystems;
	private Map<String, String> ventilationSystem;
	private Map<String, String> emissionMeasurementTechniques;
	private Map<String, String> emissions;
	private Map<String, String> emissionReductionMethods;
	private Map<String, String> projectTitle;
	private Map<String, String> testDesign;

}
	

