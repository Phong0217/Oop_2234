package com.example.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslate {

    public static void main(String[] args) throws IOException {
        String text = "Hello world!";

        System.out.println("Translated text: "+ text + "\n"
                + translate("en", "fr", text));
    }

    private static String translate(String targetLang, String sourceLang, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbwzcOvNY32uqk8FGGE0gAsn-IGT-ZTarAcNmEKp-Z7tTkNkKzPCaivOPTprNN6zXq4N/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + targetLang +
                "&source=" + sourceLang;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return response.toString();
    }
}
