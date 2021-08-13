package com.basteez.quarkustelegrambotpoc;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class BobRossBot extends TelegramLongPollingBot {
    public String botUsername = "";
    public String botToken = "";

    public BobRossBot(String username, String token){
        this.botUsername = username;
        this.botToken = token;
    }

    SendMessage message = new SendMessage();
    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();

        if(command.equals("/randomquote")){
            message.setText(Quotes.getRandomQuote(this.getClass()));
            message.setChatId(update.getMessage().getChatId());
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}
