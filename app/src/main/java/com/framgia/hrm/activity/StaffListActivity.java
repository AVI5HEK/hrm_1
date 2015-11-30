package com.framgia.hrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.framgia.hrm.R;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffListActivity extends AppCompatActivity {
    private static final String BUNDLE_ID = "id";
    private DatabaseHelper mDb;
    private ListView mListView;
    /**extras & bundles*/
    private static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        mDb = new DatabaseHelper(this);
        ArrayList arrayListName = new ArrayList();
        final ArrayList<Integer> arrayListId = new ArrayList<Integer>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong(EXTRA_ID);
            if (id > 0) {
                List<Staff> staffList = mDb.getStaffByDepartment(id);
                for (Staff stf : staffList) {
                    arrayListName.add(stf.getName());
                    arrayListId.add(stf.getStaff_id());
                    Log.d("Name: ", stf.getName());
                    Log.d("ID: ", Integer.toString(stf.getStaff_id()));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                        .simple_list_item_1, arrayListName);
                mListView = (ListView) findViewById(R.id.lv_staffs);
                mListView.setAdapter(arrayAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int idToSearch = arrayListId.get(position);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putLong(BUNDLE_ID, idToSearch);
                        Intent intent = new Intent(getApplicationContext(), com.framgia.hrm.activity
                                .StaffDetail.class);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                    }
                });
            }
        }
        mDb.closeDB();
    }
}
