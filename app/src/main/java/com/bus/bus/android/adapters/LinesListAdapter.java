package com.bus.bus.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bus.bus.android.BaseActivity;
import com.bus.bus.android.R;

public class LinesListAdapter extends BaseAdapter {

    Context mContext;
    String id[];
    String shortName[];
    String longName[];
    String color[];
    String textColor[];
    String mode[];
    String type[];

    public LinesListAdapter(Context mContext, String[] id, String[] shortName, String[] longName, String[] color, String[] textColor, String[] mode, String[] type) {
        this.mContext = mContext;
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.color = color;
        this.textColor = textColor;
        this.mode = mode;
        this.type = type;
    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int i) {
        return id[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View rowView = LayoutInflater.from(mContext).inflate(R.layout.lines_list_item, viewGroup, false);

        TextView route = rowView.findViewById(R.id.route);
        route.setText("Line: "+shortName[i]+"\nName: " + longName[i] + "\nMode: " + mode[i]);
        route.setTextColor(Color.parseColor("#" + textColor[i]));
        route.setBackgroundColor(Color.parseColor("#"+color[i]));


        return rowView;
    }
}
