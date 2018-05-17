package com.bus.bus.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bus.bus.android.R;

public class FavoritsAdapter extends BaseAdapter {

    Context mContext;
    String url[];
    String cluster[];

    public FavoritsAdapter(Context mContext, String[] url, String[] cluster) {
        this.mContext = mContext;
        this.url = url;
        this.cluster = cluster;
    }

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public Object getItem(int i) {
        return cluster[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View rowView = LayoutInflater.from(mContext).inflate(R.layout.favorit_list_item, viewGroup, false);

        TextView favoritItem = rowView.findViewById(R.id.favoritItem);
        favoritItem.setText(cluster[i]);


        return rowView;
    }
}
