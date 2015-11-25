package com.framgia.hrm.model;

/**
 * Created by avishek on 11/23/15.
 */
public class Department {
    int dept_id;
    String dept_name;

    public Department() {
    }

    public Department(String dept_name) {
        this.dept_name = dept_name;
    }

//    public Department(int dept_id, String dept_name) {
//        this.dept_id = dept_id;
//        this.dept_name = dept_name;
//    }

    public int getDept_id() {
        return this.dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return this.dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
