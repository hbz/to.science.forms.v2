package de.hbz.nrw.to.science.forms.v2.service;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.objects.Item;
import de.hbz.nrw.to.science.forms.v2.model.objects.JoinedFunding;
import de.hbz.nrw.to.science.forms.v2.model.objects.PrimaryTopicOf;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.properties.ArticleProperties;
import de.hbz.nrw.to.science.forms.v2.properties.ResearchdataProperties;
import de.hbz.nrw.to.science.forms.v2.util.URLUtils;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.ARTICLE;
import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.FILE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Data
public class JsonMapperService {
	
	@Autowired 
	private ApplicationContext applicationContext;

	@Autowired
	private WebClientService client;
	
	@Autowired
	private ArticleProperties articleProp;
	
	@Autowired
	private ResearchdataProperties researchdataProp;

	public String getResourceId() {
		return client.createResource(ARTICLE);
	}
	
	public String getChildResourceId(String nameOfResource) {
		return client.createChildResource(FILE, nameOfResource);
	}
	
	public String getCatalogId(String nameOfFrl) {
		return "ED" + StringUtils.substringAfterLast(nameOfFrl, ":");
	}
	
	public String getLobidLabel(String almaMmsId) {
		return client.getLobidLabel(almaMmsId);
	}
	
	public String getName(String orcidId) {
		return client.getOrcidName(orcidId);
	}
	
	public void fillSimpleObjectChilds(List<? extends SimpleObject> objects, String language) {
		Map<String, Function<String, String>> providers = Map.of(
		        "orcid.org", id -> client.getOrcidName(id),
		        "adhoc/uri", URLUtils::decode,
		        "d-nb.info", id -> client.getLobidName(id),
		        "lobid.org", id -> client.getLobidLabel(id),
		        "aims.fao.org", id -> client.getSubjectLabel(id, language)
		    );

		    objects.forEach(object -> {
		        String id = object.getId();
		        if (StringUtils.isBlank(id)) return;

		        String key = providers.keySet()
		                                    .stream()
		                                    .filter(id::contains)
		                                    .findFirst()
		                                    .orElse(null);

		        if (key != null) {
		            String processedId = key.equals("lobid.org") || key.equals("aims.fao.org") ? id : StringUtils.substringAfterLast(id, "/");
		            object.setPrefLabel(providers.get(key).apply(processedId));
		        }
		    });
	    objects.removeIf(cre -> StringUtils.isBlank(cre.getId()));
	}
	
	public void fillRdfType(List<SimpleObject> rdfs) {
		rdfs.forEach(rdf -> rdf.setPrefLabel(articleProp.getRdftype().get(rdf.getId())));
		rdfs.removeIf(rd -> StringUtils.isBlank(rd.getId()));
	}
	
	public void fillReviewStatus(List<SimpleObject> reviews) {
		reviews.forEach(review -> {
			review.setPrefLabel(articleProp.getReviewStatus().get(review.getId()));		
		});
		reviews.removeIf(review -> StringUtils.isBlank(review.getId()));
	}
	
	public void fillMedia(List<SimpleObject> media) {
		media.forEach(med -> med.setPrefLabel(researchdataProp.getMedium().get(med.getId())));
		media.removeIf(medi -> StringUtils.isBlank(medi.getId()));
}
	
	public void fillCollections(List<SimpleObject> collections) {
		collections.forEach(col -> {
				if(StringUtils.isBlank(col.getId()))
					return;
				String id = StringUtils.substringAfterLast(col.getId(), "/");
				col.setPrefLabel(client.getLobidName(id));	
		});
		collections.removeIf(col -> StringUtils.isBlank(col.getId()));
	}
	
