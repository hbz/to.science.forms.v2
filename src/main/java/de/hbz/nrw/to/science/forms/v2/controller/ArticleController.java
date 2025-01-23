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
import de.hbz.nrw.to.science.forms.v2.model.forms.Article;
import de.hbz.nrw.to.science.forms.v2.properties.ResourceProperties;
import de.hbz.nrw.to.science.forms.v2.service.ArticleService;
import de.hbz.nrw.to.science.forms.v2.service.WebClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static de.hbz.nrw.to.science.forms.v2.constants.ContentType.*;

import javax.validation.Valid;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final WebClientService client;
    private final ArticleService articleService;
    private final FormsData formsData;
    private final ResourceProperties link;
    
    @ModelAttribute
    public void addCommonAttributes(Model model) {
    	model.addAttribute("articleData", formsData.getArticleData());
    }

    @GetMapping
    public String addArticle(Model model) {
    	Article article = new Article();
    	model.addAttribute("articleData", formsData.getArticleData());
        model.addAttribute(ARTICLE, article);
        return "article";
    }

    @GetMapping("/{pid}")
    public String getArticle(@PathVariable String pid, Model model) {
        Article fetchedArticle = client.getArticle(pid);
        model.addAttribute("articleData", formsData.getArticleData());
        model.addAttribute(ARTICLE, fetchedArticle);
        return "article";
    }

    @PostMapping
    public Object postArticle(@Valid @ModelAttribute Article article, BindingResult result, RedirectAttributes redirectAttributes) {
      
    	String pid = null;
		
		if(!result.hasErrors()) {
			pid = client.createResource(ARTICLE);
			//pid="frl:65050050"; // to test
			log.info("PID_ARTICLE: {}", pid);
		}	
		
		return postArticleWithPid(article, result, pid,redirectAttributes);
    }

    @PostMapping("/{pid}")
    public Object postArticleWithPid(@Valid @ModelAttribute Article article, BindingResult result, @PathVariable String pid, RedirectAttributes redirectAttributes) {

        articleService.populateArticleFields(article, pid);
        
        if(result.hasErrors()) {
			log.error("Still validation errors available");
			return "article";
		}
        
        // Metadaten hochladen
 		client.uploadMetadataArticle(article, pid);
 		
 		redirectAttributes.addFlashAttribute("message", "Article was created/updated successfully");
 	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
 	    
 	    //return ResponseEntity.ok(article); // to test
 		//return "redirect:/article/" + pid;
 	    return "redirect:" + link.getFrlUrl() + "resource/" + pid;
    }

}
