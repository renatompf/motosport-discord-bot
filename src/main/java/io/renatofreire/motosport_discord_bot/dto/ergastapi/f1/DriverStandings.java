package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DriverStandings(
        String position,
        String positionText,
        String points,
        String wins,
        @JsonProperty("Driver") Driver driver,
        @JsonProperty("Constructors") List<Constructor> constructors
) {
}
