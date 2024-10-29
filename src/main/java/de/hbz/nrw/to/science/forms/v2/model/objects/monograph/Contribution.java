package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
public class Contribution {

	@JsonProperty(access = WRITE_ONLY, value = "agent")
	private Agent agent;
	
	@JsonProperty(access = READ_ONLY, value = "agent")
	private List<Agent> agentList;
	
	@JsonProperty(access = READ_ONLY)
	private List<SimpleObject> rdftype;
	
	@JsonProperty(access = WRITE_ONLY)
	private List<String> type;
	
	@JsonProperty(access = WRITE_ONLY, value = "role")
	private SimpleObject role;
	
	@JsonProperty(access = READ_ONLY, value = "role")
	private List<SimpleObject> roleList;

	private List<Contribution> contribution;
	
}
