package com.framgia.hrm.activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.framgia.hrm.R;
import com.framgia.hrm.database.DatabaseHelper;
import com.framgia.hrm.model.Staff;
import java.util.Calendar;
public class Addstaff extends AppCompatActivity {
    private DatabaseHelper mDb;
    private EditText mEditName;
    private EditText mEditPhone;
    private EditText mBirthPlace;
    private EditText mCalenderId;
    private Spinner mSactivity;
    private Spinner mSdept;
    private Spinner mSstatus;
    private Spinner mSposition;
    private ImageButton set_date;
    private String mName, mPhone, mBplace, mBirthday;
    private int mActivity, mDept, mStatus, mPosition;
    private Button mAddbutton;
    private String BUNDLE_ID = "id";
    /**id for departments*/
    private static final int HRM_AND_ADMIN = 0;
    private static final int FINANCE_AND_ACCOUNTS = 1;
    private static final int SELLS = 2;
    private static final int MARKETING = 3;
    private static final int OPERATIONS = 4;
    /**id for status*/
    private static final int TRAINEE = 0;
    private static final int INTERNSHIP = 1;
    private static final int OFFICIAL_STAFF = 2;
    /**IDs for position*/
    private static final int CEO = 0;
    private static final int MANAGER = 1;
    private static final int DEPUTY_MANAGER = 2;
    private static final int SR_EXECUTIVE = 3;
    private static final int EXECUTIVE = 4;
    /**IDs for activity*/
    private static final int ACTIVE = 0;
    private static final int INACTIVE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstaff);
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
        mAddbutton = (Button) findViewById(R.id.btn_add_usr);
        set_date = (ImageButton) findViewById(R.id.imageButton1);
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditPhone = (EditText) findViewById(R.id.edit_phone);
        mBirthPlace = (EditText) findViewById(R.id.edit_birth_place);
        mCalenderId = (EditText) findViewById(R.id.edit_calendar_id);
        mSactivity = (Spinner) findViewById(R.id.spinner_text_activity);
        mSdept = (Spinner) findViewById(R.id.spinner_dept);
        mSstatus = (Spinner) findViewById(R.id.spinner_status);
        mSposition = (Spinner) findViewById(R.id.spinner_position);
        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatefield();
            }
        });
        mSactivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case ACTIVE:
                        mActivity = 1;
                        break;
                    case INACTIVE:
                        mActivity = 2;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSdept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case HRM_AND_ADMIN:
                        mDept = 1;
                        break;
                    case FINANCE_AND_ACCOUNTS:
                        mDept = 2;
                        break;
                    case SELLS:
                        mDept = 3;
                        break;
                    case MARKETING:
                        mDept = 4;
                        break;
                    case OPERATIONS:
                        mDept = 5;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case TRAINEE:
                        mStatus = 1;
                        break;
                    case INTERNSHIP:
                        mStatus = 2;
                        break;
                    case OFFICIAL_STAFF:
                        mStatus = 3;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSposition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case CEO:
                        mPosition = 1;
                        break;
                    case MANAGER:
                        mPosition = 2;
                        break;
                    case DEPUTY_MANAGER:
                        mPosition = 3;
                        break;
                    case SR_EXECUTIVE:
                        mPosition = 4;
                        break;
                    case EXECUTIVE:
                        mPosition = 5;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mAddbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = mEditName.getText().toString();
                mPhone = mEditPhone.getText().toString();
                mBplace = mBirthPlace.getText().toString();
                mBirthday = mCalenderId.getText().toString();
                Staff staff = new Staff(mName, mBirthday, mBplace, mPhone, mDept, mStatus, mActivity,
                        mPosition);
                long staff_id = mDb.createStaff(staff);
                mDb.closeDB();
                Toast.makeText(getApplicationContext(), "New staff added", Toast.LENGTH_SHORT).show();
                Bundle dataBundle = new Bundle();
                dataBundle.putLong(BUNDLE_ID, staff_id);
                Intent intent = new Intent(getApplicationContext(), StaffDetail.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    public void setDatefield() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), " " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_LONG).show();
                        String date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        mCalenderId.setText(date);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
}
