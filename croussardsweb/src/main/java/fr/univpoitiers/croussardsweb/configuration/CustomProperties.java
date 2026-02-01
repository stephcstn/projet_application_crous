package fr.univpoitiers.croussardsweb.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fr.univpoitiers.croussardsweb")
public class CustomProperties {

    private String apiUrl;
}
