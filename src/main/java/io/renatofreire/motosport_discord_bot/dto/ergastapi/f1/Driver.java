package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Driver(
        @JsonProperty("driverId") String driverId,
        @JsonProperty("permanentNumber") String permanentNumber,
        @JsonProperty("code") String code,
        @JsonProperty("url") String url,
        @JsonProperty("givenName") String givenName,
        @JsonProperty("familyName") String familyName,
        @JsonProperty("dateOfBirth") String dateOfBirth,
        @JsonProperty("nationality") String nationality
) {}
