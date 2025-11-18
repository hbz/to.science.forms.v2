package de.hbz.nrw.to.science.forms.v2.model.forms;

import java.util.List;

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
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Contribution;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.DescribedBy;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.PartOf;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
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
	
	//@JsonProperty(index = 2)
	//private List<String> isbn;
	
	private String accessScheme = "private";
	private String publishScheme = "private";
	
	private List<SimpleObject> parallelEdition;
	
	@JsonProperty(access = WRITE_ONLY, value = "almaMmsId")
	private String almaMmsIdLobid;
	private List<String> almaMmsId;
	
	//private List<String> alternative;
	
	@JsonProperty(access = WRITE_ONLY, value = "abstract")
	private List<String> abstractLobid;
	@JsonProperty(access = READ_ONLY, value = "abstractText")
	private List<String> abstractFrl;
	
	private List<String> additionalNotes;
	
	@JsonProperty(access = WRITE_ONLY, value = "bibliographicLevel")
	private SimpleObject bibliographicLevelLobid;
	@JsonProperty(access = READ_ONLY, value = "bibliographicLevel")
	private List<SimpleObject> bibliographicLevelFrl;
	
	@JsonProperty("bibo:doi")
	private List<String> doi;
	
	// Von lobid zu toscience
	@JsonProperty(access = WRITE_ONLY, value = "isPartOf")
	private List<PartOf> isPartOf;
	private List<SimpleObject> containedIn;
	
	@JsonProperty(access = WRITE_ONLY, value = "type")
	private List<String> type;
	@JsonProperty(access = READ_ONLY, value = "rdfType")
	private List<SimpleObject> rdfType; 
	
	private String catalogId;
	private String contentType = MONOGRAPH;
	
	@JsonProperty(access = WRITE_ONLY, value = "contribution")
	private List<Contribution> contribution;
	//@JsonProperty(access = READ_ONLY, value = "creator")
	private List<CreatorObject> creator;
	private List<CreatorObject> contributor;
	
	@JsonProperty(access = WRITE_ONLY, value = "deprecatedUri")
	private String deprecatedUriLobid;
	private List<SimpleObject> catalogLink;
	
	private List<String> edition;
	
	@JsonProperty(access = WRITE_ONLY, value = "extent")
	private String extentLobid;
	private List<String> extent;
	
	private List<SimpleObject> fulltextOnline;
	
	// private String hbzId; HT Nummer SimpleObject id
	
	private List<String> isbn;
	
	@JsonProperty(access = WRITE_ONLY, value = "publicationLobid")
	private List<Publication> publicationLobid; 
	private String issued;
	
	private List<SimpleObject> language;
	
	@JsonProperty(access = WRITE_ONLY, value = "describedBy")
	private DescribedBy describedByLobid;
	private List<SimpleObject> license;
	
	private List<SimpleObject> medium;
	private List<String> note;
	private List<String> otherTitleInformation;
	private List<String> responsibilityStatement;
	
	private String title;
	
	private List<String> urn; // Urn sollte in lobid vorhanden sein
	
	@JsonProperty(access = WRITE_ONLY, value = "zdbId")
	private String zdbIdLobid;  // zdbId gibt es bei Monographie nicht
	private List<String> zdbId;
	
	@JsonProperty(access = WRITE_ONLY, value = "hbzId")
	private String hbzIdLobid;
	private List<String> hbzId;
	
	// ---------------------------------------------
	
	
	
	
	//private List<Item> hasItem;
	//private List<SimpleObject> inCollection;
		
	/*private List<SimpleObject> natureOfContent;
	private List<String> note;*/
	//private List<String> oclcNumber;
	//private String primaryTopic;
	
	//@Value("${resource.publishScheme}")
	
	//private List<IsbnObject> relation;
	
	//private List<SimpleObject> sameAs;
	//private List<String> stockNumber;
	//private List<Subject> subject;
//	@JsonProperty(access = WRITE_ONLY, value = "title")
//	private String title;
//	@JsonProperty(access = READ_ONLY, value = "title")
//	private List<String> titleList;
	
	//private String zdbId;
	
}
