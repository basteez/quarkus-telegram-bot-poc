package com.basteez.quarkustelegrambotpoc;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@QuarkusMain
public class MainClass {

    public static void main(String[] args){
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {
        @ConfigProperty(name = "bot.username")
        public String botUsername;

        @ConfigProperty(name = "bot.token")
        public String botToken;

        @Override
        public int run(String... args) throws Exception {
            System.out.println("BOT STARTING");

            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(new BobRossBot(botUsername, botToken));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            Quarkus.waitForExit();
            return 0;
        }
    }
}
