package de.hbz.nrw.to.science.forms.v2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.forms.Article;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {
	
	private JsonMapperService json;

	public void populateArticleFields(Article article, String pid) {
	    if (pid != null) {
	        article.setId(pid);
	        article.setCatalogId(json.getCatalogId(pid));
	        article.setPrimaryTopic(pid);
	        article.setItemID(json.fillItemId(pid));
	        article.setIsPrimaryTopicOf(json.fillPrimaryTopicOf(pid));
	    }

	    String concat = article.getCreator().stream()
	        .map(CreatorObject::getId)
	        .collect(Collectors.joining(" | "));
	    
	    if (!StringUtils.isBlank(concat)) {
	        article.setContributerOrder(List.of(concat));
	    }

	    // Weitere Felder befüllen
	    json.getDdcLabels(article.getDdc(), true);
	    json.fillLanguages(article.getLanguage(), true);
	    json.fillCollections(article.getCollectionOne());
	    json.fillCollections(article.getCollectionTwo());
	    json.getFundingLabels(article.getFundingId());
	    json.fillRdfType(article.getRdftype());
	    json.fillPublicationStatus(article.getPublicationStatus());
	    json.fillReviewStatus(article.getReviewStatus());
	    json.fillSimpleObjectChilds(article.getInstitution(), null);
	    json.fillSimpleObjectChilds(article.getCreator(), null);
	    json.fillSimpleObjectChilds(article.getContributor(), null);
	    json.fillSimpleObjectChilds(article.getEditor(), null);
	    json.fillSimpleObjectChilds(article.getOther(), null);
	    json.fillSimpleObjectChilds(article.getContainedIn(), null);
	    json.fillSimpleObjectChilds(article.getSubject(), "de");
	    
	    article.setJoinedFunding(json.fillJoinedFunding(article.getFundingId(), 
	                                                    article.getFundingProgram(), 
	                                                    article.getProjectId()));
	}
}
