package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Result(
        @JsonProperty("number") String number,
        @JsonProperty("position") String position,
        @JsonProperty("positionText") String positionText,
        @JsonProperty("points") String points,
        @JsonProperty("Driver") Driver driver,
        @JsonProperty("Constructor") Constructor constructor,
        @JsonProperty("grid") String grid,
        @JsonProperty("laps") String laps,
        @JsonProperty("status") String status,
        @JsonProperty("Time") Time time,
        @JsonProperty("FastestLap") FastestLap fastestLap
) {}
