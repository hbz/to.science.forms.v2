package de.hbz.nrw.to.science.forms.v2.service;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.RESEARCHDATA;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.properties.URLProperties;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResearchdataService {
	
	private JsonMapperService json;
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
		
		//researchdata.getRecordingCoordinates().removeIf(coordinate -> coordinate.getId().isBlank());
		json.fillRecordLocations(researchdata.getRecordingLocation());
		
		SimpleObject obj = new SimpleObject();
		obj.setId(url.getMember() + RESEARCHDATA);
		obj.setPrefLabel(obj.getId());
		researchdata.setIsMemberOf(List.of(obj));
		
		String concat = researchdata.getCreator().stream().map(CreatorObject::getId).collect(Collectors.joining(" | "));
		if(!StringUtils.isBlank(concat))
			researchdata.setContributerOrder(List.of(concat));
		
		json.getDdcLabels(researchdata.getDdc(), false);
		json.getDataOriginLabels(researchdata.getDataOrigin(), false);
		json.getLicenseLabels(researchdata.getLicense(), false);
		json.fillLanguages(researchdata.getLanguage(), false);
		json.fillMedia(researchdata.getMedium());
		json.getFundingLabels(researchdata.getFundingId());
		json.fillSimpleObjectChilds(researchdata.getInstitution(), null);
		json.fillSimpleObjectChilds(researchdata.getCreator(), null);
		json.fillSimpleObjectChilds(researchdata.getContributor(), null);
		json.fillSimpleObjectChilds(researchdata.getOther(), null);
		json.fillSimpleObjectChilds(researchdata.getSubject(), "en");
		
		researchdata.setJoinedFunding(json.fillJoinedFunding(researchdata.getFundingId(), 
				researchdata.getFundingProgram(), 
				researchdata.getProjectId()));
	}
}
