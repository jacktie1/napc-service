package org.apathinternational.faithpathrestful.model.request;

import java.util.List;

public class GetReferencesRequest {
    private List<String> referenceTypes;

    public GetReferencesRequest() {
    }

    public GetReferencesRequest(List<String> referenceTypes) {
        this.referenceTypes = referenceTypes;
    }

    public List<String> getReferenceTypes() {
        return referenceTypes;
    }

    public void setReferenceTypes(List<String> referenceTypes) {
        this.referenceTypes = referenceTypes;
    }
}
