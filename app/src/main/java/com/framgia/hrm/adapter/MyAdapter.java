package com.framgia.hrm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.framgia.hrm.R;
import com.framgia.hrm.model.Department;

import java.util.ArrayList;

/**
 * Created by ahsan on 11/23/15.
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Department> myListItems;
    private LayoutInflater myLayoutInflater;

    public MyAdapter(Context context, ArrayList<Department> arrayList){

        myListItems = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myListItems.size();
    }

    @Override

    public Department getItem(int i) {
        return myListItems.get(i);
    }

    @Override
    public long getItemId(int i) {

        return getItem(i).getDept_id();
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {


        ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();

            view = myLayoutInflater.inflate(R.layout.department_list, null);
            holder.itemName = (TextView) view.findViewById(R.id.list_item_text_view);


            view.setTag(holder);
        } else {

            holder = (ViewHolder)view.getTag();
        }

        Department de=myListItems.get(position);
        String stringItem = de.getDept_name();

        Log.e("values",stringItem);
       /* if (stringItem != null) {
            if (holder.itemName != null) {
                //set the item name on the TextView
                holder.itemName.setText(stringItem);
            }
        }*/

        holder.itemName.setText(stringItem);
        return view;

    }


    private static class ViewHolder {

         TextView itemName;
    }
}
