package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FastestLap(
        @JsonProperty("rank") String rank,
        @JsonProperty("lap") String lap,
        @JsonProperty("Time") Time time,
        @JsonProperty("AverageSpeed") AverageSpeed averageSpeed
) {}
