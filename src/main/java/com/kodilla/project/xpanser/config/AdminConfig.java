package com.kodilla.project.xpanser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    public String getAdminMail() {
        return adminMail;
    }
}

