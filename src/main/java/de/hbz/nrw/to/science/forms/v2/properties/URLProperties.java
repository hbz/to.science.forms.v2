package de.hbz.nrw.to.science.forms.v2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties("url.to")
@Data
public class URLProperties {
	
	private String context;
	private String orcidApi;
	private String orcidSearch;
	private String lobidSearch;
	private String member;
	private String lobid;
	private String lobidGnd;
	private String lobidGndSearch;
	private String funding;
	private String fundingApi;
	private String agrovocSearch;
	private String agrovocData;
	private String vagrant;
	private String findLabels;
	private String geoSearch;
	private String geoNames;
	
}
