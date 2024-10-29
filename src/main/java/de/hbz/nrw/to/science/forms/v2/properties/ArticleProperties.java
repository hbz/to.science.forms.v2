package de.hbz.nrw.to.science.forms.v2.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import de.hbz.nrw.to.science.forms.v2.util.MapUtils;
import lombok.Data;

@Component("articleData")
@PropertySource("classpath:article.properties")
@ConfigurationProperties("article")
@Data
public class ArticleProperties {
	
	private Map<String, String> professionalGroup;
	private Map<String, String> ddc;
	private Map<String, String> collectionOne;
	private Map<String, String> dewey;
	private Map<String, String> dataOrigin;
	private Map<String, String> publicationStatus;
	private Map<String, String> reviewStatus;
	private Map<String, String> medium;
	private Map<String, String> rdftype;
	private Map<String, String> license;
	private Map<String, String> language;
	private Map<String, String> role;
	private Map<String, String> personLookupEndpoints;
	private Map<String, String> subjectLookupEndpoints;
	private Map<String, String> fundingLookupEndpoints;
	private Map<String, String> institutionLookupEndpoints;
	private Map<String, String> collectionTwoEndpoints;
	private Map<String, String> titleLookupEndpoints;
	
	public Map<String, String> getSortedDdc() {
		return MapUtils.getSortedMapByValues(this.ddc);
	}
}
	

