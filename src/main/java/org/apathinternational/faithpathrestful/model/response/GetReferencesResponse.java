package org.apathinternational.faithpathrestful.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apathinternational.faithpathrestful.entity.Reference;
import org.apathinternational.faithpathrestful.model.entityDTO.ReferenceDTO;

public class GetReferencesResponse {
    private Map<String, List<ReferenceDTO>> referencesByType;

    public GetReferencesResponse() {
    }

    public GetReferencesResponse(List<Reference> references) {
        // create referencesByType from references
        referencesByType = new HashMap<String, List<ReferenceDTO>>();
        for (Reference reference : references) {
            String referenceType = reference.getReferenceType();
            if (!referencesByType.containsKey(referenceType)) {
                referencesByType.put(referenceType, new ArrayList<ReferenceDTO>());
            }

            referencesByType.get(referenceType).add(new ReferenceDTO(reference));
        }
    }

    public GetReferencesResponse(Map<String, List<ReferenceDTO>> referencesByType) {
        this.referencesByType = referencesByType;
    }

    public Map<String, List<ReferenceDTO>> getReferencesByType() {
        return referencesByType;
    }
}
