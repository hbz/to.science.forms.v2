package de.hbz.nrw.to.science.forms.v2.service;

import java.io.File;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

import de.hbz.nrw.to.science.forms.v2.model.clientresponse.Label;
import de.hbz.nrw.to.science.forms.v2.model.clientresponse.ResponseObject;
import de.hbz.nrw.to.science.forms.v2.model.forms.Article;
import de.hbz.nrw.to.science.forms.v2.model.forms.Monograph;
import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.model.parent.ToScienceObject;
import de.hbz.nrw.to.science.forms.v2.properties.ResourceProperties;
import de.hbz.nrw.to.science.forms.v2.properties.URLProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This client class manages all the different requests
 * @author Alessio Pellerito
 *
 */
@Slf4j
@Component
@Data
@AllArgsConstructor
public class WebClientService {
	
	private URLProperties urlTo;
	private WebClient webClient;
	private ResourceProperties props;
	private ToScienceObject attr;
	private ObjectMapper mapper;
	
	/**
	 * 
	 * Creates a new sub Resource under the specified parent resource
	 *  
	 * @param type the type of the Resource
	 * @param parentPid the identifier of the parent resource
	 * @return name of the new created child Resource
	 */
	public String createChildResource(String type, String parentPid) {
		attr.setContentType(type);
		attr.setParentPid(parentPid);
		attr.setAccessScheme(props.getAccessScheme());
		attr.setPublishScheme(props.getPublishScheme());
		ResponseObject response = webClient.post() 
								 .uri(props.getFrlUrl() + "frl")
								 .contentType(MediaType.APPLICATION_JSON)
								 .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
								 .bodyValue(attr)
								 .retrieve()
				                 .bodyToMono(ResponseObject.class)   
				                 .block();
		log.info("Response text: {}", response.getText());
		return response.getText().split(" ")[0];
	}
	
	/**
	 * 
	 * Creates a new Resource
	 * 
	 * @param type the type of the Resource
	 * @return name of the new created Ressource
	 */
	public String createResource(String type) {
		return createChildResource(type, null);
	}
	
	/**
	 * 
	 * Sends the Article Object to frl
	 * to the rdf format and then send it to the specified frl ressource.
	 * 
	 * @param articleObj
	 */
	public void uploadMetadataArticle(Article articleObj, String resourcePid) {
		uploadMetadata(articleObj, resourcePid, ARTICLE);
		/*try {
			File file = new File(tmpFile);
			mapper.writeValue(file, articleObj);
			
			MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
			bodyBuilder.part("data", new FileSystemResource(file));
			bodyBuilder.part("type", MediaType.APPLICATION_JSON);
			webClient.put()
			         .uri(props.getFrlDevUrl() + resourcePid + "/uploadUpdateMetadata")
			         .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
			         .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
			         .retrieve()
			         .toBodilessEntity()
			         .block();
		}
		catch (Exception e) {
			log.error("Uploading Metadata of Article failed");
			e.printStackTrace();
		}*/
	}
	
	public void uploadMetadataResearchdata(Researchdata researchdataObj, String resourcePid) {
		uploadMetadata(researchdataObj, resourcePid, RESEARCHDATA);
		/*try {
			File file = new File(tmpFile);
			mapper.writeValue(file, researchdataObj);
			
			MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
			bodyBuilder.part("data", new FileSystemResource(file));
			bodyBuilder.part("type", MediaType.APPLICATION_JSON);
			webClient.put()
			         .uri(props.getFrlDevUrl() + resourcePid + "/uploadUpdateMetadata")
			         .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
			         .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
			         .retrieve()
			         .toBodilessEntity()
			         .block();
		}
		catch (Exception e) {
			log.error("Uploading Metadata of Researchdata failed");
			e.printStackTrace();
		}*/
	}
	
	public void uploadMetadataMonograph(Monograph monographObj, String resourcePid) {
		uploadMetadata(monographObj, resourcePid, MONOGRAPH);
		/*try {
			File file = new File(tmpFile);
			mapper.writeValue(file, monographObj);
			
			MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
			bodyBuilder.part("data", new FileSystemResource(file));
			bodyBuilder.part("type", MediaType.APPLICATION_JSON);
			webClient.put()
			         .uri(props.getFrlDevUrl() + resourcePid + "/uploadUpdateMetadata")
			         .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
			         .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
			         .retrieve()
			         .toBodilessEntity()
			         .block();
		}
		catch (Exception e) {
			log.error("Uploading Metadata of Monograph failed");
		}*/
	}
	
	public void uploadMetadata(Object metadataObj, String resourcePid, String metadataType) {
	    try {
	        File file = new File(props.getTmpFile());
	        mapper.writeValue(file, metadataObj);

	        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
	        bodyBuilder.part("data", new FileSystemResource(file));
	        bodyBuilder.part("type", MediaType.APPLICATION_JSON);
	        
	        webClient.put()
	                 .uri(props.getFrlUrl() + resourcePid + "/uploadUpdateMetadata")
	                 .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
	                 .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
	                 .retrieve()
	                 .toBodilessEntity()
	                 .block();
	    } catch (Exception e) {
	        log.error("Uploading Metadata of " + metadataType + " failed", e);
	    }
	}

	
	/**
	 * 		LOBID-RESOURCES
	 */
	
