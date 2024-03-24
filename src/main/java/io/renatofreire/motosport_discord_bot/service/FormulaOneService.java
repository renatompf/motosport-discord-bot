package io.renatofreire.motosport_discord_bot.service;

import io.renatofreire.motosport_discord_bot.dto.ergastapi.f1.*;
import io.renatofreire.motosport_discord_bot.dto.responses.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FormulaOneService {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private final ErgastService ergastService;
    public FormulaOneService(ErgastService ergastService) {
        this.ergastService = ergastService;
    }

    public ScheduleResponseDTO findFollowingRaceSchedule() {
        ErgastAPIResults allRaces = ergastService.getAllRaces();
        Race followingRace = findFollowingRace(allRaces);

        return buildScheduleDTO(followingRace);
    }

    public ScheduleResponseDTO findTodayRaceSchedule() {
        ErgastAPIResults allRaces = ergastService.getAllRaces();
        Race todayRace = findTodayRace(allRaces);

        return buildScheduleDTO(todayRace);
    }

    public ResultDTO findLatestResult() {
        ErgastAPIResults latestResult = ergastService.getLatestResult();

        Race latestRace = latestResult.mrData()
                .raceTable()
                .races()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoResourceFoundException("No Races found"));

        CircuitDTO circuitDTO = mapToCircuitDTO(latestRace.circuit());

        DateTime time = new DateTime(latestRace.date(), formatTime(latestRace.time()));

        List<ResultDTO.DriverPositionDTO> drivers = mapToDriverPositionDTO(latestRace.results());

        return ResultDTO.builder()
                .circuit(circuitDTO)
                .time(time)
                .drivers(drivers)
                .build();
    }

    public StandingsDTO findDriversStandings() {
        ErgastAPIResults standings = ergastService.getDriversStanding();

        List<StandingsDTO.StandingDriversDTO> driversStandings = standings
                .mrData()
                .standingsTable()
                .standingsLists()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoResourceFoundException("No Standings Lists found"))
                .driverStandings()
                .stream()
                .map(ds -> {
                            DriverDTO driver = DriverDTO.builder()
                                    .number(ds.driver().permanentNumber())
                                    .code(ds.driver().code())
                                    .constructor(ds.constructors().getFirst().name())
                                    .build();

                            return StandingsDTO.StandingDriversDTO.builder()
                                    .driver(driver)
                                    .position(ds.position())
                                    .points(ds.points())
                                    .build();
                        }
                ).toList();

        return StandingsDTO.builder()
                .drivers(driversStandings)
                .build();

    }

    public StandingsDTO findConstructorsStandings() {
        ErgastAPIResults standings = ergastService.getConstructorsStanding();

        List<StandingsDTO.StandingConstructorsDTO> driversStandings = standings
                .mrData()
                .standingsTable()
                .standingsLists()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoResourceFoundException("No Standings Lists found"))
                .constructorsStandings()
                .stream()
                .map(ds -> StandingsDTO.StandingConstructorsDTO.builder()
                        .teamName(ds.constructor().name())
                        .position(ds.position())
                        .points(ds.points())
                        .build()
                ).toList();

        return StandingsDTO.builder()
                .constructors(driversStandings)
                .build();

    }

    private Race findFollowingRace(@NonNull ErgastAPIResults allRaces) {
        return allRaces.mrData()
                .raceTable()
                .races()
                .stream()
                .filter(r -> LocalDate.now().isBefore(convertStringIntoDate(r.date())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No following race found"));
    }

    private Race findTodayRace(@NonNull ErgastAPIResults allRaces) {
        return allRaces.mrData()
                .raceTable()
                .races()
                .stream()
                .filter(r -> LocalDate.now().isEqual(convertStringIntoDate(r.date())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No following race found"));
    }

    private CircuitDTO mapToCircuitDTO(Circuit circuit) {
        return CircuitDTO.builder()
                .circuitName(circuit.circuitName())
                .country(circuit.location().country())
                .locality(circuit.location().locality())
                .build();
    }

    private List<ResultDTO.DriverPositionDTO> mapToDriverPositionDTO(List<Result> results) {
        return results.stream()
                .map(result -> {
                    DriverDTO driver = DriverDTO.builder()
                            .number(result.driver().permanentNumber())
                            .code(result.driver().code())
                            .status(result.status())
                            .constructor(result.constructor().name())
                            .build();

                    return ResultDTO.DriverPositionDTO.builder()
                            .driver(driver)
                            .startedFrom(result.grid())
                            .finishedIn(result.position())
                            .status(result.status())
                            .build();
                })
                .toList();
    }

    private ScheduleResponseDTO buildScheduleDTO(@NonNull Race race) {
        ScheduleResponseDTO.ScheduleResponseDTOBuilder builder = ScheduleResponseDTO.builder();

        if(race.circuit() != null){
            CircuitDTO circuitDTO;
            Circuit circuit = race.circuit();
            if(race.circuit().location() != null){
                Location location = circuit.location();
                circuitDTO = new CircuitDTO(circuit.circuitName(), location.locality(), location.country());
            }else{
                circuitDTO = new CircuitDTO(circuit.circuitName(), null, null);
            }

            builder.circuit(circuitDTO);
        }

        if (race.firstPractice() != null) {
            builder.firstPractice(new DateTime(race.firstPractice().date(), formatTime(race.firstPractice().time())));
        }

        if (race.secondPractice() != null) {
            builder.secondPractice(new DateTime(race.secondPractice().date(), formatTime(race.secondPractice().time())));
        }

        if (race.thirdPractice() != null) {
            builder.thirdPractice(new DateTime(race.thirdPractice().date(), formatTime(race.thirdPractice().time())));
        }

        if (race.qualifying() != null) {
            builder.qualifying(new DateTime(race.qualifying().date(), formatTime(race.qualifying().time())));
        }

        builder.race(new DateTime(race.date(), formatTime(race.time())));

        return builder.build();
    }

    private String formatTime(String time){
        return time.replace("Z", "") + " GMT";
    }

    private LocalDate convertStringIntoDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }

    private static LocalDate convertInstantToDate(@NonNull Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
