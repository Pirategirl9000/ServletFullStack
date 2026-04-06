package org.example.servletfullstack.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@WebListener
public class ThymeLeafFactory implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {

        // Set up the template resolver for the thymleaf engine
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("WEB-INF/templates/");
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
