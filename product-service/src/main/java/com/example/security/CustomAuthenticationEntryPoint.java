package com.example.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint implements Serializable {
    private static final long serialVersionUID = -8236047777718904543L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        final String expiredMsg = (String) request.getAttribute("expired");
        final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);
        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Developer");
        super.afterPropertiesSet();
    }
}