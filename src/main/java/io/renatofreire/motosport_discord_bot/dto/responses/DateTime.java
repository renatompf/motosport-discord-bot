package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DateTime(
        @JsonProperty("date") String date,
        @JsonProperty("time") String time
) {

    @Override
    public String toString() {
        final int FIELD_WIDTH = 10; // Width for field names

        // Format and align each field
        String dateFormatted = String.format("%-" + FIELD_WIDTH + "s", "Date:") + date;
        String timeFormatted = String.format("%-" + FIELD_WIDTH + "s", "Time:") + time;

        // Concatenate the formatted fields
        return "\n" + dateFormatted + "\n\t" + timeFormatted + "\n\t";
    }


}
