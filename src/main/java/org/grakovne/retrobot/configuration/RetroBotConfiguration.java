package org.grakovne.retrobot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Knows how to provide business valued configuration form context.
 */
@Configuration
public class RetroBotConfiguration {

    @Value("${bot.token}")
    private String token;

    public String getToken() {
        return token;
    }

    public RetroBotConfiguration setToken(String token) {
        this.token = token;
        return this;
    }
}
