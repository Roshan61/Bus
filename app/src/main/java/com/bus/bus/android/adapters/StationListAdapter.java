package com.bus.bus.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bus.bus.android.R;

public class StationListAdapter extends BaseAdapter {
    Context mContext;
    String code[];
    String city[];
    String name[];

    public StationListAdapter(Context mContext, String[] code, String[] city, String[] name) {
        this.mContext = mContext;
        this.code = code;
        this.city = city;
        this.name = name;
    }

    @Override
    public int getCount() {
        return code.length;
    }

    @Override
    public Object getItem(int i) {
        return code[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View rowView = LayoutInflater.from(mContext).inflate(R.layout.stations_list_item, viewGroup, false);

        TextView station = rowView.findViewById(R.id.station);
        station.setText("City: " + city[i] + "\nName: " + name[i]);


        return rowView;
    }
}
