package de.hbz.nrw.to.science.forms.v2.model.forms;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import de.hbz.nrw.to.science.forms.v2.model.objects.Item;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.CatalogLink;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Contribution;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.DescribedBy;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.PartOf;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.MONOGRAPH;
import lombok.Data;

/**
 * @author Alessio Pellerito
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Component
@Data
@JsonFilter("noEmptyValuesFilter")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Monograph {
	
	//@Value("${url.to.context}")
	//@JsonProperty(value = "@context", index = 1)
	//private String context;
	
	@JsonProperty(value = "@id", index = 1)
	@JsonAlias("id")
	private String id;
	
	@JsonProperty(index = 2)
	private List<String> isbn;
	
	private String accessScheme = "private";
	
	@JsonProperty(access = WRITE_ONLY, value = "almaMmsId")
	private String almaMmsId;	
	@JsonProperty(access = READ_ONLY, value = "almaMmsId")
	private List<String> almaMmsIdList;
	
	//private List<String> alternative;
	
	@JsonProperty(access = WRITE_ONLY, value = "bibliographicLevel")
	private SimpleObject bibliographicLevel;
	@JsonProperty(access = READ_ONLY, value = "bibliographicLevel")
	private List<SimpleObject> bibliographicLevelList;
	
	private String catalogId;
	private List<CatalogLink> catalogLink;
	private String contentType = MONOGRAPH;
	private List<Contribution> contribution;
	
	//private List<CreatorObject> creator;
	
	@JsonProperty(access = WRITE_ONLY, value = "deprecatedUri")
	private String deprecatedUri;
	@JsonProperty(access = READ_ONLY, value = "deprecatedUri")
	private List<String> deprecatedUriList;
	
	private DescribedBy describedBy;
	//private String dnbId;
	//private List<String> edition;
	//private List<SimpleObject> exampleOfWork;
	
	@JsonProperty(access = WRITE_ONLY, value = "extent")
	private String extent;
	@JsonProperty(access = READ_ONLY, value = "extent")
	private List<String> extentList;
	
	//private List<SimpleObject> fulltextOnline;
	private List<Item> hasItem;
	//private List<SimpleObject> hasPart;
	@JsonProperty(access = WRITE_ONLY, value = "hbzId")
	private String hbzId;
	@JsonProperty(access = READ_ONLY, value = "hbzId")
	private List<String> hbzIdList;
	private List<SimpleObject> inCollection;
	//private List<IsDescribedBy> isDescribedBy;
	//private List<SimpleObject> isMemberOf;
	//private List<Item> itemID;
	//private List<String> langNote;
	
	@Valid
	private List<SimpleObject> language;
	
	@JsonProperty(value = "lv:isPartOf")
	@JsonAlias("isPartOf")
	private List<PartOf> isPartOf;
	
	private List<SimpleObject> medium;
	/*private List<SimpleObject> natureOfContent;
	private List<String> note;*/
	private List<String> oclcNumber;
	private List<String> otherTitleInformation;
	private List<SimpleObject> parallelEdition;
	private String primaryTopic;
	private List<Publication> publication; 
	
	//@Value("${resource.publishScheme}")
	private String publishScheme = "private";
	//private List<IsbnObject> relation;
	private List<String> responsibilityStatement;
	private List<SimpleObject> sameAs;
	//private List<String> stockNumber;
	//private List<Subject> subject;
	@JsonProperty(access = WRITE_ONLY, value = "title")
	private String title;
	@JsonProperty(access = READ_ONLY, value = "title")
	private List<String> titleList;
	
	@JsonProperty(access = WRITE_ONLY, value = "type")
	private List<String> type;
	@JsonProperty(access = READ_ONLY, value = "rdfType")
	private List<SimpleObject> rdfType;
	//private String zdbId;
	
}
