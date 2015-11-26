package com.framgia.hrm.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.hrm.R;

/**
 * Created by sharma on 11/23/15.
 */
public class ViewFragment extends Fragment {
    public ViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        // Inflate the layout for this fragment
        return rootView;
    }
}
