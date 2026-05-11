package de.hbz.nrw.to.science.forms.v2.model.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

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

import de.hbz.nrw.to.science.forms.v2.model.ktbl.KtblObject;
import de.hbz.nrw.to.science.forms.v2.model.objects.Institution;
import de.hbz.nrw.to.science.forms.v2.model.objects.Item;
import de.hbz.nrw.to.science.forms.v2.model.objects.JoinedFunding;
import de.hbz.nrw.to.science.forms.v2.model.objects.PrimaryTopicOf;
import de.hbz.nrw.to.science.forms.v2.model.objects.Subject;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.validator.AtLeastOne;
import de.hbz.nrw.to.science.forms.v2.validator.FieldNotEmpty;
import de.hbz.nrw.to.science.forms.v2.validator.NotBothFieldsEmpty;
import de.hbz.nrw.to.science.forms.v2.validator.ValidAssociatedDatasetUrls;
import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.RESEARCHDATA;
import lombok.Data;

/**
 * @author Alessio Pellerito
 * @author Hasan Adoud
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
@Data
@Component
@JsonFilter("noEmptyValuesFilter")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NotBothFieldsEmpty(first="creator[0].id", 
					second="contributor[0].id", 
					field1="Creator",
					field2="Contributor",
					message="{not.both.fields.empty.en}")
@ValidAssociatedDatasetUrls(message = "Please fill in the field \"Related data publication\" with a valid URL!")
public class Researchdata {
	
	/***************** Systemvariablen *******************/
	
	//@Value("${url.to.context}")
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
	private String contentType = RESEARCHDATA;
	private List<String> prefLabel;
	
	/***************** Systemvariablen ENDE *******************/
	
	/***************** Formularvariablen *******************/
	// Title data
	@Valid
	private List<@FieldNotEmpty(value = "Title", message = "{field.not.empty.en}") String> title;
	private List<String> alternative;
	private List<String> edition;
	
	// Creatorship data
	@Valid
	private List<CreatorObject> creator;
	@Valid
	private List<CreatorObject> contributor;
	private List<CreatorObject> other;
	
	private List<String> contributerOrder = new ArrayList<>();
	//private List<SimpleObject> hasPart; // Woher kommen die hasPart Daten?
	// Source data
	@Valid
	@FieldNotEmpty(value = "Publicationyear",message = "{field.not.empty.en}")
	private String issued;
	
	// Collection Assignment
	private List<Institution> institution;
	
	// File data
	//private List<String> copyrightYear;
	private List<String> embargoTime;
	private List<SimpleObject> medium;
	private List<String> yearOfCopyright;
	@Valid
	@JsonProperty("license")
	private List<@FieldNotEmpty(value = "License",message = "{field.not.empty.en}")SimpleObject> license;
	
	// Content data
	@JsonProperty("language")
	private List<SimpleObject> language;
	private List<String> description;
	private List<String> usageManual;
	
	@Valid
	@JsonProperty("ddc")
	private List<@AtLeastOne(value="DDC",message = "{at.least.one.en}") SimpleObject> ddc;
	private List<Subject> subject;
	
	// Funder data
	private List<SimpleObject> fundingId;
	private List<String> projectId;
	private List<String> fundingProgram;
	
	// Acquisition
	@JsonProperty("dataOrigin")
	private List<SimpleObject> dataOrigin;
	private List<String> recordingPeriod;
	private List<SimpleObject> recordingLocation;
	//private List<SimpleObject> recordingCoordinates;
	
	// // Publications
	private List<String> reference;
	private List<String> associatedPublication;
	private List<SimpleObject> associatedDataset;
	private List<SimpleObject> additionalMaterial;
	private List<String> nextVersion;
	private List<String> previousVersion;
	
	// Identifiers
	private List<String> urn;
	private List<String> doi;
	private List<SimpleObject> isLike;
	
	//ktbl associated variables for ResearchDataForm 
	private KtblObject info;
	
	//private IsDescribedBy isDescribedBy;

	// Angereicherte Werte
	private List<PrimaryTopicOf> isPrimaryTopicOf;
	private List<SimpleObject> isMemberOf;
	private List<Item> itemID;
	private List<JoinedFunding> joinedFunding;
	private String primaryTopic;
	private List<SimpleObject> rdftype;
	private List<SimpleObject> relatedDatasets;
	
	/***************** Formularvariablen ENDE *******************/

	@JsonProperty("additionalMaterial")
    public void setAdditionalMaterialByType(List<?> values) {
        if (values == null) {
            this.additionalMaterial = null;
            return;
        }
        List<SimpleObject> result = new ArrayList<>();
        for (Object item : values) {
            if (item instanceof String) {
                SimpleObject so = new SimpleObject();
                so.setId((String) item);
                so.setPrefLabel((String) item);
                result.add(so);
            } else if (item instanceof SimpleObject) {
                result.add((SimpleObject) item);
            } else if (item instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) item;
                Object id = map.get("@id");
                if (id == null) {
                    id = map.get("id");
                }
                Object prefLabel = map.get("prefLabel");
                if (id != null || prefLabel != null) {
                    SimpleObject so = new SimpleObject();
                    String value = id != null ? id.toString() : prefLabel.toString();
                    so.setId(value);
                    so.setPrefLabel(prefLabel != null ? prefLabel.toString() : value);
                    result.add(so);
                }
            }
        }
        this.additionalMaterial = result;
    }
	
}
