package de.hbz.nrw.to.science.forms.v2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * Mapping class of the application.properties file with prefix "resource"
 * @author Alessio Pellerito
 *
 */
@Data
@Configuration
@PropertySource(value = "file:/etc/forms/application.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "resource")
public class ResourceProperties {
	
	private String apiUser;
	private String apiPassword;
	private String frlUrl;
	private String frlUrlFedora;
	private String orcidApiUrl;
	private String contextUrl;
	private String accessScheme;
	private String publishScheme;
	private String tmpFile;

}
