package io.renatofreire.motosport_discord_bot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.renatofreire.motosport_discord_bot.dto.ergastapi.f1.ErgastAPIResults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.file.AccessDeniedException;

@Service
public class ErgastService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ErgastService(@Qualifier("ergastAPI") RestClient restClient) {
        this.restClient = restClient;
    }

    public ErgastAPIResults getAllRaces() {
        return restClient.get()
                .uri("current.json")
                .accept(MediaType.APPLICATION_JSON)
                .exchange((restRequest, response) -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new AccessDeniedException(String.format("Something went wrong while making the call. Ex: %s", response.getBody()));
                    } else {
                        return objectMapper.readValue(response.getBody(), ErgastAPIResults.class);
                    }
                });
    }

    public ErgastAPIResults getLatestResult() {
        return restClient.get()
                .uri("current/last/results.json")
                .accept(MediaType.APPLICATION_JSON)
                .exchange((restRequest, response) -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new AccessDeniedException(String.format("Something went wrong while making the call. Ex: %s", response.getBody()));
                    } else {
                        return objectMapper.readValue(response.getBody(), ErgastAPIResults.class);
                    }
                });
    }

    public ErgastAPIResults getDriversStanding() {
        return restClient.get()
                .uri("current/driverStandings.json")
                .accept(MediaType.APPLICATION_JSON)
                .exchange((restRequest, response) -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new AccessDeniedException(String.format("Something went wrong while making the call. Ex: %s", response.getBody()));
                    } else {
                        return objectMapper.readValue(response.getBody(), ErgastAPIResults.class);
                    }
                });
    }

    public ErgastAPIResults getConstructorsStanding() {
        return restClient.get()
                .uri("current/constructorStandings.json")
                .accept(MediaType.APPLICATION_JSON)
                .exchange((restRequest, response) -> {
                    if (!response.getStatusCode().is2xxSuccessful()) {
                        throw new AccessDeniedException(String.format("Something went wrong while making the call. Ex: %s", response.getBody()));
                    } else {
                        return objectMapper.readValue(response.getBody(), ErgastAPIResults.class);
                    }
                });
    }

}