	public void getFundingLabels(List<SimpleObject> funders) {
		funders.forEach(fund -> {
			if(StringUtils.isBlank(fund.getId()))
				return;
			int index = StringUtils.ordinalIndexOf(fund.getId(), "/", 3);
			String id = fund.getId().substring(index + 1);
			if(fund.getId().contains("adhoc/uri"))
				fund.setPrefLabel(URLUtils.decode(StringUtils.substringAfterLast(id, "/")));
			else
				fund.setPrefLabel(client.getFunderLabel(id));

		});
		funders.removeIf(fund -> StringUtils.isBlank(fund.getId()));
	}
	
	public void getLabels(List<SimpleObject> objectList, Map<String, String> propertyMap, boolean isDdc) {
	    objectList.forEach(obj -> {
	        String subject = propertyMap.get(obj.getId());
	        if (isDdc) {
	            String label = StringUtils.substringBeforeLast(subject, " ");
	            obj.setPrefLabel(label);
	        } else {
	            obj.setPrefLabel(subject);
	        }
	    });
	    objectList.removeIf(obj -> StringUtils.isBlank(obj.getId()));
	}
	
	public void getDdcLabels(List<SimpleObject> ddcList) {
		getLabels(ddcList, articleProp.getDdc(), true);
	}
	
	public void getDdcENLabels(List<SimpleObject> ddcList) {
		getLabels(ddcList, researchdataProp.getDdc(), true);
	}

	public void getDataOriginENLabels(List<SimpleObject> dataOriginList) {
	    getLabels(dataOriginList, researchdataProp.getDataOrigin(), false);
	}
	
	public void getDataOriginLabels(List<SimpleObject> dataOriginList) {
	    getLabels(dataOriginList, articleProp.getDataOrigin(), false);
	}

	public void getLicenseLabel(List<SimpleObject> licenseList) {
	    getLabels(licenseList, articleProp.getLicense(), false);
	}
	
	public void getLicenseLabelEN(List<SimpleObject> licenseList) {
	    getLabels(licenseList, researchdataProp.getLicense(), false);
	}
	
	public void fillLanguages(List<SimpleObject> languages) {
		languages.forEach(lang -> lang.setPrefLabel(articleProp.getLanguage().get(lang.getId())));		
	}
	
	public void fillLanguagesEN(List<SimpleObject> languages) {
		languages.forEach(lang -> lang.setPrefLabel(researchdataProp.getLanguage().get(lang.getId())));		
	}

	
	// isPrimaryTopicOf
	public List<PrimaryTopicOf> fillPrimaryTopicOf(String articleId) {
		List<PrimaryTopicOf> primaries = new ArrayList<>(); 
		PrimaryTopicOf prim = applicationContext.getBean(PrimaryTopicOf.class);
		prim.setId("http://localhost:8080" + "/resource/" + articleId + "/edit");
		prim.setPrefLabel("http://localhost:8080" + "/resource/" + articleId + "/edit");
		prim.setPrimaryTopic(articleId);
		primaries.add(prim);
		return primaries;
		
	}
	
	public List<Item> fillItemId(String articleId) {
		List<Item> items = new ArrayList<>();
		String itemId = "oai:api.localhost.de:" + articleId;
		Item obj = applicationContext.getBean(Item.class);
		obj.setId(itemId);
		obj.setPrefLabel(itemId);
		items.add(obj);
		return items;
	}
	
	public List<JoinedFunding> fillJoinedFunding(List<SimpleObject> funders, List<String> fundProgram, List<String> projects) {
		List<JoinedFunding> join = new ArrayList<>();
		for(int i = 0; i < funders.size(); i++) {
			JoinedFunding obj = applicationContext.getBean(JoinedFunding.class);
			obj.setFundingJoined(funders.get(i));
			if(!fundProgram.isEmpty())
				obj.setFundingProgramJoined(fundProgram.get(i));
			if(!projects.isEmpty())
				obj.setProjectIdJoined(projects.get(i));
			join.add(obj);
		}
		return join;
	}
		
	public void fillPublicationStatus(List<Publication> status) {
		status.forEach(stat -> stat.setPrefLabel(articleProp.getPublicationStatus().get(stat.getId())));
	}
	
}
