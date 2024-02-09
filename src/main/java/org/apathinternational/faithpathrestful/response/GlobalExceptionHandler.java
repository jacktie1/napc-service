package org.apathinternational.faithpathrestful.response;

import org.apathinternational.faithpathrestful.common.exception.ApplicationException;
import org.apathinternational.faithpathrestful.common.exception.BusinessException;
import org.apathinternational.faithpathrestful.common.exception.CustomAccessDeniedException;
import org.apathinternational.faithpathrestful.common.exception.ValidationException;
import org.apathinternational.faithpathrestful.common.util.Constants;
import org.apathinternational.faithpathrestful.model.response.ErrorResponse;
import org.apathinternational.faithpathrestful.model.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Here starts the handlers for the framework exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Exception: ", ex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error found. Please contact support for assistance"));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<Object> handleAuthorizationServiceException(AuthorizationServiceException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()));
        map.put("status", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage()));
        map.put("status", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        return new ResponseEntity<Object>(map, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()));
        map.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<Object>(map, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        map.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> validationErrors = new HashMap<String, String>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });

        map.put("result", null);
        map.put("error", new ValidationErrorResponse(Constants.ERROR_CODE_VALIDATION_EXCEPTION, "Validation error(S) found. Please check the request and try again.", validationErrors));
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }
    
    // Here starts the handlers for the application exceptions
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(ex.getCode(), ex.getMessage()));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("result", null);
        map.put("status", HttpStatus.BAD_REQUEST.value());

        if(ex.getFieldErrors() != null) {
            map.put("error", new ValidationErrorResponse(ex.getCode(), ex.getMessage(), ex.getFieldErrors()));
        }
        else {
            map.put("error", new ErrorResponse(ex.getCode(), ex.getMessage()));
        }

        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(ex.getCode(), ex.getMessage()));
        map.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class, CustomAccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", null);
        map.put("error", new ErrorResponse(Constants.ERROR_CODE_CUSTOM_ACCESS_DENIED_EXCEPTION, ex.getMessage()));
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
    }
}