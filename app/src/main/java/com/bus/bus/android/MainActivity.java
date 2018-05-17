package com.bus.bus.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bus.bus.android.adapters.LinesListAdapter;
import com.bus.bus.android.adapters.StationListAdapter;
import com.bus.bus.android.models.StationModel;
import com.bus.bus.android.models.LinesListModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    String linesListUrl = "https://data.metromobilite.fr/api/routers/default/index/routes";
    LinesListModel[] linesList = null;

    String spinnerItems[] = {"Chrono", "Tram", "Flexo", "Proximo"};

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.linesListListView)
    ListView linesListListView;

    @BindView(R.id.listViewLinear)
    LinearLayout listViewLinear;

    @BindView(R.id.favoritImageButton)
    ImageButton favoritImageButton;

    BusDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(mActivity);
        setSpinnerItems();

        GetLineslistByAsync(linesListUrl);
        if (linesList != null)
            setLineList(0);

        spinner.setOnItemSelectedListener(this);

        favoritImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favoritsIntent = new Intent(MainActivity.this,FavoritsActivity.class);
                startActivity(favoritsIntent);
            }
        });


        linesListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String stationId = (String) adapterView.getItemAtPosition(i);

                Intent stationIntent = new Intent(MainActivity.this, StationActivity.class);
                stationIntent.putExtra("stationId", stationId);
                startActivity(stationIntent);

            }
        });

    }

    public void setSpinnerItems() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void GetLinesList(String serverResponse) {
        Gson gson = new Gson();
        linesList = gson.fromJson(serverResponse, LinesListModel[].class);
    }

    public void GetLineslistByAsync(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Publics.showToast(mContext, throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                GetLinesList(responseString);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinner)
            setLineList(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setLineList(int i) {
        if (linesList != null) {
            List<String> id = new ArrayList<String>();
            List<String> shortName = new ArrayList<String>();
            List<String> longName = new ArrayList<String>();
            List<String> color = new ArrayList<String>();
            List<String> textColor = new ArrayList<String>();
            List<String> mode = new ArrayList<String>();
            List<String> type = new ArrayList<String>();

            for (LinesListModel item : linesList) {
                for (int j = 0; j < spinnerItems.length; j++) {
                    if (item.getType().toLowerCase().equals(spinnerItems[j].toLowerCase())) {
                        if (j == i) {
                            id.add(item.getId());
                            shortName.add(item.getShortName());
                            longName.add(item.getLongName());
                            color.add(item.getColor());
                            textColor.add(item.getTextColor());
                            mode.add(item.getMode());
                            type.add(item.getType());
                        }
                    }
                }
            }

            String[] idS = new String[id.size()];
            idS = id.toArray(idS);

            String[] shortNameS = new String[shortName.size()];
            shortNameS = shortName.toArray(shortNameS);

            String[] longNameS = new String[longName.size()];
            longNameS = longName.toArray(longNameS);

            String[] colorS = new String[color.size()];
            colorS = color.toArray(colorS);

            String[] textColorS = new String[textColor.size()];
            textColorS = textColor.toArray(textColorS);

            String[] modeS = new String[mode.size()];
            modeS = mode.toArray(modeS);

            String[] typeS = new String[type.size()];
            typeS = type.toArray(typeS);

            LinesListAdapter adapter = new LinesListAdapter(mContext, idS, shortNameS, longNameS, colorS, textColorS, modeS, typeS);
            linesListListView.setAdapter(adapter);
        }
    }
}
