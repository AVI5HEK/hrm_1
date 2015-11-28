package com.framgia.hrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,SearchView.OnQueryTextListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private final static int HOME_FRAGMENT = 0;
    private final static int REGISTRATION_FRAGMENT = 1;
    private final static int VIEW_FRAGMENT = 2;
    private final static int UPDATE_FRAGMENT = 3;
    public static SharedPreferences PREF_STATE=null;
    public static SharedPreferences.Editor editor=null;
    int state=0;
    DatabaseHelper mdDatabaseHelper;
    MyAdapter adapter;
    ListView list_department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mdDatabaseHelper=new DatabaseHelper(this);
        list_department=(ListView)findViewById(R.id.list_department);

        PREF_STATE = getApplicationContext().getSharedPreferences("MyPref",
                MODE_PRIVATE);
        int pref_State = PREF_STATE.getInt("state",0);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);

        if(pref_State!=3) {
            mdDatabaseHelper.createDepartment(new Department("HRM & ADMIN"));
            mdDatabaseHelper.createDepartment(new Department("FINANCE & ACCOUNTS"));
            mdDatabaseHelper.createDepartment(new Department("SELLS"));
            mdDatabaseHelper.createDepartment(new Department("MARKETING"));
            mdDatabaseHelper.createDepartment(new Department("OPERATIONS"));
            mdDatabaseHelper.createStatus(new Status("TRAINEE"));
            mdDatabaseHelper.createStatus(new Status("INTERNSHIP"));
            mdDatabaseHelper.createStatus(new Status("OFFICIAL STAFF"));
            mdDatabaseHelper.createPosition(new Position("CEO"));
            mdDatabaseHelper.createPosition(new Position("MANAGER"));
            mdDatabaseHelper.createPosition(new Position("DEPUTY MANAGER"));
            mdDatabaseHelper.createPosition(new Position("SR. EXECUTIVE"));
            mdDatabaseHelper.createPosition(new Position("EXECUTIVE"));
            mdDatabaseHelper.createActivity(new Activity("ACTIVE"));
            mdDatabaseHelper.createActivity(new Activity("INACTIVE"));

        }
        editor = PREF_STATE.edit();
        editor.putInt("state", 3);
        editor.commit();
        ArrayList<Department> arrayList=mdDatabaseHelper.getAllDepartments();
        adapter=new MyAdapter(this,arrayList);
        list_department.setAdapter(adapter);

    }

    public void add_data(){

        ArrayList<SearchStaff> arrayList=mdDatabaseHelper.data();
        for(SearchStaff staff:arrayList){

            mdDatabaseHelper.insert_searchitem(staff);
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
                fragment = new RegistrationFragment();
                title = getString(R.string.title_registration);
                break;
            case VIEW_FRAGMENT:
                fragment = new ViewFragment();
                title = getString(R.string.title_view);
                break;
            case UPDATE_FRAGMENT:
                fragment = new UpdateFragment();
                title = getString(R.string.title_update);
                break;
            default:
                break;
        }
     /*   if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();fragmentTransaction.replace(R.id.frame_container_body, fragment);
            fragmentTransaction.commit();
             //set the toolbar title
            getSupportActionBar().setTitle(title);
        }*/
    }
    private void displayResults(String query) throws SQLException {
       // mDbHelper.db_value();
        add_data();
        Cursor cursor = mdDatabaseHelper.searchByInputText((query != null ? query : "@@@@"));

        if (cursor != null) {

            String[] from = new String[]{mdDatabaseHelper.FTS_COLUMN_NAME, mdDatabaseHelper.FTS_COLUMN_PHONE,
                    mdDatabaseHelper.FTS_BIRTH_PLACE_FIELD,mdDatabaseHelper.FTS_BIRTH_DATE_FIELD};

              /*  for (int i=0;i<from.length;i++){

                    Toast.makeText(getApplicationContext(),"From  "+from[i],Toast.LENGTH_LONG).show();

                }*/


            int[] to = new int[]{R.id.search_result_text_view, R.id.result_number, R.id.birth_place,R.id.birth_date};

            SimpleCursorAdapter cursorAdapter1 = new SimpleCursorAdapter(this, R.layout.result_search_item, cursor, from, to);
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.result_search_item, cursor, from, to, 1);
            if (cursorAdapter.getCount() > 0)
                list_department.setAdapter(cursorAdapter);

            final String staff_id=mdDatabaseHelper.FTS_COLUMN_ID;

            //listview Click listener
            list_department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //   Cursor cursor = (Cursor) myList.getItemAtPosition(position);

                    //  String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(MainActivity.this, "selectedName :" + staff_id, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), StaffDetail.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                     /*   myList.setAdapter(defaultAdapter);

                        for (int pos = 0; pos < nameList.size(); pos++) {
                            if (nameList.get(pos).equals(selectedName)) {
                                position = pos;
                                break;
                            }
                        }

                        android.os.Handler handler = new android.os.Handler();
                        final int finalPosition = position;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                myList.setSelection(finalPosition);
                            }
                        });

                        searchView.setQuery("", true);*/
                }
            });

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

        if(!newText.isEmpty()){

            try {
                displayResults(newText+"*");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else
        list_department.setAdapter(adapter);

        return false;
    }
}
