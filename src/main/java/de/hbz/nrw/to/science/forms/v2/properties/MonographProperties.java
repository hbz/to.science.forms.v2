package de.hbz.nrw.to.science.forms.v2.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component("monographData")
@PropertySource("classpath:monograph.properties")
@ConfigurationProperties("monograph")
@Data
public class MonographProperties {
	
	private Map<String, String> lobidLookupEndpoints;

}
	

