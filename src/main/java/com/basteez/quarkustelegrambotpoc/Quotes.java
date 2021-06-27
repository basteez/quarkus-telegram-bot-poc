package com.basteez.quarkustelegrambotpoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Quotes {

    public static String getRandomQuote(Class c){
        //this can be done better (i.e. loading the quotes when the bot starts)
        InputStream jsonInputStream = c.getClassLoader().getResourceAsStream("quotes.json");
        ObjectMapper mapper = new ObjectMapper();
        List<String> quotes = new ArrayList<>();
        try {
            quotes = mapper.readValue(jsonInputStream, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer randomIndex = (int)(Math.random()*quotes.size());
        return quotes.get(randomIndex);
    }
}
