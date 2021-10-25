package com.nvidad.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.nvidad.instagramclone.fragments.DetailFragment;
import com.nvidad.instagramclone.fragments.PostsFragment;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProfile;
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(Post post) {
            tvUsername.setText(post.getUser().getUsername());

            ParseFile profilePicture = post.getProfilePicture();
            if (profilePicture != null)
//                Glide.with(context).load(profilePicture.getUrl()).into(ivProfile);
                Glide.with(context)
                        .load(profilePicture.getUrl())
                        .transform(new CircleCrop())
                        .into(ivProfile);

            tvDescription.setText(post.getDescription());

            String timestamp = post.getCreatedAt().toString();

            tvTimestamp.setText(timestamp);

            ParseFile postImage = post.getImage();
            if (postImage != null)
                Glide.with(context).load(postImage.getUrl()).into(ivImage);
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

//    // Add a list of items -- change to type used
//    public void addAll(List<Post> newPosts) {
//        posts.addAll(newPosts);
//        notifyDataSetChanged();
//    }
}
