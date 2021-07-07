package com.example.instavy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Date;

public class PostDetailActivity extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreatedAt;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        post = getIntent().getParcelableExtra("Post");

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        Glide.with(PostDetailActivity.this)
                .load(post.getImage().getUrl())
                .into(ivImage);

        Date d = post.getCreatedAt();

        tvCreatedAt.setText(post.calculateTimeAgo(d));

    }
}