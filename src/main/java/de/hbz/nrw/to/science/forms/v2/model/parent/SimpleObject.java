package de.hbz.nrw.to.science.forms.v2.model.parent;

import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;
import lombok.Data;

@Data
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {"id", "prefLabel"}, alphabetic = true)
public class SimpleObject {

	@JsonProperty("@id")
	@JsonAlias("id")
	private String id;
	
	private String label;
	private String prefLabel;
	
	@JsonProperty(access = READ_ONLY)
	private List<SimpleObject> rdftype;
	
	@JsonProperty(access = WRITE_ONLY)
	private List<String> type;
	
	@JsonSetter("label")
    public void setPrefLabels(String label){
	  this.label = label;
      this.prefLabel = label;
    }
	
}
