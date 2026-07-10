package de.hbz.nrw.to.science.forms.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.model.forms.Monograph;
import de.hbz.nrw.to.science.forms.v2.properties.ResourceProperties;
import de.hbz.nrw.to.science.forms.v2.service.MonographService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;


/**
 * @author Alessio Pellerito
 * @author Hasan Adoud
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping({"/monograph/", "/monograph"})
public class MonographController {

    private final WebClientService client;
    private final MonographService monographService;
    private final FormsData formsData;
    private final ResourceProperties link;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
    	model.addAttribute("monographData", formsData.getMonographData());
    }

    @GetMapping({"/", ""})
	public String getMonograph(@RequestParam(value = "drupalUserId", required = false) String drupalUserId, @RequestParam(value = "drupalToken", required = false) String drupalToken, Model model) {
		if (missingDrupalContext(drupalUserId, drupalToken)) {
			return drupalFormsV2StartUrl("monograph");
		}
    	Monograph monograph = new Monograph();
    	model.addAttribute("pid", "");
        model.addAttribute("drupalUserId", drupalUserId);
        model.addAttribute("drupalToken", drupalToken);
		model.addAttribute(MONOGRAPH, monograph);
		return "monograph";	
	}
	
	@GetMapping({"/{pid}/", "/{pid}"})
	public String getMonograph(@PathVariable String pid, @RequestParam(value = "drupalUserId", required = false) String drupalUserId, @RequestParam(value = "drupalToken", required = false) String drupalToken, Model model) {
		Monograph monograph = client.getMonograph(pid);
		model.addAttribute("pid", pid);
        model.addAttribute("drupalUserId", drupalUserId);
        model.addAttribute("drupalToken", drupalToken);
		model.addAttribute(MONOGRAPH, monograph);
		return "monograph";
	}
	
	@PostMapping({"/", ""})
	public Object postMonograph(@ModelAttribute Monograph monograph, @RequestParam("drupalUserId") String drupalUserId, @RequestParam("drupalToken") String drupalToken, RedirectAttributes redirectAttributes) {
		
		String pid = client.createResourceViaDrupal(MONOGRAPH, drupalUserId, drupalToken); // prod
		//String pid="frl:65050050"; // testing
		log.info("PID_MONOGRAPH: {}", pid);
		
		return postMonographWithPid(monograph, pid, redirectAttributes);
		
	}
	
	@PostMapping({"/{pid}/", "/{pid}"})
	public Object postMonographWithPid(@ModelAttribute Monograph monograph, @PathVariable String pid, RedirectAttributes redirectAttributes) {
		try {
			String lobidMetadata = monographService.getRawMonographFromLobid(monograph);
			client.uploadMetadataMonographJson(lobidMetadata, pid);

			redirectAttributes.addFlashAttribute("message", "Monograph was created/updated successfully");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");

			//return ResponseEntity.ok(monograph); // testing
			return "redirect:" + link.getFrlUrl() + "resource/" + pid;
		} catch (Exception e) {
			log.error("Monograph import failed for pid {}", pid, e);
			redirectAttributes.addFlashAttribute("message", e.getMessage() != null ? e.getMessage() : "Monograph-Import fehlgeschlagen.");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/monograph";
		}
	}

    private boolean missingDrupalContext(String drupalUserId, String drupalToken) {
        return drupalUserId == null || drupalUserId.isBlank() || drupalToken == null || drupalToken.isBlank();
    }

    private String drupalFormsV2StartUrl(String bundleType) {
        String frlUrl = link.getFrlUrl();
        return "redirect:" + (frlUrl.endsWith("/") ? frlUrl : frlUrl + "/") + "edoweb/forms-v2/start/" + bundleType;
    }
}
