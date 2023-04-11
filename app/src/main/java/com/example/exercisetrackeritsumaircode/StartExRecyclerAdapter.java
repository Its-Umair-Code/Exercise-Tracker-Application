package com.example.exercisetrackeritsumaircode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StartExRecyclerAdapter extends RecyclerView.Adapter<StartExRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<ShowExImagesModel> arrStart;
    private boolean[] mChecked;

    public StartExRecyclerAdapter(Context context, ArrayList<ShowExImagesModel> arrStart) {
        this.context = context;
        this.arrStart = arrStart;
        this.mChecked = new boolean[arrStart.size()];
    }

    @NonNull
    @Override
    public StartExRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.start_ecercises, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StartExRecyclerAdapter.ViewHolder holder, int position) {

        holder.startExImage.setImageResource(arrStart.get(position).image);
        holder.startExId.setText(arrStart.get(position).id);
        holder.startExCheckbox.setChecked(mChecked[position]);
        holder.startExCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChecked[holder.getAdapterPosition()] = holder.startExCheckbox.isChecked();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrStart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView startExImage;
        TextView startExId;
        CheckBox startExCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startExImage = itemView.findViewById(R.id.startExImage);
            startExId = itemView.findViewById(R.id.startExId);
            startExCheckbox = itemView.findViewById(R.id.startCheckBox);
        }
    }

    public boolean[] getCheckItems(){
        return mChecked;
    }

}
