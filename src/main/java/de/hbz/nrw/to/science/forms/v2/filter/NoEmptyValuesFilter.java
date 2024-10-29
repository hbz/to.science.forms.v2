package de.hbz.nrw.to.science.forms.v2.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import de.hbz.nrw.to.science.forms.v2.model.ktbl.KtblData;
import de.hbz.nrw.to.science.forms.v2.model.ktbl.KtblObject;
import de.hbz.nrw.to.science.forms.v2.model.objects.JoinedFunding;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;

public class NoEmptyValuesFilter extends SimpleBeanPropertyFilter {

	@Override
	public void serializeAsField(Object pojo, JsonGenerator gen, SerializerProvider provider, PropertyWriter writer) throws Exception {
		
		// Den Wert des aktuellen Feldes abrufen
    	Object value = writer.getMember().getValue(pojo);
    	
    	// Filtere leere Strings
        if (value instanceof String && ((String) value).isEmpty()) {
            return;
        }
        
        // Filtere leere SimpleObjects
        if (value instanceof SimpleObject && (((SimpleObject) value).getId() == null || ((SimpleObject) value).getId().isEmpty())) {
            return;
        }

        if (value instanceof List) {
            List<?> list = (List<?>) value;
            
            // Filtere leere Listen
            if(list.isEmpty() || list == null) {
            	return;
            }
            
            // Filtere Listen mit leeren Strings
            if (!list.isEmpty() && list.get(0) instanceof String) {
            	List<String> filteredList= list.stream()
            			.filter(s -> !((String) s).isEmpty())
            			.map(s -> (String) s)
            			.collect(Collectors.toList());
            	if(filteredList.isEmpty()) {
            		return;
            	}
            }
            
         // Filtere Listen mit leeren SimpleObjects IDs
            if (!list.isEmpty() && list.get(0) instanceof SimpleObject) {
            	List<SimpleObject> filteredSimpleObjectList = list.stream()
                        .filter(so -> ((SimpleObject) so).getId() != null && !((SimpleObject) so).getId().isEmpty())
                        .map(obj -> (SimpleObject) obj)
                        .collect(Collectors.toList());

                if (filteredSimpleObjectList.isEmpty()) {
                    return;
                }
            }
            
            // Filtere Listen mit leeren JoinedFunding IDs
            if (!list.isEmpty() && list.get(0) instanceof JoinedFunding) {
            	List<JoinedFunding> filteredFundingList= list.stream()
            			.filter(funding -> {
		            		SimpleObject so =  ((JoinedFunding) funding).getFundingJoined();
		            		return so != null && so.getId() != null && !so.getId().isEmpty();
            			})
            			.map(funding -> (JoinedFunding) funding)
            			.collect(Collectors.toList());
            	
            	if(filteredFundingList.isEmpty()) {
            		return;
            	}
            }
           /* 
            // Filtere Listen mit leeren ArticleContribution IDs
            if (!list.isEmpty() && list.get(0) instanceof ArticleContribution) {
            	List<ArticleContribution> articleContributionList= list.stream().filter(artcont -> {
            		SimpleObject so =  ((JoinedFunding) funding).getFundingJoined();
            		return so != null && so.getId() != null && !so.getId().isEmpty();
            	}).map(funding -> (JoinedFunding) funding).collect(Collectors.toList());
            	if(filteredFundingList.isEmpty()) {
            		return;
            	}
            }
            */
        }    
        
        // Filtere das gesamte KtblObject heraus, wenn KtblData leer ist
       if(value instanceof KtblObject) {
    	   KtblObject ktblObject = (KtblObject) value;
    	   KtblData ktbl = ktblObject.getKtbl();

    	   if (ktblObject == null || ktbl == null) {
    	       return;
    	   }

    	   if (isNullOrEmpty(ktbl.getAdditionalHousingSystems()) &&
    	       isNullOrEmpty(ktbl.getHousingSystems()) &&
    	       isNullOrEmpty(ktbl.getEmissionMeasurementTechniques()) &&
    	       isNullOrEmpty(ktbl.getEmissionReductionMethods()) &&
    	       isNullOrEmpty(ktbl.getEmissions()) &&
    	       isNullOrEmpty(ktbl.getLivestockCategory()) &&
    	       isNullOrEmpty(ktbl.getLivestockProduction()) &&
    	       isNullOrEmpty(ktbl.getProjectTitle()) &&
    	       isNullOrEmpty(ktbl.getVentilationSystem()) &&
    	       isNullOrEmpty(ktbl.getTestDesign())) {
    	       return;
    	   }

        }
         

        // Wenn der Wert nicht leer ist, wird das Feld normal serialisiert
        super.serializeAsField(pojo, gen, provider, writer);
       
		
	}
	
	 private boolean isNullOrEmpty(List<String> list) {
	       return list == null || list.isEmpty() || list.get(0).isBlank();
	 }

     private boolean isNullOrEmpty(String value) {
           return value == null || value.isBlank();
     }
	
	
}
