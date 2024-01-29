package org.apathinternational.faithpathrestful.config.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apathinternational.faithpathrestful.common.util.LoggingJsonFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private boolean includeResponsePayload = true;
    private int maxPayloadLength = 1000;
    private LoggingJsonFormatter jsonFormatter = new LoggingJsonFormatter();
  
    private String getContentAsString(byte[] buf, int maxLength, String charsetName) {
      if (buf == null || buf.length == 0) return "";
      int length = Math.min(buf.length, this.maxPayloadLength);
      try {
        return new String(buf, 0, length, charsetName);
      } catch (UnsupportedEncodingException ex) {
        return "Unsupported Encoding";
      }
    }
  
    /**
     * Log each request and respponse with full Request URI, content payload and duration of the request in ms.
     * @param request the request
     * @param response the response
     * @param filterChain chain of filters
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
  
      long startTime = System.currentTimeMillis();
      String transId = request.getHeader("X-Transaction-Id");

      if (transId == null) {
        transId = "N/A";
      }

      StringBuffer reqInfo = new StringBuffer()
       .append("[TransId: ").append(transId).append("] ")
       .append("Request Details: ")
       .append("method=" + request.getMethod())
       .append(", url="+request.getRequestURL());
  
      String queryString = request.getQueryString();
      if (queryString != null) {
        reqInfo.append("?").append(queryString);
      }
  
      if (request.getUserPrincipal() != null) {
        reqInfo.append(", principalName=")
          .append(request.getUserPrincipal().getName());
      }
  
      // ========= Log request and response payload ("body") ========
      // We CANNOT simply read the request payload here, because then the InputStream would be consumed and cannot be read again by the actual processing/server.
      // So we need to apply some stronger magic here :-)
      ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
      ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
  
      filterChain.doFilter(wrappedRequest, wrappedResponse); // ======== This performs the actual request!

      long duration = System.currentTimeMillis() - startTime;
  
      // I can only log the request's body AFTER the request has been made and ContentCachingRequestWrapper did its work.
      String requestBody = this.getContentAsString(wrappedRequest.getContentAsByteArray(), this.maxPayloadLength, request.getCharacterEncoding());
      if (requestBody.length() > 0)
      {
        logger.info(reqInfo + ", body=" + jsonFormatter.format(requestBody) + ", duration=" + duration + "ms");
      }
      else
      {
        logger.info(reqInfo.toString() + ", duration=" + duration + "ms");
      }
  
      if (includeResponsePayload)
      {
        byte[] buf = wrappedResponse.getContentAsByteArray();
        String responsePayload = getContentAsString(buf, this.maxPayloadLength, response.getCharacterEncoding());

        StringBuffer respInfo = new StringBuffer()
        .append("[TransId: ").append(transId).append("] ")
        .append("Reponse Details: ")
        .append("statusCode=" + response.getStatus())
        .append(", body=" + jsonFormatter.format(responsePayload));
   
        logger.info(respInfo.toString());
      }
  
      wrappedResponse.copyBodyToResponse();  // IMPORTANT: copy content of response back into original response
    }
  
}