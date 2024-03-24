package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Constructor(
        @JsonProperty("constructorId") String constructorId,
        @JsonProperty("url") String url,
        @JsonProperty("name") String name,
        @JsonProperty("nationality") String nationality
) {}

