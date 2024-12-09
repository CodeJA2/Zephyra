package com.proyecto.Zephyra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
    
     @Bean
    public APIContext apiContext() throws PayPalRESTException {
        String clientId = "AZI4NjEvyLgQYTTZ1s_EmTmo-HAp4DtKaVnzc7h8Ea30gAmLH4aNs35yJYtYmJIH_CBjh3O9d2oBQCy-";
        String clientSecret = "EIVK-GCYnn7zyK-2mdAObkFT_vGFiMPokt2gHkHaHWdcNgyd7R4T8Ils20zna2ZbnrSMUMH-3yVUfKDA";
        String mode = "sandbox"; // Usa "live" para producción

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        return apiContext;
    }

}
