package de.hbz.nrw.to.science.forms.v2.model.objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class Institution extends SimpleObject{

	private String abbreviatedNameForTheCorporateBody;
	private String dateOfEstablishment;
	private String deprecatedUri;
	private String gndIdentifier;
	private String oldAuthorityNumber;
	private String preferredNameForTheCorporateBody;
	private String variantNameForTheCorporateBody;
}
