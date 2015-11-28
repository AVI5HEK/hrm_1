package com.framgia.hrm.model;

/**
 * Created by ahsan on 11/25/15.
 */
public class SearchStaff {

    public String name;
    public int id;
    public String phone;
    public String birth_place;
    public String birth_date;

    public SearchStaff(int id,String name,String phone,String birth_place,String birth_date){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.birth_place=birth_place;
        this.birth_date=birth_date;

    }
}
