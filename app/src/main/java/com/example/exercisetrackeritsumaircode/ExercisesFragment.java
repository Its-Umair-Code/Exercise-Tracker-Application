package com.example.exercisetrackeritsumaircode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ExercisesFragment extends Fragment {

    ArrayList<ShowExImagesModel> exImages = new ArrayList<>();
    RecyclerView recyclerView;
    ShowExRecyclerAdapter adapter;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercises, container, false);

        recyclerView = v.findViewById(R.id.showExRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        exImages = ((MainActivity) requireActivity()).getData();

        adapter = new ShowExRecyclerAdapter(getContext(), exImages);
        recyclerView.setAdapter(adapter);

        return v;
    }
}