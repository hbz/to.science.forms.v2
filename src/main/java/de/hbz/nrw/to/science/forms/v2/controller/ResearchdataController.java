package de.hbz.nrw.to.science.forms.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.properties.ResourceProperties;
import de.hbz.nrw.to.science.forms.v2.service.ResearchdataService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

import jakarta.validation.Valid;
import java.beans.PropertyEditorSupport;

/**
 * @author Alessio Pellerito
 * @author Hasan Adoud
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping({"/researchdata/", "/researchdata"})
public class ResearchdataController {

    private final WebClientService client;
    private final ResearchdataService researchdataService;
    private final FormsData formsData;
    private final ResourceProperties link;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("researchdataData", formsData.getResearchdataData());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(SimpleObject.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null);
                    return;
                }
                SimpleObject so = new SimpleObject();
                so.setId(text);
                so.setPrefLabel(text);
                setValue(so);
            }
        });
    }

    @GetMapping({"/", ""})
	public String addResearchdata(@RequestParam(value = "drupalUserId", required = false) String drupalUserId, @RequestParam(value = "drupalToken", required = false) String drupalToken, Model model) {
		if (missingDrupalContext(drupalUserId, drupalToken)) {
			return drupalFormsV2StartUrl("researchData");
		}
    	Researchdata researchdata = new Researchdata();
		model.addAttribute("pid", "");
        model.addAttribute("drupalUserId", drupalUserId);
        model.addAttribute("drupalToken", drupalToken);
		model.addAttribute(RESEARCHDATA, researchdata);
		return "researchdata";
	}
	
	@GetMapping({"/{pid}/", "/{pid}"})
	public String getResearchdata(@PathVariable String pid, @RequestParam(value = "drupalUserId", required = false) String drupalUserId, @RequestParam(value = "drupalToken", required = false) String drupalToken, Model model) {
		Researchdata researchData = client.getResearchData(pid);
		model.addAttribute("pid", pid);
        model.addAttribute("drupalUserId", drupalUserId);
        model.addAttribute("drupalToken", drupalToken);
		model.addAttribute(RESEARCHDATA, researchData);
		return "researchdata";
	}
	
	@PostMapping({"/", ""})
	public Object postResearchdata(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, @RequestParam("drupalUserId") String drupalUserId, @RequestParam("drupalToken") String drupalToken, RedirectAttributes redirectAttributes) {
		
		String pid = null;
		
		if(!result.hasErrors()) {
			pid = client.createResourceViaDrupal("researchData", drupalUserId, drupalToken);
			//pid="frl:65055533"; // to test
			log.info("PID_RESEARCHDATA: {}", pid);
		}
				
		return researchdataWithPid(researchdata, result, pid, redirectAttributes);
	}

	@PostMapping({"/{pid}/", "/{pid}"})
	public Object researchdataWithPid(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, @PathVariable String pid, RedirectAttributes redirectAttributes) {

		researchdataService.populateResearchdataFields(researchdata, pid);
        
        if(result.hasErrors()) {
			log.error("Still validation errors available");
			return "researchdata";
		}
        
        // Metadaten hochladen
 		client.uploadMetadataResearchdata(researchdata, pid);
 		
 		redirectAttributes.addFlashAttribute("message", "Researchdata was created/updated successfully");
 	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
 	    
 	    //return ResponseEntity.ok(researchdata); // to test
 	   return "redirect:" + link.getFrlUrl() + "resource/" + pid;
    }

    private boolean missingDrupalContext(String drupalUserId, String drupalToken) {
        return drupalUserId == null || drupalUserId.isBlank() || drupalToken == null || drupalToken.isBlank();
    }

    private String drupalFormsV2StartUrl(String bundleType) {
        String frlUrl = link.getFrlUrl();
        return "redirect:" + (frlUrl.endsWith("/") ? frlUrl : frlUrl + "/") + "edoweb/forms-v2/start/" + bundleType;
    }
}
