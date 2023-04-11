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

public class RecordImagesAdapter extends RecyclerView.Adapter<RecordImagesAdapter.ViewHolder> {

    Context context;
    ArrayList<RecordImagesModel> recordTimes;
    public RecordImagesAdapter(Context context,ArrayList<RecordImagesModel> recordTimes){
        this.context = context;
        this.recordTimes = recordTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.record_show_exercises, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recordImage.setImageResource(recordTimes.get(position).image);
        holder.recordTimesNo.setText(recordTimes.get(position).noOfTimes);
    }

    @Override
    public int getItemCount() {
        return recordTimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recordImage;
        TextView recordTimesNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recordImage = itemView.findViewById(R.id.recordImages);
            recordTimesNo = itemView.findViewById(R.id.recordImageNo);

        }
    }
}
