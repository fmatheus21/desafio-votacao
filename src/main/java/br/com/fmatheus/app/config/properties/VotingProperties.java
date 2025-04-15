package br.com.fmatheus.app.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "voting")
public class VotingProperties {

    private SessionProperties session;

    @Getter
    @Setter
    public static class SessionProperties {
        private int expirationTime;
    }
}
