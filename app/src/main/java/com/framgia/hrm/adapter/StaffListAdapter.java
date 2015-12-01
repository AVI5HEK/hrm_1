package com.framgia.hrm.adapter;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.framgia.hrm.R;
import com.framgia.hrm.model.Staff;
import java.util.ArrayList;
/**
 * Created by avishek on 12/1/15.
 */
public class StaffListAdapter extends BaseAdapter{
    private ArrayList<Staff> myListItems;
    private LayoutInflater myLayoutInflater;
    public StaffListAdapter(Context context, ArrayList<Staff> arrayList){
        myListItems = arrayList;
        myLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myListItems.size();
    }
    @Override
    public Staff getItem(int i) {
        return myListItems.get(i);
    }
    @Override
    public long getItemId(int i) {
        return getItem(i).getStaff_id();
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = myLayoutInflater.inflate(R.layout.staff_list, null);
            holder.itemName = (TextView) view.findViewById(R.id.text_staff_list);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        Staff staff = myListItems.get(position);
        String stringItem = staff.getName();
        Log.e("values", stringItem);
        holder.itemName.setText(stringItem);
        if(staff.getActivity_id() == 2) view.setBackgroundColor(Color.LTGRAY);
        else view.setBackgroundColor(Color.WHITE);
        return view;
    }
    private static class ViewHolder {
        TextView itemName;
    }
}
