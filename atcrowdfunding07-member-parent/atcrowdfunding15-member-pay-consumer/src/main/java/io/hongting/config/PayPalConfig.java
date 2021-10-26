package io.hongting.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author hongting
 * @create 2021 10 26 12:29 PM
 */
@Configuration
public class PayPalConfig {

    @Value("${paypal.client.app}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        return new APIContext(clientId, clientSecret, mode);
    }


}
