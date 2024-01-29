package org.apathinternational.faithpathrestful.common.exception;

import org.apathinternational.faithpathrestful.common.util.Constants;

public class DatabaseException extends SystemException {
    private final int CODE = Constants.ERROR_CODE_DATABASE_EXCEPTION;

    public DatabaseException(String message) {
        super(message);
    }

    public int getCode() {
        return CODE;
    }
    
}
