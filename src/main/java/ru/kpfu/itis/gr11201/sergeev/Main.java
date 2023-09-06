package ru.kpfu.itis.gr11201.sergeev;

import ru.kpfu.itis.gr11201.sergeev.net.HttpClientImpl;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpClientImpl httpClient = new HttpClientImpl();
        HashMap<String, String> mapGet = new HashMap<>();
        mapGet.put("id", "1");
        HashMap<String, String> mapPost = new HashMap<>();
        mapPost.put("name", "Rostik");
        mapPost.put("username", "itrostik");

        HashMap<String, String> mapPut = new HashMap<>();
        HashMap<String, String> mapDelete = new HashMap<>();
        mapPut.put("name", "name");
        mapPut.put("username", "itname");
        System.out.println(httpClient.get("https://jsonplaceholder.typicode.com/users", mapGet));
        System.out.println(httpClient.post("https://jsonplaceholder.typicode.com/users", mapPost));
        System.out.println(httpClient.put("https://jsonplaceholder.typicode.com/users/1", mapPut));
        System.out.println(httpClient.put("https://jsonplaceholder.typicode.com/users/1", mapPut));


    }
}
