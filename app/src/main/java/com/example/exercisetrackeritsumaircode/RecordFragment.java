package com.example.exercisetrackeritsumaircode;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class RecordFragment extends Fragment {

    MyExercisesDatabase db;
    ArrayList<DatabaseModel> dbData;
    TextView totalDaysTxt, maxStreakTxt, currentStreakTxt;
    ArrayList<RecordImagesModel> imagesTimes = new ArrayList<>();
    RecyclerView recyclerView;
    RecordImagesAdapter recordImagesAdapter;
    ArrayList<Integer> days = new ArrayList<>();
    ImageView imageGallery, imageViewPic;
    public static final int REQUEST_CODE_PICK_IMAGE = 100;
    private static final int GALLERY_REQUEST_CODE = 200;

    public RecordFragment() {
        // Required empty public constructor
    }

    public byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public byte[] convertImageToByteArray(int image) {

        // Convert image from drawable to bitmap :
        Bitmap bit = BitmapFactory.decodeResource(getResources(), image);

        // Convert bitmap to byte array :
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();

    }

    public Bitmap convertByteArrayToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_record, container, false);

        totalDaysTxt = v.findViewById(R.id.totalDaysTxt);
        maxStreakTxt = v.findViewById(R.id.maxStreakTxt);
        currentStreakTxt = v.findViewById(R.id.currentStreakTxt);
        imageGallery = v.findViewById(R.id.imageGallery);
        imageViewPic = v.findViewById(R.id.imageView2);
        recyclerView = v.findViewById(R.id.recordImagesRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
            }
        });

        SharedPreferences shre = requireContext().getSharedPreferences("ImageStore", MODE_PRIVATE);
        String previouslyEncodedImage = shre.getString("image", "");

        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageViewPic.setImageBitmap(bitmap);

        } else {
            imageViewPic.setImageResource(R.drawable.mypic);
        }

        db = new MyExercisesDatabase(getContext());
        dbData = db.fetchData();


        int totalDays = dbData.size();
        totalDaysTxt.setText(Integer.toString(totalDays));

        for (int i = 0; i < dbData.size(); i++) {
            days.add(Integer.parseInt(dbData.get(i).getDate().substring(0, 2)));
        }

        if (days.size() > 0) {
            int maximumStreak = 1;
            int currentStreak = 1;
            int check = 1;

            for (int i = 0; i < days.size() - 1; i++) {
                int currentDate = days.get(i);
                int nextDate = days.get(i + 1);

                if (currentDate == nextDate) {
                    continue;
                } else if ((currentDate + 1) == nextDate) {
                    check++;
                    currentStreak++;
                } else if ((currentDate == 30 || currentDate == 31) && nextDate == 1) {
                    check++;
                    currentStreak++;
                } else {
                    currentStreak = 1;
                    if (maximumStreak < check) {
                        maximumStreak = check;
                    }
                    check = 1;
                }
            }

            if (maximumStreak < check) {
                maximumStreak = check;
            }

            String currDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            String curDate = currDate.substring(0, 2);
            int date = Integer.parseInt(curDate);

            maxStreakTxt.setText(Integer.toString(maximumStreak));

            String lastDate = Integer.toString(days.get((days.size() - 1)));
            String cDate = Integer.toString(date);

            Log.d("lastDate ", lastDate);
            Log.d("currentDate ", cDate);

            if (!lastDate.equals(cDate)) {
                currentStreakTxt.setText("0");
            } else {
                currentStreakTxt.setText(Integer.toString(currentStreak));
            }

        } else {
            maxStreakTxt.setText("0");
            currentStreakTxt.setText("0");
        }

        int[] exercisesCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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

//        int[] exercisesCount = ((MainActivity) requireActivity()).getExTimesData();

        imagesTimes.add(new RecordImagesModel(R.drawable.image4, Integer.toString(exercisesCount[0])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image5, Integer.toString(exercisesCount[1])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image11, Integer.toString(exercisesCount[2])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image20, Integer.toString(exercisesCount[3])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image15, Integer.toString(exercisesCount[4])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image13, Integer.toString(exercisesCount[5])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image7, Integer.toString(exercisesCount[6])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image6, Integer.toString(exercisesCount[7])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image2, Integer.toString(exercisesCount[8])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image3, Integer.toString(exercisesCount[9])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image10, Integer.toString(exercisesCount[10])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image16, Integer.toString(exercisesCount[11])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image9, Integer.toString(exercisesCount[12])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image14, Integer.toString(exercisesCount[13])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image12, Integer.toString(exercisesCount[14])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image8, Integer.toString(exercisesCount[15])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image19, Integer.toString(exercisesCount[16])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image18, Integer.toString(exercisesCount[17])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image17, Integer.toString(exercisesCount[18])));
        imagesTimes.add(new RecordImagesModel(R.drawable.image1, Integer.toString(exercisesCount[19])));

        recordImagesAdapter = new RecordImagesAdapter(getContext(), imagesTimes);
        recyclerView.setAdapter(recordImagesAdapter);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {

            Uri uri = data.getData();

            try {
                // Convert the image uri into bitmap :
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                // To show image first :
                imageViewPic.setImageBitmap(bitmap);
                // convert the bitmap into byteArray :
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] b = stream.toByteArray();
                // Convert the byteArray into String :
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                SharedPreferences pref = requireContext().getSharedPreferences("ImageStore", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("image", encodedImage);
                editor.apply();

                Toast.makeText(getContext(), "Image is added successfully!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}