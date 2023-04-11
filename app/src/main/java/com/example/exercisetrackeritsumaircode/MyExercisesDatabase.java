package com.example.exercisetrackeritsumaircode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyExercisesDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Exercises Database";
    public static final int DATABASE_VERSION = 1;
    public static final String EXERCISES_TABLE = "Exercises_T";
    public static final String SrNO_COLUM = "Serial_no";
    public static final String DATE_COLUM = "Date";
    public static final String TIME_COLUM = "Time";
    public static final String EX1_COLUM = "Exercise_1";
    public static final String EX2_COLUM = "Exercise_2";
    public static final String EX3_COLUM = "Exercise_3";
    public static final String EX4_COLUM = "Exercise_4";
    public static final String EX5_COLUM = "Exercise_5";
    public static final String EX6_COLUM = "Exercise_6";
    public static final String EX7_COLUM = "Exercise_7";
    public static final String EX8_COLUM = "Exercise_8";
    public static final String EX9_COLUM = "Exercise_9";
    public static final String EX10_COLUM = "Exercise_10";
    public static final String EX11_COLUM = "Exercise_11";
    public static final String EX12_COLUM = "Exercise_12";
    public static final String EX13_COLUM = "Exercise_13";
    public static final String EX14_COLUM = "Exercise_14";
    public static final String EX15_COLUM = "Exercise_15";
    public static final String EX16_COLUM = "Exercise_16";
    public static final String EX17_COLUM = "Exercise_17";
    public static final String EX18_COLUM = "Exercise_18";
    public static final String EX19_COLUM = "Exercise_19";
    public static final String EX20_COLUM = "Exercise_20";

    public MyExercisesDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + '"' + EXERCISES_TABLE + '"' + "("
                + '"' + SrNO_COLUM + '"' + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + '"' + DATE_COLUM + '"' + " TEXT, "
                + '"' + TIME_COLUM + '"' + " TEXT, "
                + '"' + EX1_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX2_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX3_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX4_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX5_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX6_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX7_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX8_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX9_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX10_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX11_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX12_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX13_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX14_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX15_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX16_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX17_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX18_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX19_COLUM + '"' + " INTEGER DEFAULT 0, "
                + '"' + EX20_COLUM + '"' + " INTEGER DEFAULT 0"
                + ");" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + EXERCISES_TABLE);
        onCreate(sqLiteDatabase);

    }

    public void addData(String date, String time, int[] exercises) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATE_COLUM, date);
        values.put(TIME_COLUM, time);
        values.put(EX1_COLUM,exercises[0]);
        values.put(EX2_COLUM,exercises[1]);
        values.put(EX3_COLUM,exercises[2]);
        values.put(EX4_COLUM,exercises[3]);
        values.put(EX5_COLUM,exercises[4]);
        values.put(EX6_COLUM,exercises[5]);
        values.put(EX7_COLUM,exercises[6]);
        values.put(EX8_COLUM,exercises[7]);
        values.put(EX9_COLUM,exercises[8]);
        values.put(EX10_COLUM,exercises[9]);
        values.put(EX11_COLUM,exercises[10]);
        values.put(EX12_COLUM,exercises[11]);
        values.put(EX13_COLUM,exercises[12]);
        values.put(EX14_COLUM,exercises[13]);
        values.put(EX15_COLUM,exercises[14]);
        values.put(EX16_COLUM,exercises[15]);
        values.put(EX17_COLUM,exercises[16]);
        values.put(EX18_COLUM,exercises[17]);
        values.put(EX19_COLUM,exercises[18]);
        values.put(EX20_COLUM,exercises[19]);
        db.insert(EXERCISES_TABLE, null, values);
    }

    public ArrayList<DatabaseModel> fetchData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + EXERCISES_TABLE, null);
        ArrayList<DatabaseModel> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            int[] exercises = new int[20];
            for (int i = 0; i < 20; i++) {
                exercises[i] = cursor.getInt(i + 3);
            }
            data.add(new DatabaseModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), exercises));
        }
        return data;
    }

/*    public ArrayList<DatabaseModel> fetchData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + EXERCISES_TABLE, null);
        ArrayList<DatabaseModel> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(SrNO_COLUM);
            int dateIndex = cursor.getColumnIndex(DATE_COLUM);
            int timeIndex = cursor.getColumnIndex(TIME_COLUM);
            do {
                int[] exercises = new int[20];
                for (int i = 0; i < 20; i++) {
                    int no = i+3;
                    exercises[i] = cursor.getInt(no);
                }
                data.add(new DatabaseModel(cursor.getInt(idIndex), cursor.getString(dateIndex), cursor.getString(timeIndex), exercises));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }*/

}
