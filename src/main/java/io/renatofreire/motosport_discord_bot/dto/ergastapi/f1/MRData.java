package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MRData(
        @JsonProperty("xmlns") String xmlns,
        @JsonProperty("series") String series,
        @JsonProperty("url") String url,
        @JsonProperty("limit") String limit,
        @JsonProperty("offset") String offset,
        @JsonProperty("total") String total,
        @JsonProperty("RaceTable") RaceTable raceTable,
        @JsonProperty("StandingsTable") StandingsTable standingsTable
) {}

