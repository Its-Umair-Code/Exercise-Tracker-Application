package com.example.exercisetrackeritsumaircode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    ArrayList<ShowExImagesModel> arrData = new ArrayList<>();
/*    int[] exercisesCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    MyExercisesDatabase db;
    ArrayList<DatabaseModel> dbData;*/

    public class MyThread extends Thread {
        @Override
        public void run() {
            arrData.add(new ShowExImagesModel("01", R.drawable.image4));
            arrData.add(new ShowExImagesModel("02", R.drawable.image5));
            arrData.add(new ShowExImagesModel("03", R.drawable.image11));
            arrData.add(new ShowExImagesModel("04", R.drawable.image20));
            arrData.add(new ShowExImagesModel("05", R.drawable.image15));
            arrData.add(new ShowExImagesModel("06", R.drawable.image13));
            arrData.add(new ShowExImagesModel("07", R.drawable.image7));
            arrData.add(new ShowExImagesModel("08", R.drawable.image6));
            arrData.add(new ShowExImagesModel("09", R.drawable.image2));
            arrData.add(new ShowExImagesModel("10", R.drawable.image3));
            arrData.add(new ShowExImagesModel("11", R.drawable.image10));
            arrData.add(new ShowExImagesModel("12", R.drawable.image16));
            arrData.add(new ShowExImagesModel("13", R.drawable.image9));
            arrData.add(new ShowExImagesModel("14", R.drawable.image14));
            arrData.add(new ShowExImagesModel("15", R.drawable.image12));
            arrData.add(new ShowExImagesModel("16", R.drawable.image8));
            arrData.add(new ShowExImagesModel("17", R.drawable.image19));
            arrData.add(new ShowExImagesModel("18", R.drawable.image18));
            arrData.add(new ShowExImagesModel("19", R.drawable.image17));
            arrData.add(new ShowExImagesModel("20", R.drawable.image1));
        }
    }

    public void startMyThread() {
        MyThread thread = new MyThread();
        thread.start();
    }

/*    public class MyThread2 extends Thread{

        @Override
        public void run() {

            db = new MyExercisesDatabase(MainActivity.this);
            dbData = db.fetchData();

            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).exercises[0] == 1) {
                    exercisesCount[0]++;
                }
                if (dbData.get(i).exercises[1] == 1) {
                    exercisesCount[1]++;
                }
                if (dbData.get(i).exercises[2] == 1) {
                    exercisesCount[2]++;
                }
                if (dbData.get(i).exercises[3] == 1) {
                    exercisesCount[3]++;
                }
                if (dbData.get(i).exercises[4] == 1) {
                    exercisesCount[4]++;
                }
                if (dbData.get(i).exercises[5] == 1) {
                    exercisesCount[5]++;
                }
                if (dbData.get(i).exercises[6] == 1) {
                    exercisesCount[6]++;
                }
                if (dbData.get(i).exercises[7] == 1) {
                    exercisesCount[7]++;
                }
                if (dbData.get(i).exercises[8] == 1) {
                    exercisesCount[8]++;
                }
                if (dbData.get(i).exercises[9] == 1) {
                    exercisesCount[9]++;
                }
                if (dbData.get(i).exercises[10] == 1) {
                    exercisesCount[10]++;
                }
                if (dbData.get(i).exercises[11] == 1) {
                    exercisesCount[11]++;
                }
                if (dbData.get(i).exercises[12] == 1) {
                    exercisesCount[12]++;
                }
                if (dbData.get(i).exercises[13] == 1) {
                    exercisesCount[13]++;
                }
                if (dbData.get(i).exercises[14] == 1) {
                    exercisesCount[14]++;
                }
                if (dbData.get(i).exercises[15] == 1) {
                    exercisesCount[15]++;
                }
                if (dbData.get(i).exercises[16] == 1) {
                    exercisesCount[16]++;
                }
                if (dbData.get(i).exercises[17] == 1) {
                    exercisesCount[17]++;
                }
                if (dbData.get(i).exercises[18] == 1) {
                    exercisesCount[18]++;
                }
                if (dbData.get(i).exercises[19] == 1) {
                    exercisesCount[19]++;
                }
            }
        }
    }

    public void startMyThread2(){
        MyThread2 thread2 = new MyThread2();
        thread2.start();
    }*/

    public void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag) {
            ft.add(R.id.container, fragment);
        } else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }

    public void setNavButtonAndLoadFrag(int id, Fragment fragment) {
        bottom_nav.setSelectedItemId(id);
        loadFragment(fragment, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btn_home) {
                    loadFragment(new HomeFragment(), true);
                } else if (id == R.id.btn_exercises) {
                    loadFragment(new ExercisesFragment(), false);
                } else if (id == R.id.btn_start) {
                    loadFragment(new StartFragment(), false);
                } else {
                    loadFragment(new RecordFragment(), false);
                }
                return true;
            }
        });
        bottom_nav.setSelectedItemId(R.id.btn_home);

        startMyThread();
//        startMyThread2();

    }

    public ArrayList<ShowExImagesModel> getData() {
        return arrData;
    }
    /*public int[] getExTimesData(){
        return exercisesCount;
    }*/

}