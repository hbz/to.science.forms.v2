package de.hbz.nrw.to.science.forms.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.hbz.nrw.to.science.forms.v2.data.FormsData;
import de.hbz.nrw.to.science.forms.v2.filter.NoEmptyValuesFilter;

@Configuration
public class JsonConfig implements WebMvcConfigurer {

	@Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("noEmptyValuesFilter", new NoEmptyValuesFilter());
        return builder.filters(filterProvider).build();
    }
	
	// Import json file
	@Bean
	FormsData formsProp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("static/data/formsdata.json");
        return mapper.readValue(resource.getInputStream(), FormsData.class);
    }

}
