package com.framgia.hrm.model;

/**
 * Created by avishek on 11/23/15.
 */
public class Position {
    int position_id;
    String position_name;

    public Position() {
    }

    public Position(String position_name) {
        this.position_name = position_name;
    }

    public Position(int position_id, String position_name) {
        this.position_id = position_id;
        this.position_name = position_name;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
}
