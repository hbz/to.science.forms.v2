package de.hbz.nrw.to.science.forms.v2.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import lombok.Data;

/**
 * Mapping class of the application.properties file with prefix "resource"
 * @author Alessio Pellerito
 *
 */
@Data
@Configuration
//@PropertySource(value = "file:/etc/forms/application.properties", ignoreResourceNotFound = true) // for prod
@PropertySource(value = "file:/etc/forms-dev/application.properties", ignoreResourceNotFound = true) // for dev
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
