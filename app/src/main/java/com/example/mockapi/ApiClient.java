package com.example.mockapi;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClient {

    private final OkHttpClient client;

    private final Gson gson;

    private final String url = "https://jsonplaceholder.typicode.com";

    public ApiClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public List<Post> getPosts() {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url + "/posts").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) { // code - не 2**
                throw new IOException("Unexpected code " + response);
            } else {
                final String responseData = response.body().string();
                Post[] posts = gson.fromJson(responseData, Post[].class);
                return List.of(posts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Post createPost(CreatePostDto dto) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url + "/posts").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).method(
                "POST",
                RequestBody.create(
                        gson.toJson(dto, CreatePostDto.class).getBytes(StandardCharsets.UTF_8)
                )
        ).addHeader("Content-Type", "application/json; charset=UTF-8")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) { // code - не 200 OK
                throw new IOException("Unexpected code " + response);
            } else {
                final String responseData = response.body().string();
                return gson.fromJson(responseData, Post.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
