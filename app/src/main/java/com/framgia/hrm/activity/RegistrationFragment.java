package com.framgia.hrm.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.framgia.hrm.R;

import java.util.Calendar;

/**
 * Created by sharma on 11/23/15.
 */
public class RegistrationFragment extends Fragment {
    EditText editText;
    ImageButton imageButton;

    public RegistrationFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        imageButton = (ImageButton) rootView.findViewById(R.id.imageButton1);
        editText = (EditText) rootView.findViewById(R.id.edit_calendar_id);

        //**************IMPLEMENTING SPINNER DROP DOWN FOR ACTIVITY
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_text_activity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + "is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //**************IMPLEMENTING SPINNER DROP DOWN FOR DEPARTMENT
        Spinner spinner_dept = (Spinner) rootView.findViewById(R.id.spinner_dept);
        ArrayAdapter<CharSequence> adapter_dept = ArrayAdapter.createFromResource(getContext(), R.array.department_array, android.R.layout.simple_spinner_item);
        adapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dept.setAdapter(adapter_dept);
        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + "is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //****************IMPLEMENTING SPINNER DROP DOWN FOR STATUS
        Spinner spinner_status = (Spinner) rootView.findViewById(R.id.spinner_status);
        ArrayAdapter<CharSequence> adapter_status = ArrayAdapter.createFromResource(getContext(), R.array.status_array, android.R.layout.simple_spinner_item);
        adapter_status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(adapter_status);
        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + "is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //****************IMPLEMENTING SPINNER DROP DOWN FOR POSITION
        Spinner spinner_position = (Spinner) rootView.findViewById(R.id.spinner_position);
        ArrayAdapter<CharSequence> adapter_position = ArrayAdapter.createFromResource(getContext(), R.array.position_array, android.R.layout.simple_spinner_item);
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_position.setAdapter(adapter_position);
        spinner_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + "is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();

            }
        });
    }

    public void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            editText.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year));
        }
    };
}
