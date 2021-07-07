package com.example.instavy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";

    Context context;
    List<Post> posts;

    PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvUsername;
        ImageView ivImage;
        TextView tvDescription;
        ImageButton ibLike;
        ImageButton ibComment;
        ImageButton ibDM;
        ImageView ivProfile;
        TextView tvUsername2;
        TextView tvCreatedAt;
        ImageButton ibSave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ibLike = itemView.findViewById(R.id.ibLike);
            ibComment = itemView.findViewById(R.id.ibComment);
            ibDM = itemView.findViewById(R.id.ibDM);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvUsername2 = itemView.findViewById(R.id.tvUsername2);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ibSave = itemView.findViewById(R.id.ibSave);

            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            tvUsername2.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();

            if (image != null) {
                Glide.with(context)
                        .load(image.getUrl())
                        .into(ivImage);
            }


            Glide.with(context)
                    .load(R.drawable.instagram_user_filled_24)
                    .circleCrop()
                    .into(ivProfile);

            Date d = post.getCreatedAt();
            tvCreatedAt.setText(post.calculateTimeAgo(d));
            ibLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!ibLike.isSelected())
                        ibLike.setImageResource(R.drawable.ufi_heart_active);
                    else
                        ibLike.setImageResource(R.drawable.ufi_heart);
                }
            });
        }

        @Override
        public void onClick(View view){
            Log.i(TAG,"post clicked!");
            int position =  getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                Post post = posts.get(position);
                Intent i = new Intent(context, PostDetailActivity.class);
                i.putExtra("Post",post);
                context.startActivity(i);

            }
        }
    }
}
