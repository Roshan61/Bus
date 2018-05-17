package com.bus.bus.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bus.bus.android.adapters.StationListAdapter;
import com.bus.bus.android.models.StationModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class StationActivity extends BaseActivity {

    StationModel[] stationList = null;

    @BindView(R.id.stationListView)
    ListView stationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        ButterKnife.bind(mActivity);

        final String stationId = getIntent().getStringExtra("stationId");
        String stationUrl = "https://data.metromobilite.fr/api/routers/default/index/routes/" + stationId
                + "/clusters";
        GetStationOfRouteByAsync(stationUrl);

        stationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String clusterCode = (String) adapterView.getItemAtPosition(i);

                Intent nextTimeIntent = new Intent(StationActivity.this, NextTimeActivity.class);
                nextTimeIntent.putExtra("clusterCode",clusterCode);
                nextTimeIntent.putExtra("stationId",stationId);
                startActivity(nextTimeIntent);

            }
        });


    }

    public void GetStationOfRoute(String serverResponse) {
        Gson gson = new Gson();
        stationList = gson.fromJson(serverResponse, StationModel[].class);
        if (stationList != null) {
            String code[] = new String[stationList.length];
            String city[] = new String[stationList.length];
            String name[] = new String[stationList.length];
            for (int j = 0; j < stationList.length; j++) {
                code[j] = stationList[j].getCode();
                city[j] = stationList[j].getCity();
                name[j] = stationList[j].getName();
            }
            StationListAdapter adapter = new StationListAdapter(mContext, code, city, name);
            stationListView.setAdapter(adapter);
        }
    }

    public void GetStationOfRouteByAsync(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Publics.showToast(mContext, throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                GetStationOfRoute(responseString);
            }
        });

    }
}
