package se.hayumi.dressfortheweather;

import android.os.Bundle;
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

    private Retrofit retrofit;
    private Realm realm;
    private RealmAsyncTask transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(new Date());
        realm = Realm.getDefaultInstance();
        List<WeatherData> latestWeatherList = realm.where(WeatherData.class).equalTo("fetchTime", currentTime).findAll();
        System.out.println("latestWeatherList" + latestWeatherList);
        final List<WeatherData> weatherDataList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.names_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

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

                        final WeatherData weatherData = new WeatherData(weather.getDateTime().getDateTime(), weather.getCondition(),
                                weather.getTemperature().getCelsius(), weather.getFeelsLikeTemperature().getCelsius());

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

        RecyclerView.Adapter adapter = new WeatherAdapter(this, weatherDataList);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onStop() {
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();

            super.onStop();
        }
    }
}
