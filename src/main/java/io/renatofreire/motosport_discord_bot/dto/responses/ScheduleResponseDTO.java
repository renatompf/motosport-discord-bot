package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ScheduleResponseDTO(
        CircuitDTO circuit,
        DateTime firstPractice,
        DateTime secondPractice,
        DateTime thirdPractice,
        DateTime qualifying,
        DateTime sprint,
        DateTime race
) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(circuit.toString()).append("\n");
        sb.append("\n\t").append(formatField("First Practice:", firstPractice));
        sb.append("\n\t").append(formatField("Second Practice:", secondPractice));
        sb.append("\n\t").append(formatField("Third Practice:", thirdPractice));
        sb.append("\n\t").append(formatField("Qualifying:", qualifying));
        sb.append("\n\t").append(formatField("Race:", race));
        if (sprint != null) {
            sb.append("\n\t").append(formatField("Sprint:", sprint));
        }

        return sb.toString();
    }

    private String formatField(String fieldName, Object fieldValue) {
        final int FIELD_WIDTH = 15; // Width for field names

        String formattedField = String.format("%-" + FIELD_WIDTH + "s", fieldName);
        return formattedField + (fieldValue != null ? fieldValue.toString() : "");
    }
}
