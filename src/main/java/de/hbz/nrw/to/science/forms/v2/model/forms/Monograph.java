package de.hbz.nrw.to.science.forms.v2.model.forms;

import java.util.ArrayList;
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
import com.fasterxml.jackson.annotation.JsonSetter;
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
 * @author Hasan Adoud
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
	private List<String> hbzId;

	@JsonSetter("extent")
	public void setExtentFromJson(Object extentValue) {
		if (extentValue == null) {
			this.extent = null;
			return;
		}
		if (extentValue instanceof String s) {
			this.extent = List.of(s);
			return;
		}
		if (extentValue instanceof List<?> list) {
			List<String> normalized = new ArrayList<>();
			for (Object item : list) {
				if (item != null) {
					normalized.add(item.toString());
				}
			}
			this.extent = normalized.isEmpty() ? null : normalized;
			return;
		}
		this.extent = List.of(extentValue.toString());
	}

	@JsonSetter("title")
	public void setTitleFromJson(Object titleValue) {
		if (titleValue == null) {
			this.title = null;
			return;
		}
		if (titleValue instanceof String s) {
			this.title = s;
			return;
		}
		if (titleValue instanceof List<?> list) {
			for (Object item : list) {
				if (item != null && !item.toString().isBlank()) {
					this.title = item.toString();
					return;
				}
			}
			this.title = null;
			return;
		}
		this.title = titleValue.toString();
	}

	@JsonSetter("hbzId")
	public void setHbzIdFromJson(Object hbzIdValue) {
		if (hbzIdValue == null) {
			this.hbzId = null;
			return;
		}
		if (hbzIdValue instanceof String s) {
			this.hbzId = List.of(s);
			return;
		}
		if (hbzIdValue instanceof List<?> list) {
			List<String> normalized = new ArrayList<>();
			for (Object item : list) {
				if (item != null) {
					normalized.add(item.toString());
				}
			}
			this.hbzId = normalized.isEmpty() ? null : normalized;
			return;
		}
		this.hbzId = List.of(hbzIdValue.toString());
	}
	
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
