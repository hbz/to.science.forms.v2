package de.hbz.nrw.to.science.forms.v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.clientresponse.Label;
import de.hbz.nrw.to.science.forms.v2.model.forms.Monograph;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.CatalogLink;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Contribution;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.DescribedBy;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Publication;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Result;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.ResultObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.CreatorObject;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import de.hbz.nrw.to.science.forms.v2.properties.URLProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MonographService {

	private WebClientService client;
	private JsonMapperService json;
	private URLProperties url;
	
    public Monograph enrichMonographFromLobid(Monograph monograph) {
        String url = monograph.getParallelEdition().get(0).getId();
        return client.getLobidAsMonograph(url);
    }

    public Monograph enrichMonographForFRL(Monograph monograph, String pid) {
        monograph.setId(pid);
        monograph.setCatalogId(json.getCatalogId(pid));
        //monograph.setAlmaMmsIdList(List.of(monograph.getAlmaMmsId()));
        //monograph.setBibliographicLevelList(List.of(monograph.getBibliographicLevel()));
        monograph.setCatalogLink(createCatalogLink(monograph));
        
        enrichContainedInFromIsPartOf(monograph);
        enrichContributions(monograph);
        enrichCatalogLinkFromDeprecatedUri(monograph);
        enrichExtent(monograph);    
        enrichIssuedFromPublication(monograph);
        enrichAlmaMmsId(monograph);
        enrichHbzId(monograph);
        enrichLicenseFromDescribedBy(monograph);
        
        
        //enrichDescribedBy(monograph);
        
        return monograph;
    }
    
 
    
    private void enrichAlmaMmsId(Monograph monograph) {
    	if (monograph.getAlmaMmsIdLobid() != null && !monograph.getAlmaMmsIdLobid().isBlank()) {
            monograph.setAlmaMmsId(monograph.getAlmaMmsId());
        }
    }
    
    // From isPartOf to containedIn
    public void enrichContainedInFromIsPartOf(Monograph monograph) {
    	if (monograph == null) return;
        List<SimpleObject> containedIn = monograph.getIsPartOf().stream()
                .flatMap(part -> part.getHasSuperordinate().stream())
                .map(superordinate -> {
                    String cleanedId = superordinate.getId().replace("#!", "#");

                    SimpleObject obj = new SimpleObject();
                    obj.setId(cleanedId);
                    obj.setPrefLabel(superordinate.getLabel());

                    return obj;
                })
                .collect(Collectors.toList());
        monograph.setContainedIn(containedIn);
    }

    // From contribution to creator or contributors
    private void enrichContributions(Monograph monograph) {
        if (monograph == null) return;

        List<CreatorObject> creators = new ArrayList<>();
        List<CreatorObject> contributors = new ArrayList<>();

        for (Contribution contribution : monograph.getContribution()) {
            if (contribution.getRole() == null || contribution.getAgent() == null) continue;

            CreatorObject obj = new CreatorObject();
            obj.setId(contribution.getAgent().getId());
            obj.setPrefLabel(contribution.getAgent().getLabel());

            if (contribution.getRole().getLabel().equals("Autor/in")) {
                creators.add(obj);
            } else {
                contributors.add(obj);
            }
        }

        monograph.setCreator(creators);
        monograph.setContributor(contributors);
    }

    private void enrichCatalogLinkFromDeprecatedUri(Monograph monograph) {
    	if (monograph.getDeprecatedUriLobid() != null && !monograph.getDeprecatedUriLobid().isBlank()) {
            SimpleObject catalogEntry = new SimpleObject();
            catalogEntry.setId(monograph.getDeprecatedUriLobid());
            catalogEntry.setPrefLabel(""); // Was soll hier rein?
            monograph.setCatalogLink(List.of(catalogEntry));
        }
    }
    
    private void enrichExtent(Monograph monograph) {
    	if (monograph.getExtentLobid() != null && !monograph.getExtentLobid().isBlank()) {
            monograph.setExtent(List.of(monograph.getExtentLobid()));
        }
    }
    
    private void enrichIssuedFromPublication(Monograph monograph) {
    	 if (monograph.getPublicationLobid() != null && !monograph.getPublicationLobid().isEmpty()) {
             Publication publication = monograph.getPublicationLobid().get(0);
             if (publication.getStartDate() != null && !publication.getStartDate().isBlank()) {
                 monograph.setIssued(publication.getStartDate());
             }
         }
    }

    public void enrichLicenseFromDescribedBy(Monograph monograph) {
        if (monograph.getDescribedByLobid() != null) {
        	monograph.setLicense(monograph.getDescribedByLobid().getLicense());
        }
    }
    
    private void enrichHbzId(Monograph monograph) {
    	if (monograph.getHbzIdLobid() != null && !monograph.getHbzIdLobid().isBlank()) {
            monograph.setHbzId(List.of(monograph.getHbzIdLobid()));
        }
    }
    
    private List<SimpleObject> createCatalogLink(Monograph monograph) {
    	SimpleObject so = new SimpleObject();
        so.setId(url.getLobid() + monograph.getHbzId().get(0));
        so.setPrefLabel(monograph.getHbzId().get(0));
        return List.of(so);
    }
    
    
    
    // ----------------------------------------------------------------------------------
    
    
    
    
    /*
    private void enrichDescribedBy(Monograph monograph) {
        if (monograph.getDescribedByLobid() != null) {
            DescribedBy describedBy = monograph.getDescribedByLobid();
            describedBy.setInDatasetList(List.of(describedBy.getInDataset()));
            describedBy.setRdftype(createRdfType(describedBy.getType()));
            enrichResultOf(describedBy);
        }
    }
   
    private void enrichResultOf(DescribedBy describedBy) {
        Result resultOf = describedBy.getResultOf();
        ResultObject resObject = resultOf.getObject();

        resultOf.setRdftype(createRdfType(resultOf.getType()));
        resObject.setRdftype(createRdfType(resObject.getType()));
        resObject.setInDatasetAsList(List.of(resObject.getInDataset()));
        
        if (resObject.getSourceOrganization() != null) {
            resObject.setSourceOrganizationList(List.of(resObject.getSourceOrganization()));
        }

        describedBy.setResultOfList(List.of(resultOf));
    }

    private List<SimpleObject> createRdfType(List<String> types) {
        List<SimpleObject> list = new ArrayList<>();
        types.forEach(typ -> {
            log.info("Processing Label: {}", typ);
            Label label = client.getLabel("frl", typ);
            SimpleObject so = new SimpleObject();
            if (label != null && label.getJsonConf() != null) {
                so.setId(label.getJsonConf().getUri());
                so.setPrefLabel(label.getLabelStr());
            } else {
                log.warn("Label '{}' not found, needs to be added to labels API", typ);
                so.setId(null);
                so.setPrefLabel(null);
            }
            list.add(so);
        });
        return list;
    }*/
  
}
