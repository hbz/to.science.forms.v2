package de.hbz.nrw.to.science.forms.v2.model.objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;

@Component
@Scope("prototype")
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JoinedFunding {

	private SimpleObject fundingJoined;
	private String fundingProgramJoined;
	private String projectIdJoined;
	
}
