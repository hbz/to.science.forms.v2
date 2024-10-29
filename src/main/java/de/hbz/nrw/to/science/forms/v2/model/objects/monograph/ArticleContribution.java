package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
public class ArticleContribution extends SimpleObject {

	private List<Agent> agent;
	private List<SimpleObject> rdftype;
	private List<SimpleObject> role;
	
}
