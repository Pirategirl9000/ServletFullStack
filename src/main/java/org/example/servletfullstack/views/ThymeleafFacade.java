package org.example.servletfullstack.views;


import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/**
 * Helper class for working with Thymeleaf. Allows for easy requesting of web pages that need building by Thymeleaf
 */
public class ThymeleafFacade {
    /**
     * This is a helper class and cannot be instantiated
     */
    private ThymeleafFacade() {/* noop */}

    /**
     * Processes the template page with the given variables and then returns the HTML as a String
     * @param templateName The name of the page/template requested
     * @param request The HTTP request for this page
     * @param response the HTTP reesponse for this page
     * @param variables Any thymleaf variables that need to be set
     * @return the resultant page as a String
     */
    public static String requestPage(String templateName, HttpServletRequest request, HttpServletResponse response, Map<String, Object> variables) {
        JakartaServletWebApplication webApplication = JakartaServletWebApplication.buildApplication(request.getServletContext());
        IWebExchange exchange = webApplication.buildExchange(request, response);

        WebContext ctx = new WebContext(exchange, request.getLocale());
        ctx.setVariables(variables);

        // Prepare the page and send it back as a String of HTML
        return ((TemplateEngine) request.getServletContext().getAttribute("thymeleaf")).process(templateName, ctx);
    }

    /**
     * Processes the template page without any thymeleaf variables and returns the HTML as a String
     * @param templateName The name of the page/template requested
     * @param request The HTTP request for this page
     * @param response the HTTP reesponse for this page
     * @return The resultant page as a String
     */
    public static String requestPage(String templateName, HttpServletRequest request, HttpServletResponse response) {
        return requestPage(templateName, request, response, null);
    }
}
