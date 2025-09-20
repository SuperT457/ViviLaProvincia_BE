package it.tuo.progetto.config;  // usa il package che preferisci

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/images/**")
                .allowedOrigins("http://localhost:4200") // Angular dev server
                .allowedMethods("GET"); // opzionale: puoi aggiungere POST, PUT, ecc.
    }
}

