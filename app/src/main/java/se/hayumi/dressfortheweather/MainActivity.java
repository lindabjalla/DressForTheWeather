package se.hayumi.dressfortheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.hayumi.dressfortheweather.adapter.WeatherAdapter;
import se.hayumi.dressfortheweather.model.HourlyForecast;
import se.hayumi.dressfortheweather.model.WeatherData;
import se.hayumi.dressfortheweather.service.WeatherService;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.wunderground.com/api/acac042dd991e6be/";
    public static final String TAG = MainActivity.class.getSimpleName();

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.names_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<HourlyForecast> result = weatherService.getHourlyWeather("stockholm.json");

        result.enqueue(new Callback<HourlyForecast>() {

            @Override
            public void onResponse(Call<HourlyForecast> call, Response<HourlyForecast> response) {
                Log.d(TAG, "***************** " + response.body().toString());
            }

            @Override
            public void onFailure(Call<HourlyForecast> call, Throwable t) {
                Log.d(TAG, "Could not fetch user: " + t.getMessage());
            }
        });

        List<WeatherData> weatherList = new ArrayList<>();
//        WeatherDataSource dataSource = new WeatherDataSource(this);
//
//        final WeatherData latestWeather = dataSource.getLatestEntity();
//        Date date = new Date();
//        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(date);
//
//        if (latestWeather.getDateTime() == null || !currentTime.equals(latestWeather.getFetchTime())) {
//        WeatherService webService = new WeatherService(this);
//        weatherList.addAll(webService.getWeather());
//
//        }
//        List<WeatherData> weathersByFetchTimeDesc = dataSource.getWeathersByFetchTimeDesc(currentTime);
//        weatherList.addAll(weathersByFetchTimeDesc);

        RecyclerView.Adapter adapter = new WeatherAdapter(this, weatherList);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}