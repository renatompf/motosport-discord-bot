package io.renatofreire.motosport_discord_bot.dto.ergastapi.f1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ConstructorStandings(
        String position,
        String positionText,
        String points,
        String wins,
        @JsonProperty("Constructor") Constructor constructor
) {
}
