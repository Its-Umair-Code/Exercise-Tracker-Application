package com.example.exercisetrackeritsumaircode;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StartFragment extends Fragment {

    RecyclerView recyclerView;
    StartExRecyclerAdapter startExRecyclerAdapter, adapter2;
    ArrayList<ShowExImagesModel> arrStart = new ArrayList<>();
    AppCompatButton startExDoneBtn;
    private boolean[] checkedItems;
    MyExercisesDatabase db;
    private int[] setChecked = new int[20];

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        db = new MyExercisesDatabase(getActivity());

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        startExDoneBtn = v.findViewById(R.id.startExDoneBtn);
        recyclerView = v.findViewById(R.id.startExRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        arrStart = ((MainActivity) requireActivity()).getData();

        startExRecyclerAdapter = new StartExRecyclerAdapter(getContext(), arrStart);
        recyclerView.setAdapter(startExRecyclerAdapter);

        startExDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDoneActions();
                db.addData(currentDate, currentTime, setChecked);
                Toast.makeText(getContext(), "Record is successfully added!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void btnDoneActions() {
        adapter2 = (StartExRecyclerAdapter) recyclerView.getAdapter();
        checkedItems = adapter2.getCheckItems();

        for (int i = 0; i < checkedItems.length; i++) {
            if (checkedItems[i]) {
                setChecked[i] = 1;
            } else {
                setChecked[i] = 0;
            }
        }
    }

}