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
public class Result extends SimpleObject{

	private String endTime;
	
	@JsonProperty(access = WRITE_ONLY, value = "instrument")
	private SimpleObject instrument;
	@JsonProperty(access = READ_ONLY, value = "instrument")
	private List<SimpleObject> instrumentList;
	
	@JsonProperty(access = WRITE_ONLY, value = "object")
	private ResultObject object;
	@JsonProperty(access = READ_ONLY, value = "object")
	private List<ResultObject> objectList;

 }
