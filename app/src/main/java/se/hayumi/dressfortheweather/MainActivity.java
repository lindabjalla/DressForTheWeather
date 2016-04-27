package se.hayumi.dressfortheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import se.hayumi.dressfortheweather.adapter.WeatherAdapter;
import se.hayumi.dressfortheweather.database.WeatherDataSource;
import se.hayumi.dressfortheweather.model.WeatherData;
import se.hayumi.dressfortheweather.service.WeatherService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.names_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        List<WeatherData> weatherList = new ArrayList<>();
        WeatherDataSource dataSource = new WeatherDataSource(this);

        final WeatherData latestWeather = dataSource.getLatestEntity();
        Date date = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(date);

        if (latestWeather.getDateTime() == null || !currentTime.equals(latestWeather.getFetchTime())) {
        WeatherService webService = new WeatherService(this);
        weatherList.addAll(webService.getWeather());

        }
        List<WeatherData> weathersByFetchTimeDesc = dataSource.getWeathersByFetchTimeDesc(currentTime);
        weatherList.addAll(weathersByFetchTimeDesc);

        RecyclerView.Adapter adapter = new WeatherAdapter(this, weatherList);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
