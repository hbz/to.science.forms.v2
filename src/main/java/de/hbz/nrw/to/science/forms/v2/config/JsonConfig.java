package de.hbz.nrw.to.science.forms.v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import de.hbz.nrw.to.science.forms.v2.filter.NoEmptyValuesFilter;

@Configuration
public class JsonConfig implements WebMvcConfigurer {

	@Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        // Erstelle einen SimpleFilterProvider und füge den CustomFilter hinzu
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("noEmptyValuesFilter", new NoEmptyValuesFilter());

        // Baue den ObjectMapper mit dem FilterProvider
        return builder.filters(filterProvider).build();
    }

}
