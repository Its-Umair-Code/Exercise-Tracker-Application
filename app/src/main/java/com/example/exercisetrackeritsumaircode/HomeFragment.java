package com.example.exercisetrackeritsumaircode;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {


    AppCompatButton show_exercises, start_exercises, show_record;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        show_exercises = v.findViewById(R.id.show_exercises);
        start_exercises = v.findViewById(R.id.start_exercise);
        show_record = v.findViewById(R.id.show_record);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        show_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).setNavButtonAndLoadFrag(R.id.btn_exercises, new ExercisesFragment());
            }
        });

        start_exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).setNavButtonAndLoadFrag(R.id.btn_start, new StartFragment());
            }
        });

        show_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).setNavButtonAndLoadFrag(R.id.btn_records, new RecordFragment());
            }
        });

        return v;
    }
}