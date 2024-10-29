package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class ResultObject extends SimpleObject{

	private String created;
	private String dateCreated;
	private String dateModified;
	
	@JsonProperty(access = WRITE_ONLY, value = "inDataset")
	private SimpleObject inDataset;
	@JsonProperty(access = READ_ONLY, value = "inDataset")
	private List<SimpleObject> inDatasetAsList;
	
	private String modified;
	private List<SimpleObject> modifiedBy;
	
	@JsonProperty(access = WRITE_ONLY, value = "provider")
	private SimpleObject provider;
	@JsonProperty(access = READ_ONLY, value = "provider")
	private List<SimpleObject> providerList;
	
	@JsonProperty(access = WRITE_ONLY, value = "sourceOrganization")
	private IsilObject sourceOrganization;
	@JsonProperty(access = READ_ONLY, value = "sourceOrganization")
	private List<IsilObject> sourceOrganizationList;
 }
