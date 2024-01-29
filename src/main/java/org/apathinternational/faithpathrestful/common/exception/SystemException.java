package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class SystemException extends ApplicationException {
    private final int CODE = Constants.ERROR_CODE_SYSTEM_EXCEPTION;

    public SystemException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    }
    
}
