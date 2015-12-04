package com.framgia.hrm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.framgia.hrm.R;
import com.framgia.hrm.adapter.MyAdapter;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Activity;
import com.framgia.hrm.model.Department;
import com.framgia.hrm.model.Position;
import com.framgia.hrm.model.SearchStaff;
import com.framgia.hrm.model.Status;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private final static int HOME_FRAGMENT = 0;
    private final static int REGISTRATION_FRAGMENT = 1;
    private final static int ABOUT_FRAGMENT = 2;
    private final static int EXIT_FRAGMENT = 3;
    public static SharedPreferences PREF_STATE = null;
    public static SharedPreferences.Editor editor = null;
    int state = 0;
    private static final String EXTRA_ID = "ID";
    private static final String EXTRA_STAFF_DETAIL = "STAFFDETAIL";
    DatabaseHelper mDatabaseHelper;
    MyAdapter adapter;
    ListView list_department;
    Cursor cursor;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        mDatabaseHelper = new DatabaseHelper(this);
        list_department = (ListView) findViewById(R.id.list_department);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_STAFF_DETAIL)) {
                try {
                    searchView.setIconified(false);
                    displayResults(intent.getStringExtra(EXTRA_STAFF_DETAIL));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        PREF_STATE = getApplicationContext().getSharedPreferences("MyPref",
                MODE_PRIVATE);
        int pref_State = PREF_STATE.getInt("state", 0);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);
        if (pref_State != 3) {
            mCreateDepartmentList();
            mCreateStatusList();
            mCreatePositionList();
            mCreateActivityList();
        }
        editor = PREF_STATE.edit();
        editor.putInt("state", 3);
        editor.commit();
        //the commented section may be needed in the future we don't know yet.
        /*if (!mDatabaseHelper.departmentTableExists() && !mDatabaseHelper.statusTableExists() &&
                !mDatabaseHelper.positionTableExists() && !mDatabaseHelper.activityTableExists()) {
            mCreateDepartmentList();
            mCreateStatusList();
            mCreatePositionList();
            mCreateActivityList();
        }*/
        ArrayList<Department> arrayList = mDatabaseHelper.getAllDepartments();
        adapter = new MyAdapter(this, arrayList);
        list_department.setAdapter(adapter);
        list_department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idToSearch = adapter.getItem(position).getDept_id();
                Bundle dataBundle = new Bundle();
                dataBundle.putLong(EXTRA_ID, idToSearch);
                Intent intent = new Intent(getApplicationContext(), com.framgia.hrm.activity
                        .StaffListActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
        mDatabaseHelper.closeDB();
    }

    public void add_data() {
        ArrayList<SearchStaff> arrayList = mDatabaseHelper.data();
        for (SearchStaff staff : arrayList) {
            mDatabaseHelper.insert_searchitem(staff);
        }
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case HOME_FRAGMENT:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case REGISTRATION_FRAGMENT:
                Bundle dataBundle = new Bundle();
                dataBundle.putLong(EXTRA_ID, 0);
                Intent intent = new Intent(getApplicationContext(), AddstaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                break;
            case ABOUT_FRAGMENT:
                Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intentAbout);
                break;
            case EXIT_FRAGMENT:
                exitDialog();
                break;
            default:
                break;
        }
        /*if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
             //set the toolbar title
            getSupportActionBar().setTitle(title);
        }*/
    }

    private void displayResults(String query) throws SQLException {
        final String query_intent = query;
        if (!query_intent.equals("*")) {
            cursor = mDatabaseHelper.searchByInputText((query != null ? query : "@@@@"));
            Log.e("final query: ", query_intent);
            if (cursor != null && cursor.getCount() > 0) {
                Log.e("cursor ", "cname "+mDatabaseHelper.FTS_COLUMN_NAME);
                Log.e("cursor ", "c " + cursor.getCount());
                String[] from = new String[]{mDatabaseHelper.FTS_COLUMN_NAME, mDatabaseHelper.FTS_COLUMN_PHONE,
                        mDatabaseHelper.FTS_BIRTH_PLACE_FIELD, mDatabaseHelper.FTS_BIRTH_DATE_FIELD, mDatabaseHelper.FTS_COLUMN_DEPARTMENT};
                int[] to = new int[]{R.id.search_result_text_view, R.id.result_number, R.id.birth_place, R.id.birth_date, R.id.department_name};
                final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.result_search_item, cursor, from, to, 1);
                if (cursorAdapter.getCount() > 0 && cursor.getCount() > 0)
                    list_department.setAdapter(cursorAdapter);
                cursorAdapter.notifyDataSetChanged();
                list_department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "selectedName :" + id, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplication(), StaffDetailActivity.class);
                        intent.putExtra(EXTRA_ID, id);
                        intent.putExtra(EXTRA_STAFF_DETAIL, query_intent);
                        startActivity(intent);
                        searchView.setQuery("", true);
                    }
                });

            } else {
                list_department.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "data found not", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No data found !!!!!", Toast.LENGTH_LONG).show();
            list_department.setAdapter(null);
            list_department.setAdapter(adapter);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            displayResults(query + "*");
            // showResults(query + "*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()) {
            try {
                displayResults(newText + "*");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            list_department.setAdapter(adapter);
        return false;
    }

    private void exitDialog() {
        // dialog box
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.activity_main_alert_dialog_title)
                .setMessage(R.string.activity_main_alert_dialog_message)
                .setPositiveButton(R.string.activity_main_alert_dialog_yes, new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton(R.string.activity_staff_list_alert_dialog_no, null)
                .show();
    }

    private void mCreateDepartmentList() {
        mDatabaseHelper.createDepartment(new Department("HRM & ADMIN"));
        mDatabaseHelper.createDepartment(new Department("FINANCE & ACCOUNTS"));
        mDatabaseHelper.createDepartment(new Department("SELLS"));
        mDatabaseHelper.createDepartment(new Department("MARKETING"));
        mDatabaseHelper.createDepartment(new Department("OPERATIONS"));
    }

    private void mCreateStatusList() {
        mDatabaseHelper.createStatus(new Status("TRAINEE"));
        mDatabaseHelper.createStatus(new Status("INTERNSHIP"));
        mDatabaseHelper.createStatus(new Status("OFFICIAL STAFF"));
    }

    private void mCreatePositionList() {
        mDatabaseHelper.createPosition(new Position("CEO"));
        mDatabaseHelper.createPosition(new Position("MANAGER"));
        mDatabaseHelper.createPosition(new Position("DEPUTY MANAGER"));
        mDatabaseHelper.createPosition(new Position("SR. EXECUTIVE"));
        mDatabaseHelper.createPosition(new Position("EXECUTIVE"));
    }

    private void mCreateActivityList() {
        mDatabaseHelper.createActivity(new Activity("ACTIVE"));
        mDatabaseHelper.createActivity(new Activity("INACTIVE"));
    }

    @Override
    public boolean onClose() {
        return false;
    }
}
