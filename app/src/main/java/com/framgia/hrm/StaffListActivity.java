package com.framgia.hrm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.framgia.hrm.helper.DatabaseHelper;
import com.framgia.hrm.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffListActivity extends AppCompatActivity {
    private DatabaseHelper mDb;
    private ListView mListView;
    /**extras & bundles*/
    private static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        mDb = new DatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong(EXTRA_ID);
            if (id > 0) {
                List<Staff> staffList = mDb.getStaffByDepartment(id);
                ArrayList arrayListName = new ArrayList();
                for (Staff stf : staffList) {
                    arrayListName.add(stf.getName());
                    Log.e("Name: ", stf.getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                        .simple_list_item_1, arrayListName);
                mListView = (ListView) findViewById(R.id.lv_staffs);
                mListView.setAdapter(arrayAdapter);
            }
        }
        mDb.closeDB();
    }
}
