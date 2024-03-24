package io.renatofreire.motosport_discord_bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    private final String ERTGAST_API_URL = "http://ergast.com/api/f1/";

    @Bean("ergastAPI")
    public RestClient createErtgastRestClient() {
        return RestClient.builder().baseUrl(ERTGAST_API_URL)
                .build();
    }

}
