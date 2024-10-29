package de.hbz.nrw.to.science.forms.v2.model.objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.IsilObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Component
@Scope("prototype")
public class Item extends SimpleObject{

	private String callNumber;
	private String currentLocation;
	//private String currentLibrary;
	//private String electronicLocator;
	@JsonProperty(access = WRITE_ONLY, value = "heldBy")
	private IsilObject heldBy;
	@JsonProperty(access = READ_ONLY, value = "heldBy")
	private List<IsilObject> heldByList;
	//private String note;
	//private List<String> seeAlso;
	private String serialNumber;
	//private String sublocation;
	
}
