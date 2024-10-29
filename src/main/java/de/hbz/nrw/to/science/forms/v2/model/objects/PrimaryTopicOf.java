package de.hbz.nrw.to.science.forms.v2.model.objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
@Scope("prototype")  // Creates multiple instances of this class (default: singleton)
public class PrimaryTopicOf extends SimpleObject {

	private String primaryTopic;
	
}
