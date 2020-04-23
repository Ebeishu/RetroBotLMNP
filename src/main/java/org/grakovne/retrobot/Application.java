package org.grakovne.retrobot;

import org.grakovne.retrobot.service.RetroBotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * RetroBot Application.
 */
@SpringBootApplication
public class Application {

    private final RetroBotService botService;

    public Application(RetroBotService botService) {
        this.botService = botService;
    }

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Enables telegram messages subscription.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void subscribeToMessages() {
        botService.subscribeToMessages();
    }
}
