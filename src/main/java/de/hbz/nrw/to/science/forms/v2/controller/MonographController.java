package de.hbz.nrw.to.science.forms.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.model.forms.Monograph;
import de.hbz.nrw.to.science.forms.v2.service.MonographService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/monograph")
public class MonographController {

    private final WebClientService client;
    private final MonographService monographService;
    private final FormsData formsData;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
    	model.addAttribute("monographData", formsData.getMonographData());
    }

    @GetMapping
	public String getMonograph(Model model) {
    	Monograph monograph = new Monograph();
		model.addAttribute(MONOGRAPH, monograph);
		return "monograph";	
	}
	
	@GetMapping("/{pid}")
	public String getMonograph(@PathVariable String pid, Model model) {
		Monograph monograph = client.getMonograph(pid);
		model.addAttribute(MONOGRAPH, monograph);
		return "monograph";
	}
	
	@PostMapping
	public Object postMonograph(@ModelAttribute Monograph monograph, RedirectAttributes redirectAttributes) {		
		
		String pid = client.createResource(MONOGRAPH);
		//String pid="frl:65050050"; // to test
		log.info("PID_MONOGRAPH: {}", pid);
		
		return postMonographWithPid(monograph, pid, redirectAttributes);
		
	}
	
	@PostMapping("/{pid}")
	public Object postMonographWithPid(@ModelAttribute Monograph monograph, @PathVariable String pid, RedirectAttributes redirectAttributes) {
		
		// VON LOBID HOLEN
	    monograph = monographService.enrichMonographFromLobid(monograph);

	    // ANREICHERN FÜR FRL
	    monograph = monographService.enrichMonographForFRL(monograph, pid);

	    // METADATEN HOCHLADEN
	    client.uploadMetadataMonograph(monograph, pid);

	    redirectAttributes.addFlashAttribute("message", "Monograph was created/updated successfully");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");

	    //return ResponseEntity.ok(monograph); // to test
	    return "redirect:/monograph/" + pid;
	    
	}

}
