package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CircuitDTO (
    @JsonProperty("circuitName") String circuitName,
    @JsonProperty("locality") String locality,
    @JsonProperty("country") String country
){

    @Override
    public String toString() {
        final int FIELD_WIDTH = 15; // Width for field names

        // Format and align each field
        String circuitNameFormatted = String.format("%-" + FIELD_WIDTH + "s", "Circuit Name:") + circuitName;
        String localityFormatted = String.format("%-" + FIELD_WIDTH + "s", "Locality:") + locality;
        String countryFormatted = String.format("%-" + FIELD_WIDTH + "s", "Country:") + country;

        // Concatenate the formatted fields
        return circuitNameFormatted + "\n" +
                localityFormatted + "\n" +
                countryFormatted;
    }
}
