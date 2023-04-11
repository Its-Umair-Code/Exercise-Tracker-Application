package com.example.exercisetrackeritsumaircode;

public class DatabaseModel {

    int sr_no;
    String date,time;
    int[] exercises;
    public DatabaseModel(int sr_no, String date, String time, int[] exercises){
        this.sr_no = sr_no;
        this.date = date;
        this.time = time;
        this.exercises = exercises;
    }

    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
}
