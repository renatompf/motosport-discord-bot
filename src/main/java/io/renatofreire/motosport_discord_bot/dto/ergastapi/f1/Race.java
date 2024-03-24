package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Race(
        @JsonProperty("season") String season,
        @JsonProperty("round") String round,
        @JsonProperty("url") String url,
        @JsonProperty("raceName") String raceName,
        @JsonProperty("Circuit") Circuit circuit,
        @JsonProperty("date") String date,
        @JsonProperty("time") String time,
        @JsonProperty("FirstPractice") Practice firstPractice,
        @JsonProperty("SecondPractice") Practice secondPractice,
        @JsonProperty("ThirdPractice") Practice thirdPractice,
        @JsonProperty("Qualifying") Practice qualifying,
        @JsonProperty("Sprint") Practice sprint,
        @JsonProperty("Results") List<Result> results
) {}