package org.example.servletfullstack.views;


import jakarta.servlet.ServletContext;

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
     * @param sctx The servlet context for this request
     * @param variables Any thymleaf variables that need to be set
     * @return the resultant page as a String
     */
    public String requestPage(String templateName, ServletContext sctx, Map<String, Object> variables) {
        Context ctx = new Context();
        ctx.setVariables(variables);

        // Prepare the page and send it back as a String of HTML
        return ((TemplateEngine) sctx.getAttribute("thymeleaf")).process(templateName, ctx);
    }

    /**
     * Processes the template page without any thymeleaf variables and returns the HTML as a String
     * @param templateName The name of the page/template requested
     * @param sctx The servlet context for this request
     * @return The resultant page as a String
     */
    public String requestPage(String templateName, ServletContext sctx) {
        return this.requestPage(templateName, sctx, null);
    }
}
