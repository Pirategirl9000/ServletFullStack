package org.example.servletfullstack.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * ServletContextListener that builds the thymleaf engine and adds it to the servlet context on initialization
 */
@WebListener
public class ThymeLeafFactory implements ServletContextListener {
    /**
     * Triggered when the servlet context is initialized building the thymeleaf engine and adding it to the attributes
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    public void contextInitialized(ServletContextEvent sce) {

        // Set up the template resolver for the thymleaf engine
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding("UTF-8");

        // Create and set up the template engine
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        // Add the engine to our servlet context so servlets can access it
        sce.getServletContext().setAttribute("thymeleaf", engine);
    }
}
