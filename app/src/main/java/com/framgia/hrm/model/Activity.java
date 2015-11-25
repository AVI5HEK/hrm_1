package com.framgia.hrm.model;

/**
 * Created by avishek on 11/23/15.
 */
public class Activity {
    int activity_id;
    String activity_name;

    public Activity() {
    }

    public Activity(String activity_name) {
        this.activity_name = activity_name;
    }

    public Activity(int id, String activity_name) {
        this.activity_id = activity_id;
        this.activity_name = activity_name;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }
}
