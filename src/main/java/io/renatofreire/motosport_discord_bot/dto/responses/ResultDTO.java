package io.renatofreire.motosport_discord_bot.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ResultDTO(
        CircuitDTO circuit,
        DateTime time,
        List<DriverPositionDTO> drivers
) {

    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public record DriverPositionDTO(
            DriverDTO driver,
            String startedFrom,
            String finishedIn,
            String status
    ){
        @Override
        public String toString() {
            final int FIELD_WIDTH = 15; // Width for field names

            // Format and align each field
            String driverFormatted = driver().toString();
            String startedFromFormatted = String.format("%-" + FIELD_WIDTH + "s", "Started From: ") + startedFrom;
            String finishedInFormatted = String.format("%-" + FIELD_WIDTH + "s", "Finished In: ") + finishedIn;
            String statusFormatted = String.format("%-" + FIELD_WIDTH + "s", "Status: ") + status;

            // Concatenate the formatted fields
            return driverFormatted +
                    "\n\t" + startedFromFormatted +
                    "\n\t" + finishedInFormatted +
                    "\n\t" + statusFormatted + "\n\n";
        }
    }

    @Override
    public String toString() {
        // Format and align each field
        String circuitFormatted = circuit != null ? circuit.toString() : "";
        String timeFormatted = time.toString();
        String driversFormatted = drivers.toString();

        // Concatenate the formatted fields
        return  circuitFormatted +
                "\n\t" + timeFormatted +
                "\n\t" + driversFormatted + "\n";
    }
}
