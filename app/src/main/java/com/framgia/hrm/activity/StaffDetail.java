package com.framgia.hrm.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.framgia.hrm.R;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Staff;

public class StaffDetail extends AppCompatActivity {

    TextView name,date_birth,birth_place,phone_number;
    DatabaseHelper mdatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdatabaseHelper=new DatabaseHelper(this);
        name=(TextView)findViewById(R.id.name);
        date_birth=(TextView)findViewById(R.id.date_birth);
        birth_place=(TextView)findViewById(R.id.birth_place);
        Bundle extras = getIntent().getExtras();
//        int dept_id=extras.getInt("dept_id", 0);
        // int staff_id=extras.getInt("staff_id",0);
        int staff_id=mdatabaseHelper.laststaffid();
        //Log.e("last Staff id :"," id "+staff_id);
        //Intent intent=new Intent();
        //intent.getIntExtra("dept_id",2);
        // Toast.makeText(this,"dept_id : "+dept_id+"  "+staff_id,Toast.LENGTH_LONG).show();
        Staff staff=mdatabaseHelper.getStaffById(staff_id);
        name.setText(staff.getName());
        date_birth.setText(staff.getDate_of_birth());
        birth_place.setText(staff.getBirth_place());
//        phone_number.setText("YAHOOO");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
