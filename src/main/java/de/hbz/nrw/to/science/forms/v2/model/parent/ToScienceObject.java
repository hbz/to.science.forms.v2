package de.hbz.nrw.to.science.forms.v2.model.parent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @author Alessio Pellerito
 *
 */
@Component
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ConfigurationProperties(prefix = "attr") 
public class ToScienceObject {
	
	/* injecting from properties file*/
	private String contentType;
	private String accessScheme;
	private String publishScheme;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private String parentPid;

}
