package se.hayumi.dressfortheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import se.hayumi.dressfortheweather.adapter.WeatherAdapter;
import se.hayumi.dressfortheweather.model.WeatherData;
import se.hayumi.dressfortheweather.service.WeatherWebService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private WeatherWebService webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.names_recycler_view);
        layoutManager = new LinearLayoutManager(this);

//        List<WeatherData> data = new ArrayList<>();
//        data.add(new WeatherData("2:00 AM CEST on April 25, 2016", "Partly Cloudy", -1, -4));
//        data.add(new WeatherData("3:00 AM CEST on April 25, 2016", "Partly Cloudy", -1, -4));
//        data.add(new WeatherData("4:00 AM CEST on April 25, 2016", "Mostly Cloudy", -1, -4));
//        data.add(new WeatherData("5:00 AM CEST on April 25, 2016", "Rainy", 20, 20));
//        data.add(new WeatherData("6:00 AM CEST on April 25, 2016", "Rainy", 25, 30));
//        adapter = new WeatherAdapter(this, data);
        webService = new WeatherWebService(this);
        List<WeatherData> weatherList = webService.getWeather();
        adapter = new WeatherAdapter(this, weatherList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
