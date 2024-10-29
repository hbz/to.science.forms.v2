package de.hbz.nrw.to.science.forms.v2.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import de.hbz.nrw.to.science.forms.v2.properties.URLProperties;
import de.hbz.nrw.to.science.forms.v2.util.URLUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AutoCompletionController {

	@Autowired
	private WebClient.Builder webClient;
	
	@Autowired
	private URLProperties urlProp;
	
	@Autowired
	private ObjectMapper mapper;

	/**
	 * @param q the query will be redirected to geonames
	 * @return the response from api.geonames.org
	 */
	@GetMapping("/researchdata/geoSearch")
	@ResponseBody 
	public ResponseEntity<JsonNode> geoSearch(@RequestParam(required = false) String q) {
		JsonNode response = webClient.build()
			.get()
			.uri(urlProp.getGeoSearch(), uriBuilder -> uriBuilder
					.queryParam("q", q)
				 	.queryParam("username", "epublishinghbz")
				 	.build())
			.retrieve()
			.bodyToMono(JsonNode.class)
			.timeout(Duration.ofMillis(5000))
			.onErrorResume(throwable -> {
                // Fallback-Answer
                ObjectNode fallbackResponse = mapper.createObjectNode();
                fallbackResponse.put("status", "pending");
                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
                return Mono.just(fallbackResponse);
            })
            .block();
		return ResponseEntity.ok(response);
	}

	/**
	 * @param q a query string to find an orcid entry
	 * @return the orcid response as json
	 */
	
	@ResponseBody
	public ResponseEntity<JsonNode> orcidSearch(String q) {
		JsonNode response = webClient.build()
			.get()
			.uri(urlProp.getOrcidSearch(), uriBuilder -> uriBuilder
					.queryParam("q", q)
				 	.build())
			.retrieve()
			.bodyToMono(JsonNode.class)
			.timeout(Duration.ofMillis(5000))
			.onErrorResume(throwable -> {
                // Fallback-Answer
                ObjectNode fallbackResponse = mapper.createObjectNode();
                fallbackResponse.put("status", "pending");
                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
                return Mono.just(fallbackResponse);
            })
            .block();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/orcidAutocomplete")
	@ResponseBody
	public ResponseEntity<String> orcidAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");//.get("callback");
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getOrcidSearch(), uriBuilder -> uriBuilder
						.queryParam("q", "family-name:" + q + " OR given-names:" + q + " OR orcid:" + q)
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		JsonNode hits = response.at("/expanded-result");
		List<Map<String, String>> result = new ArrayList<>();
		hits.forEach((hit) -> {

			  String id = hit.at("/orcid-id").asText();
				String lastName = hit.at("/family-names").asText();
				String firstName = hit.at("/given-names").asText();
				Map<String, String> m = new HashMap<>();
				m.put("label", lastName + ", " + firstName + " | " + id);
				m.put("value", "https://orcid.org/" + id);
				result.add(m);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
		
	}
	
	@GetMapping("/agrovocAutocomplete")
	@ResponseBody
	public ResponseEntity<String> agrovocAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getAgrovocSearch(), uriBuilder -> uriBuilder
						.queryParam("query", q + "*")
						.queryParam("vocab", "agrovoc")
						.queryParam("lang", "de")
						.queryParam("labellang", "de")
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		JsonNode hits = response.at("/results");
		List<Map<String, String>> result = new ArrayList<>();
		hits.forEach((hit) -> {

			String preflabel = hit.at("/prefLabel").asText();
			String id = hit.at("/uri").asText();
			Map<String, String> m = new HashMap<>();
			m.put("label", preflabel);
			m.put("value", id);
			result.add(m);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
		
	}
	
	@GetMapping("/agrovocAutocompleteEN")
	@ResponseBody
	public ResponseEntity<String> agrovocAutocompleteEN(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getAgrovocSearch(), uriBuilder -> uriBuilder
						.queryParam("query", q + "*")
						.queryParam("vocab", "agrovoc")
						.queryParam("lang", "en")
						.queryParam("labellang", "en")
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		JsonNode hits = response.at("/results");
		List<Map<String, String>> result = new ArrayList<>();
		hits.forEach((hit) -> {

			String preflabel = hit.at("/prefLabel").asText();
			String id = hit.at("/uri").asText();
			Map<String, String> m = new HashMap<>();
			m.put("label", preflabel);
			m.put("value", id);
			result.add(m);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
		
	}

	@GetMapping("/bookAutocomplete")
	@ResponseBody
	public ResponseEntity<String> bookAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		return typedAutocomplete(q, "Book", request);
	}

	@GetMapping("/seriesAutocomplete")
	@ResponseBody
	public ResponseEntity<String> seriesAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		return typedAutocomplete(q, "Series", request);
	}

	@GetMapping("/journalAutocomplete")
	@ResponseBody
	public ResponseEntity<String> journalAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		return typedAutocomplete(q, "Periodical", request);
	}

	@GetMapping("/allAutocomplete")
	@ResponseBody
	public ResponseEntity<String> allAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		return typedAutocomplete(q, "*", request);
	}
	
	@ResponseBody
	public ResponseEntity<String> typedAutocomplete(String q, String type, HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getLobidSearch(), uriBuilder -> uriBuilder
						.queryParam("q", q + " AND type:" + type)
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		JsonNode hits = response.at("/member");
		List<Map<String, String>> result = new ArrayList<>();
		hits.forEach((hit) -> {
			String title = hit.at("/title").asText();
			StringBuilder label = new StringBuilder(title);
			label.append(createLabel(hit));
			String id = hit.at("/id").asText();
			Map<String, String> m = new HashMap<>();
			m.put("label", label.toString());
			m.put("value", id);
			result.add(m);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);

	}

	private String createLabel(JsonNode hit) {
		StringBuilder label = new StringBuilder();
		String issn = "";

		JsonNode issns = hit.at("/issn");
		if (issns != null) {
			StringBuffer issn_c = new StringBuffer();
			issns.forEach((issn_i) -> {
				issn_c.append(issn_i.asText() + ",");
			});
			if (issn_c.length() != 0) {
				issn = issn_c.substring(0, issn_c.length() - 1);
				if (issn != null && !issn.isEmpty())
					label.insert(0, issn + " - ");
			}
		}
		JsonNode publisher = hit.at("/publication");
		if (publisher != null) {
			String val = publisher.get(0) != null ? publisher.get(0).at("/publishedBy").asText() : "";
			label.append(" - Hrsg.: " + val);
		}

		return label.toString();
	}
	
	@GetMapping("/localAutocomplete")
	@ResponseBody
	public ResponseEntity<String> localAutocomplete(String q, HttpServletRequest request) {

		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> suggestThisAsNewEntry = new HashMap<>();
		suggestThisAsNewEntry.put("label", q);
		suggestThisAsNewEntry.put("value", "http://localhost:8080" + "/adhoc/uri/" + URLUtils.encode(q));
		result.add(suggestThisAsNewEntry);

		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	// Mit Spring
	@GetMapping("/subjectAutocomplete")
	@ResponseBody
	public ResponseEntity<String> subjectAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		String filter = "type:SubjectHeadingSensoStricto";
		return lobidResponse(q, filter, request);
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	// Mit Spring
	@GetMapping("/personAutocomplete")
	@ResponseBody
	public ResponseEntity<String> personAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		String filter = "type:Person";
		return lobidResponse(q, filter, request);
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	// Mit Spring
	@GetMapping("/corporateBodyAutocomplete")
	@ResponseBody
	public ResponseEntity<String> corporateBodyAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		String filter = "type:CorporateBody";
		return lobidResponse(q, filter, request);
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	@GetMapping("/conferenceAutocomplete")
	@ResponseBody
	public ResponseEntity<String> conferenceAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		String filter = "(type:ConferenceOrEvent)OR(type:SeriesOfConferenceOrEvent)";
		return lobidResponse(q, filter, request);
	}

	@ResponseBody
	private ResponseEntity<String> lobidResponse(String q, String filter, HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getLobidGndSearch(), uriBuilder -> uriBuilder
						.queryParam("q", q)
						.queryParam("format", "json:suggest")
						.queryParam("filter", filter)
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
			
		log.trace("GET " + request.getRequestURI() + request.getQueryString());
		List<Map<String, String>> result = new ArrayList<>();
		response.forEach((m) -> {
			StringBuffer label = new StringBuffer();
			label.append(m.at("/label"));
			String id = m.at("/id").asText();
			Map<String, String> map = new HashMap<>();
			label.append(" | " + getGndNumber(id));
			map.put("label", label.toString());
			map.put("value", id);
			result.add(map);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
	}

	private static String getGndNumber(String id) {
		try {
			return Iterables.getLast(Splitter.on("/").split(id));
		} catch (NoSuchElementException e) {
			return "";
		}
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	@GetMapping("/lobidAutocomplete")
	@ResponseBody
	public ResponseEntity<String> lobidAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		//String queryString = "hbzId:"+q+"* almaMmsId:"+q+"* zdbId:"+q+"*";
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getLobidSearch(), uriBuilder -> uriBuilder
						.queryParam("q", q)
						.queryParam("format", "json")
					 	.build())
				.header("accept", "application/json")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		List<Map<String, String>> result = new ArrayList<>();
		JsonNode member = response.at("/member");
		member.forEach((m) -> {
			String uri = m.at("/id").asText().replaceAll("#!", "");
			// Ermittle ID
			String id = " ";
			try {
				// Es wird immer die lobid Ressource-ID angezeigt
				String[] parts = uri.split("/");
				id = parts[parts.length -1];
			} catch (Exception e) {
				log.debug("uri {} enthält keinen \"/\", also auch keine ID.", uri);
			}
			// "Herausfiltern" von unerwünschten (!) IDs:
			if(!id.startsWith("RPB")) {
				StringBuffer label = new StringBuffer();
				label.append(id);
				label.append(" - ");
				JsonNode prefName = m.at("/title");
				if (prefName.isArray()) {
					prefName.forEach((p) -> {
						label.append(p.asText() + ",");
					});
					label.deleteCharAt(label.length() - 1);
				} else {
					label.append(prefName.asText());
				}
				Map<String, String> map = new HashMap<>();
				map.put("label", label.toString());
				map.put("value", uri);
				result.add(map);
			}
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
	}

	/**
	 * @param q a query against lobid
	 * @return a jsonp result
	 */
	// Mit Spring
	@GetMapping("/crossrefAutocomplete")
	@ResponseBody
	public ResponseEntity<String> crossrefAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		String queryString = q;
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getFundingApi(), uriBuilder -> uriBuilder
						.queryParam("query", queryString)
					 	.build())
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		List<Map<String, String>> result = new ArrayList<>();
		JsonNode member = response.at("/message/items");
		member.forEach((m) -> {
			StringBuffer label = new StringBuffer();
			label.append(m.at("/name").asText());
			StringBuffer altNamesString = new StringBuffer();

			JsonNode altNames = m.at("/alt-names");
			if (altNames.isArray()) {
				altNames.forEach((p) -> {
					altNamesString.append(p.asText() + ",");
				});
			} else {
				altNamesString.append(altNames.asText());
			}

			String id = m.at("/uri").asText().replaceAll("#!", "");
			Map<String, String> map = new HashMap<>();
			map.put("label", label.toString());
			map.put("value", id);
			map.put("desc", altNamesString.toString());
			result.add(map);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);
	}

	// Mit Spring
	@GetMapping("/conferenceAlephAutocomplete")
	@ResponseBody
	public ResponseEntity<String> conferenceAlephAutocomplete(
			@RequestParam(required = false) String q, 
			HttpServletRequest request) {
		final String[] callback =
				request == null || request.getQueryString() == null ? null
						: request.getParameterValues("callback");
		String queryString = q;
		
		JsonNode response = webClient.build()
				.get()
				.uri(urlProp.getLobidSearch(), uriBuilder -> uriBuilder
						.queryParam("q", queryString)
						.queryParam("format", "json")
						.queryParam("t", "Proceedings")
					 	.build())
				.retrieve()
				.bodyToMono(JsonNode.class)
				.timeout(Duration.ofMillis(5000))
				.onErrorResume(throwable -> {
	                // Fallback-Answer
	                ObjectNode fallbackResponse = mapper.createObjectNode();
	                fallbackResponse.put("status", "pending");
	                fallbackResponse.put("message", "The request is taking longer than expected. Please wait a moment.");
	                return Mono.just(fallbackResponse);
	            })
	            .block();
		
		List<Map<String, String>> result = new ArrayList<>();
		JsonNode member = response.at("/member");
		member.forEach((m) -> {
			StringBuffer label = new StringBuffer();
			label.append(m.at("/hbzId").asText());
			label.append(" - ");
			JsonNode prefName = m.at("/title");
			if (prefName.isArray()) {
				prefName.forEach((p) -> {
					label.append(p.asText() + ",");
				});
				label.deleteCharAt(label.length() - 1);
			} else {
				label.append(prefName.asText());
			}

			String id = m.at("/id").asText().replaceAll("#!", "");
			Map<String, String> map = new HashMap<>();
			map.put("label", label.toString());
			map.put("value", id);
			result.add(map);
		});
		String searchResult = objectToString(result);
		String myResponse = callback != null
				? String.format("/**/%s(%s)", callback[0], searchResult)
				: searchResult;
		return ResponseEntity.ok(myResponse);

	}
	
	private String objectToString(Object object) {
		try {
			return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
					.writeValueAsString(object);
		} catch (Exception e) {
			return "To String failed " + e.getMessage();
		}
	}
	
}
