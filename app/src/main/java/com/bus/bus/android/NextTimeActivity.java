package com.bus.bus.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bus.bus.android.models.NextTimeModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.orhanobut.hawk.Hawk;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class NextTimeActivity extends BaseActivity {

    NextTimeModel nextTimeModel[] = null;

    @BindView(R.id.nextTimeTextView)
    TextView nextTimeTextView;

    @BindView(R.id.goDirection)
    TextView goDirection;

    @BindView(R.id.comeBackDirection)
    TextView comeBackDirection;

    @BindView(R.id.lineTextView)
    TextView lineTextView;

    @BindView(R.id.nextTime)
    TextView nextTime;
    @BindView(R.id.secondTime)
    TextView secondTime;
    @BindView(R.id.comeBackNextTime)
    TextView comeBackNextTime;
    @BindView(R.id.comeBackSecondTime)
    TextView comeBackSecondTime;

    @BindView(R.id.favoritStation)
    ImageView favoritStation;

    BusDBHandler dbHandler;

    String nextTimeUrl = null;

    boolean ch1 = false;
    boolean ch2 = false;
    boolean ch3 = false;
    boolean ch4 = false;

    String TAG = "err";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_time);
        ButterKnife.bind(mActivity);
        dbHandler = new BusDBHandler(mContext, "bus", null, 2);

        final String stationId = getIntent().getStringExtra("stationId");
        lineTextView.setText("Line: " + stationId);

        final String clusterCode = getIntent().getStringExtra("clusterCode");
        final String favoritUrl = getIntent().getStringExtra("favotitUrl");
        if (favoritUrl != null && !favoritUrl.isEmpty()) {
            nextTimeUrl = favoritUrl;
            GetNextTimeByAsync(nextTimeUrl);
        } else {
            nextTimeUrl = "https://data.metromobilite.fr/api/routers/default/index/clusters/" + clusterCode + "/stoptimes";
            GetNextTimeByAsync(nextTimeUrl);
        }


        String clusters[] = dbHandler.getAllCluster();
        if (Arrays.asList(clusters).contains(clusterCode))
            favoritStation.setImageResource(R.drawable.favorit_enabled);
        else
            favoritStation.setImageResource(R.drawable.favorit_disabled);

        favoritStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmap = ((BitmapDrawable) favoritStation.getDrawable()).getBitmap();
                Drawable myDrawable = getResources().getDrawable(R.drawable.favorit_disabled);
                Bitmap isFavorit = ((BitmapDrawable) myDrawable).getBitmap();
                if (bmap.sameAs(isFavorit)) {
                    dbHandler.insertRecord(nextTimeUrl, clusterCode, stationId);
                    favoritStation.setImageResource(R.drawable.favorit_enabled);
                } else {
                    dbHandler.deleteRecord(nextTimeUrl, clusterCode);
                    favoritStation.setImageResource(R.drawable.favorit_disabled);
                }
            }
        });

        new CountDownTimer(2000000000, 30000) {
            public void onTick(long millisUntilFinished) {
                GetNextTimeByAsync(nextTimeUrl);
            }

            public void onFinish() {

            }
        }.start();


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GetNextTime(String serverResponse) {
        Gson gson = new Gson();
        nextTimeModel = gson.fromJson(serverResponse, NextTimeModel[].class);
        GetTwoNextTimeArrival(nextTimeModel);

    }

    public void GetNextTimeByAsync(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Publics.showToast(mContext, throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                GetNextTime(responseString);
            }
        });

    }

    public void GetTwoNextTimeArrival(NextTimeModel[] nextTimeModel) {
        if (nextTimeModel != null) {
            ch1 = false;
            ch2 = false;
            ch3 = false;
            ch4 = false;
            nextTime.setText("");
            secondTime.setText("");
            comeBackNextTime.setText("");
            comeBackSecondTime.setText("");

            String nextTimeText = nextTimeModel[0].getTimes().get(0).getStopName().toString();
            nextTimeTextView.setText("Stop: " + nextTimeText);

            for (int i = 0; i < nextTimeModel.length; i++) {
                if (!nextTime.getText().toString().isEmpty() && !secondTime.getText().toString().isEmpty() && !comeBackNextTime.getText().toString().isEmpty() && !comeBackSecondTime.getText().toString().isEmpty())
                    break;
                long unixMilliSecondNow = System.currentTimeMillis();
                Calendar current = Calendar.getInstance();
                current.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), current.get(Calendar.DATE), 0, 0, 0);
                long zeroToday = current.getTimeInMillis();

                if (nextTimeModel[i].getPattern().getDir().trim() == "1") {
                    goDirection.setText("Direction: " + nextTimeModel[i].getPattern().getShortDesc().toString());
                    for (int j = 0; j < nextTimeModel[i].getTimes().size(); j++) {
                        long secondArrival = Long.parseLong(nextTimeModel[i].getTimes().get(j).getRealtimeArrival());
                        long milliSecondArrival = secondArrival * 1000;
                        long unixMilliSecondArrival = zeroToday + milliSecondArrival;

                        if (unixMilliSecondNow < unixMilliSecondArrival) {
                            Date dateArrival = new Date(unixMilliSecondArrival);
                            Date dateNow = new Date(unixMilliSecondNow);
                            long seconds = (dateArrival.getTime() - dateNow.getTime()) / 1000;
                            String s = String.format("%d min, %d sec",
                                    TimeUnit.SECONDS.toMinutes(seconds),
                                    TimeUnit.SECONDS.toSeconds(seconds) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)));

                            if (ch1 == false) {
                                ch1 = true;
                                nextTime.setText("Next Time Is:\n" + s);
                            } else if (ch1 == true && ch2 == false) {
                                ch2 = true;
                                secondTime.setText("Second Time Is:\n" + s);
                            }
                        }
                    }
                } else if (nextTimeModel[i].getPattern().getDir().trim() == "2") {
                    comeBackDirection.setText("Direction: " + nextTimeModel[i].getPattern().getShortDesc().toString());
                    for (int j = 0; j < nextTimeModel[i].getTimes().size(); j++) {
                        long secondArrival = Long.parseLong(nextTimeModel[i].getTimes().get(j).getRealtimeArrival());
                        long milliSecondArrival = secondArrival * 1000;
                        long unixMilliSecondArrival = zeroToday + milliSecondArrival;

                        if (unixMilliSecondNow < unixMilliSecondArrival) {
                            Date dateArrival = new Date(unixMilliSecondArrival);
                            Date dateNow = new Date(unixMilliSecondNow);
                            long seconds = (dateArrival.getTime() - dateNow.getTime()) / 1000;
                            String s = String.format("%d min, %d sec",
                                    TimeUnit.SECONDS.toMinutes(seconds),
                                    TimeUnit.SECONDS.toSeconds(seconds) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds)));

                            if (ch3 == false) {
                                ch3 = true;
                                comeBackNextTime.setText("Come Back Next Time Is:\n" + s);
                            } else if (ch3 == true && ch4 == false) {
                                ch4 = true;
                                comeBackSecondTime.setText("Come Back Second Time Is:\n" + s);
                            }
                        }
                    }
                }
            }
        }
    }

}
