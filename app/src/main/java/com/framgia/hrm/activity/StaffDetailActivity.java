package com.framgia.hrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.framgia.hrm.R;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Activity;
import com.framgia.hrm.model.Department;
import com.framgia.hrm.model.Position;
import com.framgia.hrm.model.Staff;
import com.framgia.hrm.model.Status;

public class StaffDetailActivity extends AppCompatActivity {
    private TextView mName, mDate_birth, mBirth_place, mPhone_number, mDept, mStatus, mActivity,
            mPosition;
    private Button mEditButton;
    private static final String EXTRA_ID = "ID";
    private Staff mStaff;
    private static final String EXTRA_STAFF_DETAIL = "STAFFDETAIL";
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
        mEditButton = (Button) findViewById(R.id.staff_detail_btn_edit_usr);
        Bundle extras = getIntent().getExtras();
        /**the commented section was written by Ahsan-san*/
        /*staff_id = extras.getLong("ID");
        if (staff_id == 0)
        Log.e("staff id ", " " + staff_id);*/
        staff_id = extras.getLong(EXTRA_ID);
        mStaff = mDatabaseHelper.getStaffById(staff_id);
        mName.setText(mStaff.getName());
        mDate_birth.setText(mStaff.getDate_of_birth());
        mBirth_place.setText(mStaff.getBirth_place());
        mPhone_number.setText(mStaff.getPhone_number());
        Department dept = mDatabaseHelper.getDepartmentById(mStaff.getDept_id());
        mDept.setText(dept.getDept_name());
        Status status = mDatabaseHelper.getStatusById(mStaff.getStatus_id());
        mStatus.setText(status.getStatus_name());
        Activity act = mDatabaseHelper.getActivityById(mStaff.getActivity_id());
        mActivity.setText(act.getActivity_name());
        Position pos = mDatabaseHelper.getPositionById(mStaff.getPosition_id());
        mPosition.setText(pos.getPosition_name());
        mDatabaseHelper.closeDB();

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idToSearch = mStaff.getStaff_id();
                Bundle dataBundle = new Bundle();
                dataBundle.putLong(EXTRA_ID, idToSearch);
                Intent intent = new Intent(getApplicationContext(), AddstaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_STAFF_DETAIL)) {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra(EXTRA_STAFF_DETAIL, intent.getStringExtra(EXTRA_STAFF_DETAIL));
                startActivity(i);
                finish();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        }
        super.onBackPressed();
    }
}
