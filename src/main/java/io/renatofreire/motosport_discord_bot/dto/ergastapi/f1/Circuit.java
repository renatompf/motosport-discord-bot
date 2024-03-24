package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Circuit(
        @JsonProperty("circuitId") String circuitId,
        @JsonProperty("url") String url,
        @JsonProperty("circuitName") String circuitName,
        @JsonProperty("Location") Location location
) {}
