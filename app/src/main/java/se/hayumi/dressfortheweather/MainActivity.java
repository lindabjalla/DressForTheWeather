package se.hayumi.dressfortheweather;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.hayumi.dressfortheweather.adapter.WeatherAdapter;
import se.hayumi.dressfortheweather.model.HourlyForecast;
import se.hayumi.dressfortheweather.model.Weather;
import se.hayumi.dressfortheweather.model.WeatherData;
import se.hayumi.dressfortheweather.service.WeatherService;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.wunderground.com/api/acac042dd991e6be/";
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String WEATHER_KEY = "se.hayumi.dressfortheweather.WEATHER_KEY";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Retrofit retrofit;
    private Realm realm;
    private RealmAsyncTask transaction;
    private final ArrayList<WeatherData> weatherDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        realm = Realm.getDefaultInstance();

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(new Date());
        List<WeatherData> latestWeatherList = realm.where(WeatherData.class)
                        .equalTo("fetchTime", currentTime).findAllSorted("dateTimeInMilliSeconds", Sort.ASCENDING);

        System.out.println("latestWeatherList" + latestWeatherList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.names_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        if (!latestWeatherList.isEmpty()) {
            weatherDataList.addAll(latestWeatherList);
        } else {

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
                    final List<Weather> weatherList = response.body().getWeatherList();

                    for (Weather weather : weatherList) {

                        final WeatherData weatherData = new WeatherData(weather.getTime().getDateTime(), weather.getCondition(),
                                weather.getTemperature().getCelsius(), weather.getFeelsLikeTemperature().getCelsius(), weather.getTime().getDateTimeInMilliSeconds());

                        transaction = realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealm(weatherData);
                            }
                        });
                        weatherDataList.add(weatherData);
                    }
                }

                @Override
                public void onFailure(Call<HourlyForecast> call, Throwable t) {
                    Log.d(TAG, "Could not fetch HourlyForecast: " + t.getMessage());
                }
            });
        }

        adapter = new WeatherAdapter(this, weatherDataList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList(WEATHER_KEY, weatherDataList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<WeatherData> savedWeatherDataList = savedInstanceState.getParcelableArrayList(WEATHER_KEY);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }
}
