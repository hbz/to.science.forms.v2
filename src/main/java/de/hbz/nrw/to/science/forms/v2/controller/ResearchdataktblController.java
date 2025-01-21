package de.hbz.nrw.to.science.forms.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.service.ResearchdataService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/researchdataktbl")
public class ResearchdataktblController {

    private final WebClientService client;
    private final ResearchdataService researchdataService;
    private final FormsData formsData;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("researchdataData", formsData.getResearchdataData());
    }

    @GetMapping
	public String addResearchdataKtbl(Model model) {
    	Researchdata researchdata = new Researchdata();
		model.addAttribute(RESEARCHDATA, researchdata);
		return "researchdata-ktbl";
	}
	
	@GetMapping("/{pid}")
	public String getResearchdataKtbl(@PathVariable String pid, Model model) {
		Researchdata researchData = client.getResearchData(pid);
		Researchdata ktbl = client.getKtbl(pid);
		researchData.setInfo(ktbl.getInfo());
		model.addAttribute(RESEARCHDATA, researchData);
		return "researchdata-ktbl";
	}
	
	@PostMapping
	public Object postResearchdataKtbl(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, RedirectAttributes redirectAttributes) {		
		
		String pid = null;
		
		if(!result.hasErrors()) {
			pid = client.createResource("researchData");
			//pid="frl:65050050"; // to test
			log.info("PID_RESEARCHDATA_KTBL: {}", pid);
		}
				
		return researchdataKtblWithPid(researchdata, result, pid, redirectAttributes);
	}

	@PostMapping("/{pid}")
	public Object researchdataKtblWithPid(@Valid @ModelAttribute Researchdata researchdata, BindingResult result, @PathVariable String pid, RedirectAttributes redirectAttributes) {		

		researchdataService.populateResearchdataFields(researchdata, pid);
        
        if(result.hasErrors()) {
			log.error("Still validation errors available");
			return "researchdata-ktbl";
		}
        
        // Metadaten hochladen
 		client.uploadMetadataResearchdata(researchdata, pid);
 		
 		redirectAttributes.addFlashAttribute("message", "Researchdata with KTBL was created/updated successfully");
 	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
 	    
 	    //return ResponseEntity.ok(researchdata); // to test
 	   return "redirect:/researchdataktbl/" + pid;
    }

}
