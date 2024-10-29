package de.hbz.nrw.to.science.forms.v2.model.objects.monograph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class IsbnObject extends SimpleObject{

	private List<String> Isbn;
}
