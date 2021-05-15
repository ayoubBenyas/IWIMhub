package com.example.iwimhub.data.model;

import com.google.firebase.firestore.DocumentReference;

public class ModuleModel extends  Model{
    private String code, title;
    private DocumentReference chef;
    private Schedule schedule;

    public ModuleModel(){
        schedule = new Schedule();
    }

    public static class Schedule{
        private String day, startAt, endAt;

        public Schedule(){}

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getStartAt() {
            return startAt;
        }

        public void setStartAt(String startAt) {
            this.startAt = startAt;
        }

        public String getEndAt() {
            return endAt;
        }

        public void setEndAt(String endAt) {
            this.endAt = endAt;
        }

        public String toString(){ return day+", "+toTime(); }

        public String toTime(){ return startAt+" - "+endAt; }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public DocumentReference getChef() {
        return chef;
    }

    public void setChef(DocumentReference chef) {
        this.chef = chef;
    }
}
