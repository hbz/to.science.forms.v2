package de.hbz.nrw.to.science.forms.v2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Mapping class of the application.properties file with prefix "resource"
 * @author Alessio Pellerito
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "resource")
public class ResourceProperties {
	
	private String frlUrl;
	private String frlUrlHelptext;
	private String frlApiUser;
	private String frlApiPassword;
	private String frlApiUrl;
	private String frlApiUrlFedora;
	private String orcidApiUrl;
	private String contextUrl;
	private String accessScheme;
	private String publishScheme;
	private String tmpFile;

}
