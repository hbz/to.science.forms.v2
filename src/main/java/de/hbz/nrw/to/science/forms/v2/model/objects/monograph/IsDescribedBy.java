package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class IsDescribedBy extends SimpleObject {

	private String created;
	private String createdBy;
	private String describes;
	private String lastModifiedBy;
	private String modified;
	private String objectTimestamp;
	
}
