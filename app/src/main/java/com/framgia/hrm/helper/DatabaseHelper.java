package com.framgia.hrm.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.framgia.hrm.model.Activity;
import com.framgia.hrm.model.Department;
import com.framgia.hrm.model.Position;
import com.framgia.hrm.model.Staff;
import com.framgia.hrm.model.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avishek on 11/23/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HRM.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * table names
     */
    private static final String TABLE_DEPARTMENT = "department";
    private static final String TABLE_STAFF = "staff";
    private static final String TABLE_STATUS = "status";
    private static final String TABLE_POSITION = "position";
    private static final String TABLE_ACTIVITY = "activity";

    /**
     * Department table column names
     */
    private static final String DEPARTMENT_COLUMN_ID = "dept_id";
    private static final String DEPARTMENT_COLUMN_NAME = "dept_name";

    /**
     * Status table column names
     */
    private static final String STATUS_COLUMN_ID = "status_id";
    private static final String STATUS_COLUMN_NAME = "status_name";

    /**
     * Position table column names
     */
    private static final String POSITION_COLUMN_ID = "position_id";
    private static final String POSITION_COLUMN_NAME = "position_name";

    /**
     * Activity table column names
     */
    private static final String ACTIVITY_COLUMN_ID = "activity_id";
    private static final String ACTIVITY_COLUMN_NAME = "activity_name";

    /**
     * Staff table column names
     */
    private static final String STAFF_COLUMN_ID = "staff_id";
    private static final String STAFF_COLUMN_NAME = "name";
    private static final String STAFF_COLUMN_DATE_OF_BIRTH = "date_of_birth";
    private static final String STAFF_COLUMN_BIRTH_PLACE = "birth_place";
    private static final String STAFF_COLUMN_PHONE_NUMBER = "phone_number";
    private static final String STAFF_COLUMN_DEPT = DEPARTMENT_COLUMN_ID;
    private static final String STAFF_COLUMN_STATUS = STATUS_COLUMN_ID;
    private static final String STAFF_COLUMN_ACTIVITY = ACTIVITY_COLUMN_ID;
    private static final String STAFF_COLUMN_POSITION = POSITION_COLUMN_ID;

    /**
     * department table create statement
     */
    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT
            + "(" + DEPARTMENT_COLUMN_ID + " INTEGER PRIMARY KEY, " + DEPARTMENT_COLUMN_NAME + " " +
            "text)";

    /**
     * status table create statement
     */
    private static final String CREATE_TABLE_STATUS = "CREATE TABLE " + TABLE_STATUS
            + "(" + STATUS_COLUMN_ID + " INTEGER PRIMARY KEY, " + STATUS_COLUMN_NAME + " " +
            "text)";

    /**
     * position table create statement
     */
    private static final String CREATE_TABLE_POSITION = "CREATE TABLE " + TABLE_POSITION
            + "(" + POSITION_COLUMN_ID + " INTEGER PRIMARY KEY, " + POSITION_COLUMN_NAME + " " +
            "text)";

    /**
     * activity table create statement
     */
    private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE " + TABLE_ACTIVITY
            + "(" + ACTIVITY_COLUMN_ID + " INTEGER PRIMARY KEY, " + ACTIVITY_COLUMN_NAME + " " +
            "text)";

    /**
     * staff table create statement
     */
    /*private static final String CREATE_TABLE_STAFF = "CREATE TABLE " + TABLE_STAFF
            + "(" + STAFF_COLUMN_ID + " INTEGER PRIMARY KEY, " + STAFF_COLUMN_NAME + " " +
            "TEXT, " + STAFF_COLUMN_DATE_OF_BIRTH + " TEXT, " + STAFF_COLUMN_BIRTH_PLACE + " TEXT, " +
            "" + STAFF_COLUMN_PHONE_NUMBER + " TEXT, " + STAFF_COLUMN_DEPT + " INTEGER, " +
            "" + STAFF_COLUMN_STATUS + " INTEGER, " + STAFF_COLUMN_ACTIVITY + " INTEGER, " +
            "" + STAFF_COLUMN_POSITION + " INTEGER)";*/
    private static final String CREATE_TABLE_STAFF = "CREATE TABLE " + TABLE_STAFF
            + "(" + STAFF_COLUMN_ID + " INTEGER PRIMARY KEY, " + STAFF_COLUMN_NAME + " " +
            "TEXT, " + STAFF_COLUMN_DATE_OF_BIRTH + " TEXT, " + STAFF_COLUMN_BIRTH_PLACE + " TEXT, " +
            "" + STAFF_COLUMN_PHONE_NUMBER + " TEXT, " + STAFF_COLUMN_DEPT + " INTEGER, " +
            "" + STAFF_COLUMN_STATUS + " INTEGER, " + STAFF_COLUMN_ACTIVITY + " INTEGER, " +
            "" + STAFF_COLUMN_POSITION + " INTEGER, FOREIGN KEY (" + STAFF_COLUMN_DEPT + ") " +
            "REFERENCES " + TABLE_DEPARTMENT + "(" + DEPARTMENT_COLUMN_ID + "), FOREIGN KEY" +
            "(" + STAFF_COLUMN_STATUS + ") REFERENCES " + TABLE_STATUS + "(" + STATUS_COLUMN_ID +
            "), FOREIGN" +
            " KEY(" + STAFF_COLUMN_ACTIVITY + ") REFERENCES " + TABLE_ACTIVITY + "" +
            "(" + ACTIVITY_COLUMN_ID + "), FOREIGN KEY (" + STAFF_COLUMN_POSITION + ") REFERENCES " +
            "" + TABLE_POSITION + "(" + POSITION_COLUMN_ID + "))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_STATUS);
        db.execSQL(CREATE_TABLE_POSITION);
        db.execSQL(CREATE_TABLE_ACTIVITY);
        db.execSQL(CREATE_TABLE_STAFF);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        /**Create new tables*/
        onCreate(db);
    }

    /**
     * Creating department
     */
    public long createDepartment(Department dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEPARTMENT_COLUMN_NAME, dept.getDept_name());
        long dept_id = db.insert(TABLE_DEPARTMENT, null, values);
        return dept_id;
    }

    /**
     * Creating status
     */
    public long createStatus(Status status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS_COLUMN_NAME, status.getStatus_name());
        long status_id = db.insert(TABLE_STATUS, null, values);
        return status_id;
    }

    /**
     * Creating Position
     */
    public long createPosition(Position pos) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POSITION_COLUMN_NAME, pos.getPosition_name());
        long pos_id = db.insert(TABLE_POSITION, null, values);
        return pos_id;
    }

    /**
     * Creating activity
     */
    public long createActivity(Activity act) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTIVITY_COLUMN_NAME, act.getActivity_name());
        long act_id = db.insert(TABLE_ACTIVITY, null, values);
        return act_id;
    }

    /**
     * Creating staff
     */
    public long createStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_COLUMN_NAME, staff.getName());
        values.put(STAFF_COLUMN_DATE_OF_BIRTH, staff.getDate_of_birth());
        values.put(STAFF_COLUMN_BIRTH_PLACE, staff.getBirth_place());
        values.put(STAFF_COLUMN_PHONE_NUMBER, staff.getPhone_number());
        values.put(STAFF_COLUMN_DEPT, staff.getDept_id());
        values.put(STAFF_COLUMN_STATUS, staff.getStatus_id());
        values.put(STAFF_COLUMN_ACTIVITY, staff.getActivity_id());
        values.put(STAFF_COLUMN_POSITION, staff.getPosition_id());
        long staff_id = db.insert(TABLE_STAFF, null, values);
        return staff_id;
    }

    /**Reader methods*/

    /**
     * Getting a single Department
     */
    /*public Department getDepartment(long dept_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEPARTMENT + " WHERE " + DEPARTMENT_COLUMN_ID + " = " +
                dept_id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) c.moveToFirst();
        Department dept = new Department();
        dept.setDept_id(c.getInt(c.getColumnIndex(DEPARTMENT_COLUMN_ID)));
        dept.setDept_name(c.getString(c.getColumnIndex(DEPARTMENT_COLUMN_NAME)));
        return dept;
    }*/

    /**
     * Getting all the Departments
     */
    public List<Department> getAllDepartments() {
        ArrayList<Department> departments = new ArrayList<Department>();
        String selectQuery = "SELECT * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        /**looping through all rows and adding to list*/
        if (c.moveToFirst()) {
            do {
                Department dept = new Department();
                dept.setDept_id(c.getInt(c.getColumnIndex(DEPARTMENT_COLUMN_ID)));
                dept.setDept_name(c.getString(c.getColumnIndex(DEPARTMENT_COLUMN_NAME)));

                departments.add(dept);
            } while (c.moveToNext());
        }
        return departments;
    }

    /**
     * Getting staffs by department
     */
    public List<Staff> getStaffByDepartment(long dept_id) {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF + " WHERE " + STAFF_COLUMN_DEPT + " =" +
                " " + dept_id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                Staff staff = new Staff();
                staff.setStaff_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ID)));
                staff.setName(c.getString(c.getColumnIndex(STAFF_COLUMN_NAME)));
                staff.setDate_of_birth(c.getString(c.getColumnIndex(STAFF_COLUMN_DATE_OF_BIRTH)));
                staff.setBirth_place(c.getString(c.getColumnIndex(STAFF_COLUMN_BIRTH_PLACE)));
                staff.setPhone_number(c.getString(c.getColumnIndex(STAFF_COLUMN_PHONE_NUMBER)));
                staff.setDept_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_DEPT)));
                staff.setStatus_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_STATUS)));
                staff.setActivity_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ACTIVITY)));
                staff.setPosition_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_POSITION)));
                staffs.add(staff);
            } while (c.moveToNext());
        }
        return staffs;
    }
    /*public ArrayList getStaffByDepartment(long dept_id) {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF + " INNER JOIN " + TABLE_DEPARTMENT + " " +
                "ON " + TABLE_STAFF + "." + STAFF_COLUMN_DEPT + " = " + TABLE_DEPARTMENT + "" +
                "." + DEPARTMENT_COLUMN_ID + "WHERE " + TABLE_STAFF + "." + STAFF_COLUMN_DEPT + " = " +
                dept_id;

        Cursor c = db.rawQuery(selectQuery, null);
        ArrayList arrayListName = new ArrayList();
        c.moveToFirst();
        while(c.isAfterLast() == false){
            arrayListName.add(c.getString(c.getColumnIndex("staff_id")));
            arrayListName.add(c.getString(c.getColumnIndex("name")));
        }

        *//*if (c != null) c.moveToFirst();

        if (c.moveToFirst()) {
            do {
                Staff staff = new Staff();
                staff.setStaff_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ID)));
                staff.setName(c.getString(c.getColumnIndex(STAFF_COLUMN_NAME)));
                staff.setDate_of_birth(c.getString(c.getColumnIndex(STAFF_COLUMN_DATE_OF_BIRTH)));
                staff.setBirth_place(c.getString(c.getColumnIndex(STAFF_COLUMN_BIRTH_PLACE)));
                staff.setPhone_number(c.getString(c.getColumnIndex(STAFF_COLUMN_PHONE_NUMBER)));
                staff.setDept_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_DEPT)));
                staff.setStatus_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_STATUS)));
                staff.setActivity_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ACTIVITY)));
                staff.setPosition_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_POSITION)));

                staffs.add(staff);
            } while (c.moveToNext());
        }

        return staffs;*//*
        return arrayListName;
    }*/

    /**the tables below will be used later*/
    /**
     * Getting staff by id
     */
    public Staff getStaffById(long staff_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF + " WHERE " + STAFF_COLUMN_ID + " =" +
                " " + staff_id;
        Cursor c = db.rawQuery(selectQuery, null);
        Staff staff = new Staff();
        if (c != null) c.moveToFirst();
        staff.setStaff_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ID)));
        staff.setName(c.getString(c.getColumnIndex(STAFF_COLUMN_NAME)));
        staff.setDate_of_birth(c.getString(c.getColumnIndex(STAFF_COLUMN_DATE_OF_BIRTH)));
        staff.setBirth_place(c.getString(c.getColumnIndex(STAFF_COLUMN_BIRTH_PLACE)));
        staff.setPhone_number(c.getString(c.getColumnIndex(STAFF_COLUMN_PHONE_NUMBER)));
        staff.setDept_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_DEPT)));
        staff.setStatus_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_STATUS)));
        staff.setActivity_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_ACTIVITY)));
        staff.setPosition_id(c.getInt(c.getColumnIndex(STAFF_COLUMN_POSITION)));
        return staff;
    }

    /**
     * Getting department by id
     */
    public Department getDepartmentById(long dept_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DEPARTMENT + " WHERE " +
                DEPARTMENT_COLUMN_ID + " = " + dept_id;
        Cursor c = db.rawQuery(selectQuery, null);
        Department dept = new Department();
        if (c != null) c.moveToFirst();
        dept.setDept_id(c.getInt(c.getColumnIndex(DEPARTMENT_COLUMN_ID)));
        dept.setDept_name(c.getString(c.getColumnIndex(DEPARTMENT_COLUMN_NAME)));
        return dept;
    }

    /**
     * Getting status by id
     */
    public Status getStatusById(long status_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STATUS + " WHERE " + STATUS_COLUMN_ID +
                " =" +
                " " + status_id;
        Cursor c = db.rawQuery(selectQuery, null);
        Status status = new Status();
        if (c != null) c.moveToFirst();
        status.setStatus_id(c.getInt(c.getColumnIndex(STATUS_COLUMN_ID)));
        status.setStatus_name(c.getString(c.getColumnIndex(STATUS_COLUMN_NAME)));
        return status;
    }

    /**
     * Getting position by id
     */
    public Position getPositionById(long pos_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_POSITION + " WHERE " + POSITION_COLUMN_ID +
                " = " + pos_id;
        Cursor c = db.rawQuery(selectQuery, null);
        Position pos = new Position();
        if (c != null) c.moveToFirst();
        pos.setPosition_id(c.getInt(c.getColumnIndex(POSITION_COLUMN_ID)));
        pos.setPosition_name(c.getString(c.getColumnIndex(POSITION_COLUMN_NAME)));
        return pos;
    }

    /**
     * Getting activity by id
     */
    public Activity getActivityById(long act_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ACTIVITY + " WHERE " + ACTIVITY_COLUMN_ID +
                " = " + act_id;
        Cursor c = db.rawQuery(selectQuery, null);
        Activity act = new Activity();
        if (c != null) c.moveToFirst();
        act.setActivity_id(c.getInt(c.getColumnIndex(ACTIVITY_COLUMN_ID)));
        act.setActivity_name(c.getString(c.getColumnIndex(ACTIVITY_COLUMN_NAME)));
        return act;
    }

    /** Update methods */

    /**
     * Update staff by id
     */
    public int updateStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_COLUMN_NAME, staff.getName());
        values.put(STAFF_COLUMN_DATE_OF_BIRTH, staff.getDate_of_birth());
        values.put(STAFF_COLUMN_BIRTH_PLACE, staff.getBirth_place());
        values.put(STAFF_COLUMN_PHONE_NUMBER, staff.getPhone_number());
        values.put(STAFF_COLUMN_DEPT, staff.getDept_id());
        values.put(STAFF_COLUMN_STATUS, staff.getStatus_id());
        values.put(STAFF_COLUMN_ACTIVITY, staff.getActivity_id());
        values.put(STAFF_COLUMN_POSITION, staff.getPosition_id());

        // updating row
        return db.update(TABLE_STAFF, values, STAFF_COLUMN_ID + " = ?",
                new String[]{String.valueOf(staff.getStaff_id())});
    }
    /** Delete methods */

    /**
     * Delete staff by id
     */
    public void deleteStaff(long staff_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STAFF, STAFF_COLUMN_ID + " = ?",
                new String[]{String.valueOf(staff_id)});
    }

    /**
     * Checks if department table exists
     */
    public boolean departmentTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_DEPARTMENT + "", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                cur.close();
                return false;
            }
        } else {
            cur.close();
            return false;
        }
    }

    /**
     * Checks if status table exists
     */
    public boolean statusTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_STATUS + "", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                cur.close();
                return false;
            }
        } else {
            cur.close();
            return false;
        }
    }

    /**
     * Checks if position table exists
     */
    public boolean positionTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_POSITION + "", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                cur.close();
                return false;
            }
        } else {
            cur.close();
            return false;
        }
    }

    /**
     * Checks if activity table exists
     */
    public boolean activityTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_ACTIVITY + "", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                cur.close();
                return false;
            }
        } else {
            cur.close();
            return false;
        }
    }

    /**
     * Checks if staff table exists
     */
    public boolean staffTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select * from " + TABLE_STAFF + "", null);
        if (cur != null) {
            if (cur.getCount() > 0) {
                cur.close();
                return true;
            } else {
                cur.close();
                return false;
            }
        } else {
            cur.close();
            return false;
        }
    }

    /**
     * closing database
     */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
