package de.hbz.nrw.to.science.forms.v2.service;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.RESEARCHDATA;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.properties.URLProperties;

@Service
public class ResearchdataService {
	
	@Autowired
	private JsonMapperService json;
	
	@Autowired
	private URLProperties url;

	public void populateResearchdataFields(Researchdata researchdata, String pid) {
		if(pid != null) {
			researchdata.setId(pid);
			researchdata.setCatalogId(json.getCatalogId(pid));
			researchdata.setPrefLabel(List.of(pid));
			researchdata.setPrimaryTopic(pid);
			researchdata.setItemID(json.fillItemId(pid));
			researchdata.setIsPrimaryTopicOf(json.fillPrimaryTopicOf(pid));
		}
		
		researchdata.getRecordingCoordinates().removeIf(coordinate -> coordinate.getId().isBlank());	
		researchdata.getRecordingLocation().removeIf(location -> location.getId().isBlank());
		
		SimpleObject obj = new SimpleObject();
		obj.setId(url.getMember() + RESEARCHDATA);
		obj.setPrefLabel(obj.getId());
		researchdata.setIsMemberOf(List.of(obj));
		
		String concat = researchdata.getCreator().stream().map(CreatorObject::getId).collect(Collectors.joining(" | "));
		if(!StringUtils.isBlank(concat))
			researchdata.setContributerOrder(List.of(concat));
		
		json.getDdcENLabels(researchdata.getDdc());
		json.getDataOriginENLabels(researchdata.getDataOrigin());
		json.getLicenseLabelEN(researchdata.getLicense());
		json.fillLanguagesEN(researchdata.getLanguage());
		json.fillMedia(researchdata.getMedium());
		json.getFundingLabels(researchdata.getFundingId());
		json.fillSimpleObjectChilds(researchdata.getInstitution());
		json.fillSimpleObjectChilds(researchdata.getCreator());
		json.fillSimpleObjectChilds(researchdata.getContributor());
		json.fillSimpleObjectChilds(researchdata.getOther());
		json.getSubjectValue(researchdata.getSubject());
		
		researchdata.setJoinedFunding(json.fillJoinedFunding(researchdata.getFundingId(), 
				researchdata.getFundingProgram(), 
				researchdata.getProjectId()));
	}
}
