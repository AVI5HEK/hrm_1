package com.framgia.hrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.framgia.hrm.R;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Activity;
import com.framgia.hrm.model.Department;
import com.framgia.hrm.model.Position;
import com.framgia.hrm.model.Staff;
import com.framgia.hrm.model.Status;

public class StaffDetail extends AppCompatActivity {
    private TextView mName, mDate_birth, mBirth_place, mPhone_number, mDept, mStatus, mActivity,
            mPosition;
    private String EXTRA_ID = "id";
    DatabaseHelper mDatabaseHelper;
    long staff_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabaseHelper = new DatabaseHelper(this);
        mName = (TextView) findViewById(R.id.name);
        mDate_birth = (TextView) findViewById(R.id.date_birth);
        mBirth_place = (TextView) findViewById(R.id.birth_place);
        mPhone_number = (TextView) findViewById(R.id.phone_number);
        mDept = (TextView) findViewById(R.id.text_dept);
        mStatus = (TextView) findViewById(R.id.text_status);
        mActivity = (TextView) findViewById(R.id.text_act);
        mPosition = (TextView) findViewById(R.id.text_pos);
        Bundle extras = getIntent().getExtras();
        staff_id = extras.getLong("ID");
        if (staff_id == 0)
        Log.e("staff id ", " " + staff_id);
        Staff staff = mDatabaseHelper.getStaffById(staff_id);
        mName.setText("Name: " + staff.getName());
        mDate_birth.setText("Date of birth: " + staff.getDate_of_birth());
        mBirth_place.setText("Birth place: " + staff.getBirth_place());
        //mDate_birth.setText(staff.getDate_of_birth());
        //mBirth_place.setText(staff.getBirth_place());
        mPhone_number.setText("Phone number: " + staff.getPhone_number());
        Department dept = new Department();
        dept = mDatabaseHelper.getDepartmentById(staff.getDept_id());
        mDept.setText("Department: " + dept.getDept_name());
        Status status = new Status();
        status = mDatabaseHelper.getStatusById(staff.getStatus_id());
        mStatus.setText("Status: " + status.getStatus_name());
        Activity act = new Activity();
        act = mDatabaseHelper.getActivityById(staff.getActivity_id());
        mActivity.setText("Activity: " + act.getActivity_name());
        Position pos = new Position();
        pos = mDatabaseHelper.getPositionById(staff.getPosition_id());
        mPosition.setText("Position: " + pos.getPosition_name());
        mDatabaseHelper.closeDB();
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("STAFFDETAIL")) {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("STAFFDETAIL", intent.getStringExtra("STAFFDETAIL"));
                startActivity(i);
                finish();
            }
        }
        super.onBackPressed();
    }
}
