package com.asaitec.rest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestUserRoleFirewall implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestUserRoleFirewall.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Users ROLE filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String restPrefix = request.getRequestURI().split("/")[1];
            String role = request.getHeader("role");
            if(restPrefix.equalsIgnoreCase(role)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401,"NO_ROLE_PERMISSIONS");
            }
        } catch(Exception e) {
            response.sendError(400,"BAD_REQUEST");
            e.printStackTrace();
        }
    }

}