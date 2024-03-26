package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record DriverDTO(
        String code,
        String number,
        String status,
        String constructor
) {
    @Override
    public String toString() {
        // Format and align each field
        String codeFormatted = code;
        String numberFormatted = number;
        String teamFormatted = constructor;
        String statusFormatted = status != null ? status : "";

        // Concatenate the formatted fields
        return codeFormatted + "\n" + numberFormatted + "\n" + teamFormatted + "\n" + statusFormatted;
    }
}
