package de.hbz.nrw.to.science.forms.v2.model.forms;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.hbz.nrw.to.science.forms.v2.model.objects.*;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.ArticleContribution;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.validator.AtLeastOne;
import de.hbz.nrw.to.science.forms.v2.validator.FieldNotEmpty;
import de.hbz.nrw.to.science.forms.v2.validator.NotBothFieldsEmpty;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.ARTICLE;
import lombok.Data;

/**
 * @author Alessio Pellerito
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Data
@Component
@JsonFilter("noEmptyValuesFilter")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NotBothFieldsEmpty(first = "creator[0].id", second = "contributor[0].id", field1 = "Autor/in", field2 = "Mitwirkende/r", message = "{not.both.fields.empty.de}")
public class Article {
	
	/***************** Systemvariablen *******************/
	
	//@Value("${url.to.context:'default'}")
	//@JsonProperty(value = "@context", index = 1)
	@JsonIgnore
	private String context = "https://api.localhost.de/context.json";
	
	@JsonProperty(value = "@id", index = 1)
	private String id;
	
	//@Value("${resource.accessScheme}")
	private String accessScheme = "private";
	
	//@Value("${resource.publishScheme}")
	private String publishScheme = "private";
	
	private String catalogId;
	private String contentType = ARTICLE;
	
	//private List<SimpleObject> hasPart;
	//private List<SimpleObject> isMemberOf;
	//private List<String> prefLabel; 
	private String primaryTopic;
	//private List<String> transformer;
	/***************** Systemvariablen ENDE *******************/
	
	/***************** Formularvariablen *******************/
	
	// Angaben zur Publikation
	@Valid
	private List<@AtLeastOne(value = "Typ", message = "{at.least.one.de}") SimpleObject> rdftype;
	@Valid
	private List<@FieldNotEmpty(value = "Publikationsstatus", message="{field.not.empty.de}") Publication> publicationStatus;
	private List<SimpleObject> reviewStatus;
	
	// Angaben zum Titel
	@Valid
	private List<@FieldNotEmpty(value = "Titel", message = "{field.not.empty.de}") String> title;
	private List<String> alternative;
	
	// Angaben zur Urheberschaft
	@Valid
	private List<CreatorObject> creator;
	@Valid
	private List<CreatorObject> contributor;
	private List<CreatorObject> editor;
	private List<CreatorObject> other;
	
	private List<String> contributerOrder;
	
	// Angaben zur Quelle
	@Valid
	private List<@AtLeastOne(value = "Erschienen als", message = "{at.least.one.de}") SimpleObject> containedIn;
	private List<String> bibliographicCitation;
	private List<String> issued;
	@Valid
	private List<@FieldNotEmpty(value = "Erscheinungsjahr", message = "{field.not.empty.de}") String> publicationYear;
	
	// Zuordung zur Sammlung
	private List<Institution> institution;
	private List<SimpleObject> collectionTwo;
	private List<SimpleObject> collectionOne;
	
	//private DescribedBy isDescribedBy;
	private List<PrimaryTopicOf> isPrimaryTopicOf;
	
	private List<Item> itemID;
	
	// Angaben zur Datei
	private List<String> yearOfCopyright;
	private List<SimpleObject> license;
	private List<String> embargoTime;
	
	// Angaben zum Inhalt
	private List<String> abstractText;
	@Valid
	private List<@FieldNotEmpty(value = "Sprache der Publikation", message = "{field.not.empty.de}") SimpleObject> language;
	@Valid
	private List<@AtLeastOne(value = "Fächerklassifikation (DDC)", message = "{at.least.one.de}") SimpleObject> ddc;
	private List<Subject> subject;
	
	// Externe und interne Referenzen
	private List<SimpleObject> publisherVersion;
	private List<String> fulltextVersion;
	private List<String> additionalMaterial;
	private List<String> internalReference;
	
	// Angaben zur Förderung
	private List<SimpleObject> fundingId;
	private List<String> fundingProgram;
	private List<String> projectId;
	
	private List<JoinedFunding> joinedFunding;
	@JsonProperty(value = "contribution")
	private List<ArticleContribution> contribution;
	
	// Erweiterte Angaben
	private List<String> additionalNotes;
	
	/***************** Formularvariablen ENDE *******************/ 

}
