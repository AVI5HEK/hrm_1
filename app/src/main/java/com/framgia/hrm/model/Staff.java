package com.framgia.hrm.model;

/**
 * Created by avishek on 11/23/15.
 */
public class Staff {
    int staff_id;
    String name;
    String date_of_birth;
    String birth_place;
    String phone_number;
    int dept_id;
    int status_id;
    int activity_id;
    int position_id;

    public Staff(){}

    public Staff(String name){
        this.name = name;
    }

    public Staff(int staff_id, String name) {
        this.staff_id = staff_id;
        this.name = name;
    }

    public Staff(String name, String date_of_birth, String birth_place, String phone_number, int dept_id, int status_id, int activity_id, int position_id) {
        //this.staff_id = staff_id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.birth_place = birth_place;
        this.phone_number = phone_number;
        this.dept_id = dept_id;
        this.status_id = status_id;
        this.activity_id = activity_id;
        this.position_id = position_id;
    }
    public Staff(String name, String date_of_birth, String birth_place, String phone_number){
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.birth_place = birth_place;
        this.phone_number = phone_number;

    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }
}
