package ru.kpfu.itis.gr11201.sergeev.net;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientImpl implements HttpClient{
    @Override
    public String get(String url, Map<String, String> params) throws IOException {
        url = addParamsToUrl(url, params);
        try {
            HttpURLConnection connection = createConnection(url, "GET");
            connection.disconnect();
            return readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String post(String url, Map<String, String> params) throws IOException {
        HttpURLConnection connection = createConnection(url, "POST");
        String jsonInput = json(params);
        writeRequest(connection, jsonInput);
        connection.disconnect();
        return readResponse(connection);
    }

    @Override
    public String put(String url, Map<String, String> params) throws IOException {
        HttpURLConnection connection = createConnection(url, "PUT");
        String jsonInput = json(params);
        writeRequest(connection, jsonInput);
        connection.disconnect();
        return readResponse(connection);
    }

    @Override
    public String delete(String url, Map<String, String> params) throws IOException {
        HttpURLConnection connection = createConnection(url, "DELETE");
        String jsonInput = json(params);
        writeRequest(connection, jsonInput);
        connection.disconnect();
        return readResponse(connection);
    }
    private String addParamsToUrl(String url, Map<String, String> params) {
        if (!params.isEmpty()) {
            StringBuilder paramString = new StringBuilder(url);
            paramString.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramString.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            return paramString.toString();
        }
        return null;
    }
    private HttpURLConnection createConnection(String url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer b354b2236b412ea58747a670945e41f711b7fafe8d8de7b9248e5b16f1454aa8");
        connection.setDoOutput(true);
        return connection;
    }
    private String readResponse(HttpURLConnection connection) throws IOException {
        System.out.println(connection.getResponseCode());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
            return content.toString();
        }
    }
    private void writeRequest(HttpURLConnection connection, String jsonInput) {
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String json(Map<String, String> params) {
        Gson json = new Gson();
        System.out.println(json.toJson(params));
        return json.toJson(params);
    }
}
