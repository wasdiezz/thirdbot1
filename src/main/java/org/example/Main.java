package org.example;

import org.example.engine.TelegramBotHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotHandler botHandler = new TelegramBotHandler();
            botsApi.registerBot(botHandler);
            System.out.println("Бот oпущен...");

        } catch (TelegramApiException e) {
            System.out.println("Ошибка. " + e);
        }
    }
}