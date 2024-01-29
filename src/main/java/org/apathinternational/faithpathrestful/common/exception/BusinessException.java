package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class BusinessException extends ApplicationException {
    private final int CODE = Constants.ERROR_CODE_BUSINESS_EXCEIPTION;

    public BusinessException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    }
}
