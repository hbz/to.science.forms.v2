package de.hbz.nrw.to.science.forms.v2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.hbz.nrw.to.science.forms.v2.model.clientresponse.Label;
import de.hbz.nrw.to.science.forms.v2.model.forms.Monograph;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Agent;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.CatalogLink;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.DescribedBy;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.Result;
import de.hbz.nrw.to.science.forms.v2.model.objects.monograph.ResultObject;
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
	private CatalogLink catalogLink;
	
    public Monograph enrichMonographFromLobid(Monograph monograph) {
        String url = monograph.getParallelEdition().get(0).getId();
        return client.getLobidAsMonograph(url);
    }

    public Monograph enrichMonographForFRL(Monograph monograph, String pid) {
        monograph.setId(pid);
        monograph.setCatalogId(json.getCatalogId(pid));
        //monograph.setAlmaMmsIdList(List.of(monograph.getAlmaMmsId()));
        monograph.setBibliographicLevelList(List.of(monograph.getBibliographicLevel()));
        monograph.setCatalogLink(createCatalogLink(monograph));
        
        enrichContribution(monograph);
        enrichDeprecatedUri(monograph);
        enrichDescribedBy(monograph);
        enrichHasItem(monograph);
        enrichOtherFields(monograph);
        
        return monograph;
    }

    private void enrichContribution(Monograph monograph) {
        if (monograph.getContribution() != null) {
            monograph.getContribution().forEach(contribution -> {
                Agent agent = contribution.getAgent();
                SimpleObject role = contribution.getRole();
                
                List<SimpleObject> agentTypeList = createRdfType(agent.getType());
                agent.setRdftype(agentTypeList);
                contribution.setAgentList(List.of(agent));
                contribution.setRoleList(List.of(role));
                contribution.setRdftype(createRdfType(contribution.getType()));
            });
        }
    }

    private void enrichDeprecatedUri(Monograph monograph) {
        if (monograph.getDeprecatedUri() != null) {
            monograph.setDeprecatedUriList(List.of(monograph.getDeprecatedUri()));
        }
    }

    private void enrichDescribedBy(Monograph monograph) {
        if (monograph.getDescribedBy() != null) {
            DescribedBy describedBy = monograph.getDescribedBy();
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

    private void enrichHasItem(Monograph monograph) {
        if (monograph.getHasItem() != null) {
            monograph.getHasItem().forEach(item -> {
                item.setHeldByList(List.of(item.getHeldBy()));
                item.setRdftype(createRdfType(item.getType()));
            });
        }
    }

    private void enrichOtherFields(Monograph monograph) {
        if (monograph.getHbzId() != null) {
            monograph.setHbzIdList(List.of(monograph.getHbzId()));
        }

        if (monograph.getInCollection() != null) {
            monograph.getInCollection().get(0)
                     .setRdftype(createRdfType(monograph.getInCollection().get(0).getType()));
        }

        if (monograph.getDescribedBy() != null) {
            SimpleObject sim = new SimpleObject();
            sim.setId(monograph.getDescribedBy().getId());
            sim.setLabel(monograph.getDescribedBy().getId());
            monograph.setParallelEdition(List.of(sim));
        }

        monograph.setPrimaryTopic(monograph.getId());

        if (monograph.getPublication() != null) {
            monograph.getPublication().get(0)
                     .setRdftype(createRdfType(monograph.getPublication().get(0).getType()));
        }

        if (monograph.getTitle() != null) {
            monograph.setTitleList(List.of(monograph.getTitle()));
        }

        if (monograph.getType() != null) {
            monograph.setRdfType(createRdfType(monograph.getType()));
        }
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
    }
    
    private List<CatalogLink> createCatalogLink(Monograph monograph) {
        catalogLink.setId(url.getLobid() + monograph.getHbzId());
        catalogLink.setPrefLabel(monograph.getHbzId());
        return List.of(catalogLink);
    }
}
