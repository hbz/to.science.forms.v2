package de.hbz.nrw.to.science.forms.v2.model.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Source;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class ComponentList extends SimpleObject {
	
	private String gndIdentifier;
	private String oldAuthorityNumber;
	private String preferredNameForTheSubjectHeading;
	private List<SimpleObject> rdftype;
	private List<Source> source;
}
