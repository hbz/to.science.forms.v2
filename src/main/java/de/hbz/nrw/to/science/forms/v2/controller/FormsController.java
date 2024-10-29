package de.hbz.nrw.to.science.forms.v2.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Alessio Pellerito
 *
 */

@Controller
@Data
public class FormsController {

	/**
	 * 
	 * GET ALL FORMS LIST
	 * 
	 */
	
	@GetMapping("/")
	public String getForms() {
			return "forms";
	}


	
}