	/**
	 * Gets the lobid resource mapped to Monograph
	 * 
	 * @param url url of the resource
	 * @return Monograph lobid ressource mapped to Monograph
	 */
	public Monograph getLobidAsMonograph(String url) {
		url.replace("#!", "");
		String num = url.substring(url.lastIndexOf("/")+1);
		return webClient.get()
						.uri(urlTo.getLobid() + num, uriBuilder -> uriBuilder
							.queryParam("format", "json")
							.build() )
						.retrieve()
				        .bodyToMono(Monograph.class)   
				        .block();
	}
	
	
	/**
	 * Gets only the title of the lobid resource
	 * 
	 * @param url url of the resource
	 * @return String label of the specific ressource
	 */
	public String getLobidLabel(String url) {
		Monograph monograph = getLobidAsMonograph(url);
		return monograph.getTitle(); 
	
	}
	
	
	/**
	 * 		LOBID-GND
	 */
	
	public String getLobidName(String id) {
		JsonNode node = webClient.get()
				 				 .uri(urlTo.getLobidGnd() + id + ".json")
				 				 .retrieve()
				 				 .bodyToMono(JsonNode.class)   
				 				 .block();
		JsonNode name = node.at("/preferredName");
		return name != null ? name.asText() : "Name does not exist";
	} 
	
	public String getOrcidName(String orcidId) {
		JsonNode node = webClient.get()
				 				 .uri(urlTo.getOrcidApi() + orcidId + "/personal-details")
				 				 .accept(MediaType.APPLICATION_JSON)
				 				 .retrieve()
				 				 .bodyToMono(JsonNode.class)   
				 				 .block();

		String familyName = node.at("/name/family-name/value").asText();
		String givenNames = node.at("/name/given-names/value").asText();
		return familyName == null ? givenNames : familyName + ", " + givenNames; 
	}
	
	public String getFunderLabel(String doiUrl) {
		log.info("DoiUrl: {}", urlTo.getFunding() + doiUrl);
		JsonNode node = webClient.get()
								 .uri(urlTo.getFunding() + doiUrl)
								 .accept(MediaType.APPLICATION_JSON)
								 .retrieve()
								 .bodyToMono(JsonNode.class)
								 .block();
		JsonNode label = node.at("/prefLabel/Label/literalForm/content");
		return label != null ? label.asText() : "No Label found";
	}
	
	public String getSubjectLabel(String url) {	
		JsonNode node = webClient.get()
								 .uri(urlTo.getAgrovocData(), uriBuilder -> uriBuilder
										.queryParam("uri", url)
										.queryParam("format", "application/ld+json")
										.build() )
								.retrieve()
				                .bodyToMono(JsonNode.class)   
				                .block();
		JsonNode label = node.at("/graph/4/prefLabel/3/value");
		return label != null ? label.asText() : "No Label found";
	}

//	public Article getArticle(String pid) {
//		return webClient.get()
//				 		.uri(props.getFrlDevUrlFedora() + pid + "/datastreams/toscience/content")
//				 		.headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
//				 		.retrieve()
//				 		.bodyToMono(Article.class)   
//				 		.block();
//	}
//	
//	public Researchdata getResearchData(String pid) {
//		return webClient.get()
//				 		.uri(props.getFrlDevUrlFedora() + pid + "/datastreams/toscience/content")
//				 		.headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
//				 		.retrieve()
//				 		.bodyToMono(Researchdata.class)   
//				 		.block();
//	}
//	
//	public Researchdata getKtbl(String pid) {
//		return webClient.get()
//				 		.uri(props.getFrlDevUrlFedora() + pid + "/datastreams/ktbl/content")
//				 		.headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
//				 		.retrieve()
//				 		.bodyToMono(Researchdata.class)   
//				 		.block();
//	}
//	
//	public Monograph getMonograph(String pid) {
//		return webClient.get()
//				 		.uri(props.getFrlDevUrlFedora() + pid + "/datastreams/toscience/content")
//				 		.headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
//				 		.retrieve()
//				 		.bodyToMono(Monograph.class)   
//				 		.block();
//	}
	
	public <T> T getData(String pid, String datastream, Class<T> clazz) {
	    return webClient.get()
	                    .uri(props.getFrlUrlFedora() + pid + "/datastreams/" + datastream + "/content")
	                    .headers(h -> h.setBasicAuth(props.getApiUser(), props.getApiPassword()))
	                    .retrieve()
	                    .bodyToMono(clazz)
	                    .block();
	}
	
	public Article getArticle(String pid) {
	    return getData(pid, "toscience", Article.class);
	}

	public Researchdata getResearchData(String pid) {
	    return getData(pid, "toscience", Researchdata.class);
	}

	public Researchdata getKtbl(String pid) {
	    return getData(pid, "ktbl", Researchdata.class);
	}

	public Monograph getMonograph(String pid) {
	    return getData(pid, "toscience", Monograph.class);
	}
	
	public String getNameFromAdhocUri(String encodedVal) {
		Document doc = webClient.get()
				 .uri(urlTo.getVagrant() + encodedVal)
				 .retrieve()
                 .bodyToMono(Document.class)   
                 .block();
		
		String name = doc.getElementsByTagName("prefLabel").item(0).getTextContent();
		return name;
	}
	
	public Label getLabel(String group, String name) {
		return webClient
				.get()
			    .uri(urlTo.getFindLabels(), uriBuilder -> uriBuilder
					.queryParam("group", group)
					.queryParam("name", name)
					.build() )
			    .retrieve()
			    .bodyToMono(Label.class)   
			    .block();
	}
	
}
