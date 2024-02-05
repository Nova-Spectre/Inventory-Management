package com.shubham.dev.discoveryserver;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Execute your command here
        executeCommand();
    }

    private void executeCommand() {
        // Replace this command with your actual command
        String command = "docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.6 start-dev";

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
