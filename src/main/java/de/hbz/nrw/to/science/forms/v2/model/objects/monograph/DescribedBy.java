package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class DescribedBy extends SimpleObject {

	@JsonProperty(access = WRITE_ONLY, value = "inDataset")
	private SimpleObject inDataset;
	@JsonProperty(access = READ_ONLY, value = "inDataset")
	private List<SimpleObject> inDatasetList;
	private List<SimpleObject> license;
	
	@JsonProperty(access = WRITE_ONLY, value = "resultOf")
	private Result resultOf;
	@JsonProperty(access = READ_ONLY, value = "resultOf")
	private List<Result> resultOfList;
	
	private SimpleObject modifiedBy;
	
	@JsonProperty(access = WRITE_ONLY, value = "sourceOrganization")
	private IsilObject sourceOrganization;
	@JsonProperty(access = READ_ONLY, value = "sourceOrganization")
	private List<IsilObject> sourceOrganizationList;
	
}
