package com.bus.bus.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SignalStrength;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bus.bus.android.adapters.FavoritsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritsActivity extends BaseActivity {

    BusDBHandler dbHandler;

    @BindView(R.id.favoritsListView)
    ListView favoritsListView;

    String TAG = "err";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorits);
        ButterKnife.bind(mActivity);
        dbHandler = new BusDBHandler(mContext, "bus", null, 2);

        final List<String> url = new ArrayList<>();
        List<String> cluster = new ArrayList<>();

        if (dbHandler.checkForTables()){

        String urlS[] = dbHandler.getAllUrl();
        String clusterS[] = dbHandler.getAllCluster();
            FavoritsAdapter adapter = new FavoritsAdapter(mContext, urlS, clusterS);
            favoritsListView.setAdapter(adapter);
        }

        favoritsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clusterClicked = (String) adapterView.getItemAtPosition(i);
                String urlClicked = dbHandler.getUrlByCluster(clusterClicked);
                String stationIdClicked = dbHandler.getStationIdByCluster(clusterClicked);
                Intent nextTimeActivity = new Intent(FavoritsActivity.this , NextTimeActivity.class);
                nextTimeActivity.putExtra("clusterCode",clusterClicked);
                nextTimeActivity.putExtra("favotitUrl",urlClicked);
                nextTimeActivity.putExtra("stationId",stationIdClicked);
                startActivity(nextTimeActivity);
            }
        });

    }
}
