package com.shubham.dev.discoveryserver;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Execute your Docker commands here
        startKeycloak();
        startZipkin();
    }

    private void startKeycloak() {
        // Replace this command with your actual Keycloak Docker command
        String keycloakCommand = "docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.6 start-dev";
        executeCommand(keycloakCommand);
    }

    private void startZipkin() {
        // Replace this command with your actual Zipkin Docker command
        String zipkinCommand = "docker run -d -p 9411:9411 openzipkin/zipkin";
        executeCommand(zipkinCommand);
    }

    private void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            // Print exit code (you may handle this as needed)
            System.out.println("Command exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
