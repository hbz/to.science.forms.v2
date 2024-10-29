package de.hbz.nrw.to.science.forms.v2.model.parent;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonFilter("noEmptyValuesFilter")
@EqualsAndHashCode(callSuper = false)
public class CreatorObject extends SimpleObject {

	private SimpleObject affiliation;
	private String academicDegree;
	private List<String> role;
}
