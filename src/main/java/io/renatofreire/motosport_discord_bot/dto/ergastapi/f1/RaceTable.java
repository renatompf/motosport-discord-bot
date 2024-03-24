package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RaceTable(
        @JsonProperty("season") String season,
        @JsonProperty("round") String round,
        @JsonProperty("position") String position,
        @JsonProperty("circuitId") String circuitId,
        @JsonProperty("Races") List<Race> races
) {}
