package org.apathinternational.faithpathrestful.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

import org.springframework.web.servlet.HandlerInterceptor;

public class ThreadContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String transId = request.getHeader("X-Transaction-Id");

        // add trandId to ThreadContext if it is there
        if (transId == null) {
            transId = "N/A";
        }

        MDC.put("transId", transId);

        // Continue processing the request
        return true; 
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Clear Thread Context values after processing the request
        MDC.remove("transId");
    }
}