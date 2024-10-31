package de.hbz.nrw.to.science.forms.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.service.ResearchdataService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/researchdata")
public class ResearchdataController {

    private final WebClientService client;
    private final ResearchdataService researchdataService;

    public ResearchdataController(WebClientService client, ResearchdataService researchdataService) {
        this.client = client;
        this.researchdataService = researchdataService;
    }

    @GetMapping
	public String addResearchdata(Model model) {
    	Researchdata researchdata = new Researchdata();
		model.addAttribute(RESEARCHDATA, researchdata);
		return "researchdata";
	}
	
	@GetMapping("/{pid}")
	public String getResearchdata(@PathVariable String pid, Model model) {
		Researchdata researchData = client.getResearchData(pid);
		model.addAttribute(RESEARCHDATA, researchData);
		return "researchdata";
	}
	
	@PostMapping
	public Object postResearchdata(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, RedirectAttributes redirectAttributes) {		
		
		String pid = null;
		
		if(!result.hasErrors()) {
			//pid = client.createResource("researchData");
			pid="frl:65055533";
			log.info("PID_RESEARCHDATA: {}", pid);
		}
				
		return researchdataWithPid(researchdata, result, pid, redirectAttributes);
	}

	@PostMapping("/{pid}")
	public Object researchdataWithPid(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, @PathVariable String pid, RedirectAttributes redirectAttributes) {

		researchdataService.populateResearchdataFields(researchdata, pid);
        
        if(result.hasErrors()) {
			log.error("Still validation errors available");
			return "researchdata";
		}
        
        // Metadaten hochladen
 		//client.uploadMetadataResearchdata(researchdata, pid);
 		
 		redirectAttributes.addFlashAttribute("message", "Researchdata was created/updated successfully");
 	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
 	    
 	    return ResponseEntity.ok(researchdata);
 		//return "redirect:/researchdata/" + pid;
    }

}
