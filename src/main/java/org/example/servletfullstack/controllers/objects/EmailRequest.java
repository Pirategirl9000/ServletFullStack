package org.example.servletfullstack.controllers.objects;

public record EmailRequest(String from, String subject, String body) {
}
