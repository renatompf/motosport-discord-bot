package io.renatofreire.motosport_discord_bot.messages;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import io.renatofreire.motosport_discord_bot.service.FormulaOneService;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.stream.Collectors;

public abstract class MessageListener {

    public static final String URL = "https://i.pinimg.com/736x/26/02/d3/2602d387bad5986e4d71c8c6db6b1c1e.jpg";
    public static final EmbedCreateSpec.Builder FINAL_BUILDER = EmbedCreateSpec.builder()
            .author("FormulaOneBot", null, URL)
            .timestamp(Instant.now());
    public static final String CONSTRUCTORS_STANDINGS = "https://www.formula1.com/en/results.html/2024/team.html";
    public static final String DRIVERS_STANDING = "https://www.formula1.com/en/results.html/2024/drivers.html";
    public static final String SCHEDULE = "https://www.formula1.com/en/racing/2024.html";

    private final FormulaOneService formulaOneService;

    protected MessageListener(FormulaOneService formulaOneService) {
        this.formulaOneService = formulaOneService;
    }

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .flatMap(message -> {
                    if (message.getContent().equalsIgnoreCase("!drivers_standings")) {
                        return processF1StandingsCommand(message);
                    } else if (message.getContent().equalsIgnoreCase("!constructor_standings")) {
                        return processConstructorStandingsCommand(message);
                    } else if (message.getContent().equalsIgnoreCase("!f1_today")) {
                        return processTodayRaceCommand(message);
                    } else if (message.getContent().equalsIgnoreCase("!next_race")) {
                        return processNextRaceCommand(message);
                    } else if (message.getContent().equalsIgnoreCase("!latest_result")) {
                        return processLatestResultCommand(message);
                    } else {
                        return Mono.empty();
                    }
                })
                .then();
    }

    private Mono<Void> processLatestResultCommand(Message message) {
        FINAL_BUILDER
                .title("Next Race")
                .url(SCHEDULE)
                .description(formulaOneService.findLatestResult()
                        .toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));

        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(mc -> mc.createMessage(FINAL_BUILDER.build()))
                .then();
    }

    private Mono<Void> processTodayRaceCommand(Message message) {
        FINAL_BUILDER
                .title("Today's Race")
                .url(SCHEDULE)
                .description(formulaOneService.findTodayRaceSchedule()
                        .toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));

        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(mc -> mc.createMessage(FINAL_BUILDER.build()))
                .then();
    }

    private Mono<Void> processNextRaceCommand(Message message) {
        FINAL_BUILDER
                .title("Next Race")
                .url(SCHEDULE)
                .description(formulaOneService.findFollowingRaceSchedule()
                        .toString()
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));

        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(mc -> mc.createMessage(FINAL_BUILDER.build()))
                .then();
    }

    private Mono<Void> processF1StandingsCommand(Message message) {
        String description = formulaOneService.findDriversStandings().drivers()
                .stream()
                .map(Object::toString) // map each constructor to its string representation
                .collect(Collectors.joining("\n"));

        FINAL_BUILDER
                .url(DRIVERS_STANDING)
                .title("Drivers Standing")
                .description(description.replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));

        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(mc -> mc.createMessage(FINAL_BUILDER.build()))
                .then();
    }

    private Mono<Void> processConstructorStandingsCommand(Message message) {
        String description = formulaOneService.findConstructorsStandings().constructors()
                .stream()
                .map(Object::toString) // map each constructor to its string representation
                .collect(Collectors.joining("\n"));

        FINAL_BUILDER
                .url(CONSTRUCTORS_STANDINGS)
                .title("Constructors Standing")
                .description(description.replace(",", "")
                        .replace("[", "")
                        .replace("]", ""));

        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(mc -> mc.createMessage(FINAL_BUILDER.build()))
                .then();
    }


}
