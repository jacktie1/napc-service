package org.apathinternational.faithpathrestful.response;

import org.apathinternational.faithpathrestful.common.exception.ApplicationException;
import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.common.util.Constants;
import org.apathinternational.faithpathrestful.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Exception: ", ex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(Constants.ERROR_CODE_UNKNOWN_EXCEPTION, "Internal server error found. Please contact support for assistance"));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(ex.getCode(), ex.getMessage()));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BusinessException.class, ValidationException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(ex.getCode(), ex.getMessage()));
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class, CustomAccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(Constants.ERROR_CODE_CUSTOM_ACCESS_DENIED_EXCEPTION, ex.getMessage()));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
    }
}