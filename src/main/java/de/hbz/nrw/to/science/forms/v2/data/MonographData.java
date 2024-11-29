package de.hbz.nrw.to.science.forms.v2.data;

import java.util.Map;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MonographData {
	
	private Map<String, String> lobidLookupEndpoints;

}
	

