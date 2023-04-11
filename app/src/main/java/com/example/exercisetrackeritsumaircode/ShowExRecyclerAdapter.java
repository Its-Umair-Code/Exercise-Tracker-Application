package com.example.exercisetrackeritsumaircode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowExRecyclerAdapter extends RecyclerView.Adapter<ShowExRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<ShowExImagesModel> exImages;

    public ShowExRecyclerAdapter(Context context, ArrayList<ShowExImagesModel> exImages) {
        this.context = context;
        this.exImages = exImages;
    }

    @NonNull
    @Override
    public ShowExRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.show_exercises_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowExRecyclerAdapter.ViewHolder holder, int position) {
        holder.id.setText(exImages.get(position).id);
        holder.showImage.setImageResource(exImages.get(position).image);
    }

    @Override
    public int getItemCount() {
        return exImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView showImage;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showImage = itemView.findViewById(R.id.exerciseImage);
            id = itemView.findViewById(R.id.exerciseId);
        }
    }
}
