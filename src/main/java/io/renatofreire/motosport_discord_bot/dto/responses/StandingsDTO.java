package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StandingsDTO(

        List<StandingDriversDTO> drivers,
        List<StandingConstructorsDTO> constructors
) {

    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record StandingDriversDTO (
            String position,
            String points,
            DriverDTO driver
    ){
        @Override
        public String toString() {
            // Concatenate the formatted fields
            return position + " -\t" + points + " Points\t\n" + driver + '\n';
        }

    }

    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record StandingConstructorsDTO(
            String position,
            String points,
            String teamName
    ) {

        @Override
        public String toString() {
            // Concatenate the formatted fields
            return position + "\t - " + teamName + "\t - " + points + " Points \n";
        }
    }

    @Override
    public String toString() {
        final int FIELD_WIDTH = 15; // Width for field names

        StringBuilder sb = new StringBuilder();
        sb.append(formatField("Drivers:", drivers));
        sb.append("\n\t").append(formatField("Constructors:", constructors));

        return sb.toString();
    }

    private String formatField(String fieldName, List<?> fieldValue) {
        final int FIELD_WIDTH = 15; // Width for field names

        String formattedField = String.format("%-" + FIELD_WIDTH + "s", fieldName);
        if (fieldValue != null && !fieldValue.isEmpty()) {
            return formattedField + fieldValue.toString();
        } else {
            return formattedField;
        }
    }
}
