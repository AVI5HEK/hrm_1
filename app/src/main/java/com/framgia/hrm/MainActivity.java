package com.framgia.hrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.framgia.hrm.helper.DatabaseHelper;
import com.framgia.hrm.model.Department;
import com.framgia.hrm.model.Position;
import com.framgia.hrm.model.Staff;
import com.framgia.hrm.model.Status;
import com.framgia.hrm.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mDb;
    private ListView mListView;
    private static final String BUNDLE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mDb = new DatabaseHelper(getApplicationContext());
        /**Creating departments*/
        Department dept1 = new Department("Department1");
        Department dept2 = new Department("Department2");
        Department dept3 = new Department("Department3");
        Department dept4 = new Department("Department4");
        Department dept5 = new Department("Department5");
        /**creating status*/
        Status status1 = new Status("S1");
        Status status2 = new Status("S2");
        /**creating position*/
        Position pos1 = new Position("P1");
        Position pos2 = new Position("P2");
        Position pos3 = new Position("P3");
        /**creating activity*/
        Activity act1 = new Activity("A1");
        Activity act2 = new Activity("A2");
        /**creating staff*/
        Staff staff1 = new Staff("Staff1", "B", "C", "D", 1, 1, 1, 1);
        Staff staff2 = new Staff("Staff2", "F", "G", "H", 2, 2, 2, 2);
        Staff staff3 = new Staff("Staff3", "J", "K", "L", 4, 2, 1, 2);
        Staff staff4 = new Staff("Staff4", "N", "O", "P", 5, 1, 2, 1);
        Staff staff5 = new Staff("Staff5", "B", "C", "D", 2, 2, 2, 2);
        Staff staff6 = new Staff("Staff6", "F", "G", "H", 4, 5, 1, 2);
        Staff staff7 = new Staff("Staff7", "J", "K", "L", 5, 1, 2, 1);
        Staff staff8 = new Staff("Staff8", "N", "O", "P", 1, 1, 1, 1);
        Staff staff9 = new Staff("Staff9", "B", "C", "D", 4, 5, 1, 2);
        Staff staff10 = new Staff("Staff10", "F", "G", "H", 5, 1, 2, 1);
        Staff staff11 = new Staff("Staff11", "J", "K", "L", 1, 1, 1, 1);
        Staff staff12 = new Staff("Staff12", "N", "O", "P", 2, 2, 2, 2);
        Staff staff13 = new Staff("Staff13", "B", "C", "D", 4, 2, 1, 2);
        Staff staff14 = new Staff("Staff14", "F", "G", "H", 1, 1, 1, 1);
        Staff staff15 = new Staff("Staff15", "J", "K", "L", 2, 2, 2, 2);
        Staff staff16 = new Staff("Staff16", "N", "O", "P", 4, 2, 1, 2);
        /**checks if the tables exist in the database. If not, creates them and inserts dummy
         * data. This is just for the testing purpose. In the final version of the app, the
         * 'staff' table will be populated by the inputs from GUI, which is not yet
         * implemented. In that case, 'department', 'status', 'position' and 'activity' table will
         * be created only once when the app runs for the first time after installation. The
         * values of these tables will be hardcoded in the MainActivity*/
        if (!mDb.departmentTableExists() && !mDb.statusTableExists() && !mDb.positionTableExists()
                && !mDb.activityTableExists() && !mDb.staffTableExists()) {
            /**inserting departments into mDb*/
            long dept1_id = mDb.createDepartment(dept1);
            long dept2_id = mDb.createDepartment(dept2);
            long dept3_id = mDb.createDepartment(dept3);
            long dept4_id = mDb.createDepartment(dept4);
            long dept5_id = mDb.createDepartment(dept5);
            /**inserting status into mDb*/
            long status1_id = mDb.createStatus(status1);
            long status2_id = mDb.createStatus(status2);
            long position1_id = mDb.createPosition(pos1);
            long position2_id = mDb.createPosition(pos2);
            long position3_id = mDb.createPosition(pos3);
            /**inserting activity into mDb*/
            long activity1_id = mDb.createActivity(act1);
            long activity2_id = mDb.createActivity(act2);
            /**inserting staff into mDb*/
            long staff1_id = mDb.createStaff(staff1);
            long staff2_id = mDb.createStaff(staff2);
            long staff3_id = mDb.createStaff(staff3);
            long staff4_id = mDb.createStaff(staff4);
            long staff5_id = mDb.createStaff(staff5);
            long staff6_id = mDb.createStaff(staff6);
            long staff7_id = mDb.createStaff(staff7);
            long staff8_id = mDb.createStaff(staff8);
            long staff9_id = mDb.createStaff(staff9);
            long staff10_id = mDb.createStaff(staff10);
            long staff11_id = mDb.createStaff(staff11);
            long staff12_id = mDb.createStaff(staff12);
            long staff13_id = mDb.createStaff(staff13);
            long staff14_id = mDb.createStaff(staff14);
            long staff15_id = mDb.createStaff(staff15);
            long staff16_id = mDb.createStaff(staff16);
        }
        /**getting all department names*/
        List<Department> allDept = mDb.getAllDepartments();
        ArrayList arrayListDeptName = new ArrayList();
        for (Department dept : allDept) {
            Log.d("Dept Name", dept.getDept_name());
            arrayListDeptName.add(dept.getDept_name());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                arrayListDeptName);
        mListView = (ListView) findViewById(R.id.lv_departments);
        mListView.setAdapter(arrayAdapter);
        mDb.closeDB();
        /**show staffs under the selected department in the next activity*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idToSearch = position + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putLong(BUNDLE_ID, idToSearch);
                Intent intent = new Intent(getApplicationContext(), com.framgia.hrm
                        .StaffListActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
