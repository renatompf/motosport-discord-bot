package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Practice(
        @JsonProperty("date") String date,
        @JsonProperty("time") String time
) {}
