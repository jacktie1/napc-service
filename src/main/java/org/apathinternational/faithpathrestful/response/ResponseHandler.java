package org.apathinternational.faithpathrestful.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(Object responseObj) {
        HttpStatus status = HttpStatus.OK;

        Map<String, Object> map = new HashMap<String, Object>();
            map.put("result", responseObj);
            map.put("error", null);
            map.put("status", status.value());

            return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("result", responseObj);
            map.put("error", null);
            map.put("status", status.value());

            return new ResponseEntity<Object>(map,status);
    }
}