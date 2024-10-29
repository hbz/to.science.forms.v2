package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class Publication extends SimpleObject{
	
	private List<String> location;
	private List<String> publishedBy;
	private String startDate;
	//private String endDate;
	//private String publicationHistory;

}
