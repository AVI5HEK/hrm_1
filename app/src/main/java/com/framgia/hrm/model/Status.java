package com.framgia.hrm.model;

/**
 * Created by avishek on 11/23/15.
 */
public class Status {
    int status_id;
    String status_name;

    public Status() {
    }

    public Status(String status_name) {
        this.status_name = status_name;
    }

    public Status(int status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
