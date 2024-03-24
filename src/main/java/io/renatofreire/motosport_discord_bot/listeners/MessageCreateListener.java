package io.renatofreire.motosport_discord_bot.listeners;

import discord4j.core.event.domain.message.MessageCreateEvent;
import io.renatofreire.motosport_discord_bot.service.FormulaOneService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    public MessageCreateListener(FormulaOneService formulaOneService) {
        super(formulaOneService);
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }
}
