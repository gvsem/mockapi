package com.example.mockapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PostsAdapter extends ArrayAdapter<Post> {

    private final List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        super(context, R.layout.item_post, posts);
        this.posts = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, null);
        }

        TextView titleTextview = convertView.findViewById(R.id.titleTextview);
        TextView bodyTextview = convertView.findViewById(R.id.bodyTextview);

        Post post = posts.get(position);
        titleTextview.setText(post.title);
        bodyTextview.setText(post.body);

        return convertView;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

}
