package com.szm.administrator.retrofittry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.szm.administrator.retrofittry.models.User;
import com.szm.administrator.retrofittry.services.NormalClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "szm--";

    RelativeLayout dataLayout;
    protected RelativeLayout weatherLayout;
    protected EditText searchEditText;
    protected TextView countryTextView;
    protected TextView sunriseTextView;
    protected TextView sunsetTextView;
    protected ImageView iconImageView;
    protected TextView weatherTextView;
    private TextView editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        dataLayout= (RelativeLayout) findViewById(R.id.activity_main_data);

        weatherLayout= (RelativeLayout) findViewById(R.id.activity_main_weather);

        searchEditText= (EditText) findViewById(R.id.activity_main_search);

        countryTextView= (TextView) findViewById(R.id.activity_main_sys_country_value);

        sunriseTextView= (TextView) findViewById(R.id.activity_main_sys_sunrise_value);

        sunsetTextView= (TextView) findViewById(R.id.activity_main_sys_sunset_value);

        iconImageView= (ImageView) findViewById(R.id.activity_main_weather_icon);

        weatherTextView= (TextView) findViewById(R.id.activity_main_weather_text);
        editText= (TextView) findViewById(R.id.activity_main_search_button);
        editText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new NormalClient().getUserApi().getUser(searchEditText.getText().toString(), new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                Log.i("TAG","-----------"+user.toString());
                final Date sunriseDate = new Date(user.getSys().getSunriseTime() * 1000);
                final Date sunsetDate = new Date(user.getSys().getSunsetTime() * 1000);
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh':'mm':'ss a");

                getActionBar().setTitle(user.getStrCityName());
                countryTextView.setText(user.getSys().getStrCountry());

                if (!user.getWeather().isEmpty())
                {
                    Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + user.getWeather().get(0).getStrIconName() + ".png").into(iconImageView);
                    weatherTextView.setText(user.getWeather().get(0).getStrDesc());
                }

                sunsetTextView.setText(simpleDateFormat.format(sunsetDate));
                sunriseTextView.setText(simpleDateFormat.format(sunriseDate));

                searchEditText.setText("");
                Log.e(TAG, "City name : " + user.getStrCityName());
                dataLayout.setVisibility(View.VISIBLE);
                weatherLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Error : " + t.getMessage());
                searchEditText.setText("");
                dataLayout.setVisibility(View.GONE);
                weatherLayout.setVisibility(View.GONE);
            }
        });
    }
}
