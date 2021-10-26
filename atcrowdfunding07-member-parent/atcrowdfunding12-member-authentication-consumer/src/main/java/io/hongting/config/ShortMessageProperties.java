package io.hongting.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hongting
 * @create 2021 10 17 2:32 PM
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {

    private String apiKey;

    private String apiSecret;

    private String host;
}
