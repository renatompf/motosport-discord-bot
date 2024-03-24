package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandingsList(
        String season,
        String round,
        @JsonProperty("DriverStandings") List<DriverStandings> driverStandings,
        @JsonProperty("ConstructorStandings") List<ConstructorStandings> constructorsStandings
) {
}
