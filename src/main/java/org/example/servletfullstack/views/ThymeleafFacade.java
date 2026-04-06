package org.example.servletfullstack.views;


import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Helper class for working with Thymeleaf. Allows for easy requesting of web pages that need building by Thymeleaf
 */
public class ThymeleafFacade {

    /**
     * Processes the template page with the given variables and then returns the HTML as a String
     * @param templateName The name of the page/template requested
     * @param request The HTTP request sent for this page, used to acquire the servlet context
     * @param variables Any thymleaf variables that need to be set
     * @return the resultant page as a String
     */
    public String requestPage(String templateName, HttpServletRequest request, Map<String, Object> variables) {
        Context ctx = new Context();
        ctx.setVariables(variables);

        // Prepare the page and send it back as a String of HTML
        return ((TemplateEngine) request.getServletContext().getAttribute("thymeleaf")).process(templateName, ctx);
    }

    /**
     * Processes the template page without any thymeleaf variables and returns the HTML as a String
     * @param templateName The name of the page/template requested
     * @param request The HTTP request sent for this page, used to acquire the servlet context
     * @return The resultant page as a String
     */
    public String requestPage(String templateName, HttpServletRequest request) {
        return this.requestPage(templateName, request, null);
    }
}
