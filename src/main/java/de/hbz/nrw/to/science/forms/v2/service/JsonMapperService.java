package de.hbz.nrw.to.science.forms.v2.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.model.objects.Item;
import de.hbz.nrw.to.science.forms.v2.model.objects.JoinedFunding;
import de.hbz.nrw.to.science.forms.v2.model.objects.PrimaryTopicOf;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.util.URLUtils;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.ARTICLE;
import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.FILE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Data
public class JsonMapperService {
	
	private ApplicationContext applicationContext;
	private WebClientService client;
	private FormsData formsData;

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
	
	// RdfType, ReviewStatus, Medium
	public void fillSimpleObjects(List<SimpleObject> objects, Function<String, String> labelSupplier) {
	    objects.forEach(obj -> obj.setPrefLabel(labelSupplier.apply(obj.getId())));
	    objects.removeIf(obj -> StringUtils.isBlank(obj.getId()));
	}
	
	public void fillRdfType(List<SimpleObject> rdfs) {
	    fillSimpleObjects(rdfs, id -> formsData.getArticleData().getRdftype().get(id));
	}

	public void fillReviewStatus(List<SimpleObject> reviews) {
	    fillSimpleObjects(reviews, id -> formsData.getArticleData().getReviewStatus().get(id));
	}

	public void fillMedia(List<SimpleObject> media) {
	    fillSimpleObjects(media, id -> formsData.getResearchdataData().getMedium().get(id));
	}

	// Collections, RecordingLocations
	public void processSimpleObjects(List<SimpleObject> objects, Function<String, String> idProcessor) {
	    objects.forEach(obj -> {
	        if (StringUtils.isBlank(obj.getId()))
	            return;
	        String id = StringUtils.substringAfterLast(obj.getId(), "/");
	        obj.setPrefLabel(idProcessor.apply(id));
	    });
	    objects.removeIf(obj -> StringUtils.isBlank(obj.getId()));
	}
	
	public void fillCollections(List<SimpleObject> collections) {
	    processSimpleObjects(collections, id -> client.getLobidName(id));
	}

	public void fillRecordLocations(List<SimpleObject> recordLocations) {
	    processSimpleObjects(recordLocations, id -> client.getLocationById(id));
	}

	// Funding
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
	
	// Language
	public void fillLanguageLabels(List<SimpleObject> languages, Map<String, String> languageMap) {
	    languages.forEach(lang -> {
	        String label = languageMap.get(lang.getId());
	        if (label != null) {
	            lang.setPrefLabel(label);
	        }
	    });
	}
	
	public void fillLanguages(List<SimpleObject> languages, boolean isArticle) {
        Map<String, String> languageMap = isArticle 
            ? formsData.getArticleData().getLanguage() 
            : formsData.getResearchdataData().getLanguage();
        fillLanguageLabels(languages, languageMap);
    }
	
	// DDC, DataOrigin, License
	public void processLabels(List<SimpleObject> objectList, Map<String, String> propertyMap, boolean isDdc) {
        objectList.forEach(obj -> {
            String subject = propertyMap.get(obj.getId());
            if (subject != null) {
                String label = isDdc ? StringUtils.substringBeforeLast(subject, " ") : subject;
                obj.setPrefLabel(label);
            }
        });
        objectList.removeIf(obj -> StringUtils.isBlank(obj.getId()));
    }

    public void getDdcLabels(List<SimpleObject> ddcList, boolean isArticle) {
        Map<String, String> ddcMap = isArticle 
            ? formsData.getArticleData().getDdc() 
            : formsData.getResearchdataData().getDdc();
        processLabels(ddcList, ddcMap, true);
    }

    public void getDataOriginLabels(List<SimpleObject> dataOriginList, boolean isArticle) {
        Map<String, String> dataOriginMap = isArticle 
            ? formsData.getArticleData().getDataOrigin() 
            : formsData.getResearchdataData().getDataOrigin();
        processLabels(dataOriginList, dataOriginMap, false);
    }

    public void getLicenseLabels(List<SimpleObject> licenseList, boolean isArticle) {
        Map<String, String> licenseMap = isArticle 
            ? formsData.getArticleData().getLicense() 
            : formsData.getResearchdataData().getLicense();
        // remove all after first "(", i.e. (recommended)
        licenseMap.replaceAll((key, value) -> {
            int index = value.indexOf("(");
            return index != -1 ? value.substring(0, index).trim() : value;
        });
        processLabels(licenseList, licenseMap, false);
    }
    
    // PublicationStatus
    public void fillPublicationStatus(List<Publication> status) {
		status.forEach(stat -> stat.setPrefLabel(formsData.getArticleData().getPublicationStatus().get(stat.getId())));
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
	
	// itemId
	public List<Item> fillItemId(String articleId) {
		List<Item> items = new ArrayList<>();
		String itemId = "oai:api.localhost.de:" + articleId;
		Item obj = applicationContext.getBean(Item.class);
		obj.setId(itemId);
		obj.setPrefLabel(itemId);
		items.add(obj);
		return items;
	}
	
	// joinedFunding
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
	
}
