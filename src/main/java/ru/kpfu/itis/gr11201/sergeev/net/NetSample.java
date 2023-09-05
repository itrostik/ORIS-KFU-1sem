package ru.kpfu.itis.gr11201.sergeev.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetSample {
    public static void main(String[] args) {
        //get
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //post
        try{
            URL postUrl = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer b354b2236b412ea58747a670945e41f711b7fafe8d8de7b9248e5b16f1454aa8");
            postConnection.setDoOutput(true);
            String jsonInput = "{\"name\":\"Sen. Anala Iyer\"," +
                    "\"email\":\"sen_anala_iyer10@stroman-leannon.test\"," +
                    "\"gender\":\"female\"," +
                    "\"status\":\"inactive\"}";
            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(postConnection.getResponseCode());
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content);
            }
            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
