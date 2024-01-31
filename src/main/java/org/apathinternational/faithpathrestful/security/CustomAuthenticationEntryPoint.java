package org.apathinternational.faithpathrestful.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// CustomAuthenticationEntryPoint is a custom implementation of the AuthenticationEntryPoint interface
// This class is used to handle authentication errors
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Set response status to Unauthorized (401)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create a JSON response body
        Map<String, Object> error = new HashMap<>();
        error.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        error.put("message", "Unauthorized access. Please provide a valid token.");

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("result", null);
        responseBody.put("error", error);
        responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);

        String transId = request.getHeader("X-Transaction-Id");

        if (transId != null) {
            response.setHeader("X-Transaction-Id", transId);
        }

        // Write the JSON response to the HttpServletResponse
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(responseBody));
    }
    
}
