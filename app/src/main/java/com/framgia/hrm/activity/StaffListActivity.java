package com.framgia.hrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.framgia.hrm.R;
import com.framgia.hrm.adapter.StaffListAdapter;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffListActivity extends AppCompatActivity {
    private static final String BUNDLE_ID = "id";
    private DatabaseHelper mDb;
    private ListView mListView;
    /**
     * extras & bundles
     */
    private static final String EXTRA_ID = "id";
    private StaffListAdapter mStaffListAdapter;
    List<Staff> staffList;
    int position=0;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        mDb = new DatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getLong(EXTRA_ID);
            if (id > 0) {
                Log.e("stafflist ","id "+id);
                staffList=mDb.getStaffByPaging(id,position);
                mStaffListAdapter = new StaffListAdapter(this, (ArrayList) staffList);
                mListView = (ListView) findViewById(R.id.lv_staffs);
                mListView.setAdapter(mStaffListAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int idToSearch = staffList.get(position).getStaff_id();
                        Bundle dataBundle = new Bundle();
                        dataBundle.putLong(BUNDLE_ID, idToSearch);
                        Intent intent = new Intent(getApplicationContext(), StaffDetail.class);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                        Log.d("idToSearch", idToSearch + " ");
                    }
                });
                mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        int idToSearch = staffList.get(position).getStaff_id();
                        Bundle dataBundle = new Bundle();
                        dataBundle.putLong(BUNDLE_ID, idToSearch);
                        Intent intent = new Intent(getApplicationContext(), Addstaff.class);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                        return true;
                    }
                });
                mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        int first = view.getFirstVisiblePosition();
                        int count = view.getChildCount();
                        if (scrollState == SCROLL_STATE_IDLE || (first + count > mStaffListAdapter.getCount()) ) {
                            mListView.invalidateViews();
                            position=mStaffListAdapter.getCount();
                            ArrayList<Staff>arrayList= mDb.getStaffByPaging(id, position);
                            for(int i=0;i<arrayList.size(); i++) {
                                staffList.add(arrayList.get(i));
                            }
                            mStaffListAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });

            }
        }
        mDb.closeDB();
    }
    @Override
    protected void onResume() {
     ArrayList<Staff>staffs= (ArrayList<Staff>) mDb.getStaffByDepartment(id);
        int size=staffList.size();
        staffList.clear();
        for(int i=0;i<size;i++){
          staffList.add(staffs.get(i));
        }
        mListView.setAdapter(null);
        mListView.setAdapter(mStaffListAdapter);
        mStaffListAdapter.notifyDataSetChanged();
        super.onResume();
    }
}