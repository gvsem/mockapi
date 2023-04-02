package com.example.mockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView postsListview;

    private ApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postsListview = findViewById(R.id.postsListview);
        client = new ApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(() -> {

            CreatePostDto dto = new CreatePostDto();
            dto.userId = 1;
            dto.title = "Приветик котятам";
            dto.body = "Котики-обормотики оборвали со смеху животики";
            Post post = client.createPost(dto);

            List<Post> posts = List.of(post);

            //List<Post> posts = client.getPosts();

            runOnUiThread(() -> {
                PostsAdapter adapter = new PostsAdapter(MainActivity.this, posts);
                postsListview.setAdapter(adapter);
            });

        }).start();
    }
}