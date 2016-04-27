package se.hayumi.dressfortheweather.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import se.hayumi.dressfortheweather.model.WeatherData;

import static se.hayumi.dressfortheweather.database.WeatherReaderContract.WeatherEntry;

public class WeatherDataSource {

    private Context context;
    private SQLiteDatabase readableDatabase;
    private SQLiteDatabase writableDatabase;

    public WeatherDataSource(Context context){
        WeatherReaderDbHelper dbHelper = new WeatherReaderDbHelper(context);
        readableDatabase = dbHelper.getReadableDatabase();
        writableDatabase = dbHelper.getWritableDatabase();
    }

    private String[] allColumns = {
            WeatherEntry.COLUMN_NAME_ENTRY_ID,
            WeatherEntry.COLUMN_NAME_DATE_TIME,
            WeatherEntry.COLUMN_NAME_CONDITION,
            WeatherEntry.COLUMN_NAME_TEMPERATURE,
            WeatherEntry.COLUMN_NAME_FEELS_LIKE_TEMPERATURE,
            WeatherEntry.COLUMN_NAME_CLOTHES_TO_WEAR,
            WeatherEntry.COLUMN_NAME_FETCH_TIME
    };

//    public WeatherData getLatestEntity() {
//
//        Cursor cursor = readableDatabase.query(WeatherEntry.TABLE_NAME,
//                allColumns, null, null, null, null, WeatherEntry.COLUMN_NAME_FETCH_TIME + " DESC", "1");
//
//        cursor.moveToFirst();
//        WeatherData weather = cursorToWeatherData(cursor);
//
//        cursor.close();
//        return weather;
//    }

//    public List<WeatherData> getWeathersByFetchTimeDesc(String fetchTime){
//
//        List<WeatherData> weatherDataList = new ArrayList<>();
//
//        Cursor cursor = readableDatabase.query(
//                WeatherEntry.TABLE_NAME,
//                allColumns, WeatherEntry.COLUMN_NAME_FETCH_TIME + " = ?",
//                new String[]{ fetchTime }, null, null, WeatherEntry.COLUMN_NAME_FETCH_TIME + " DESC");
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//
//            WeatherData weather = cursorToWeatherData(cursor);
//            weatherDataList.add(weather);
//            cursor.moveToNext();
//        }
//        cursor.close();
//
//        return weatherDataList;
//    }

    public long insertWeather(WeatherData weather){

        ContentValues values = new ContentValues();
        values.put(WeatherEntry.COLUMN_NAME_ENTRY_ID, weather.getEntryId());
        values.put(WeatherEntry.COLUMN_NAME_DATE_TIME, weather.getDateTime());
        values.put(WeatherEntry.COLUMN_NAME_CONDITION, weather.getCondition());
        values.put(WeatherEntry.COLUMN_NAME_TEMPERATURE, weather.getTemperature());
        values.put(WeatherEntry.COLUMN_NAME_FEELS_LIKE_TEMPERATURE, weather.getFeelsLikeTemperature());
        values.put(WeatherEntry.COLUMN_NAME_CLOTHES_TO_WEAR, weather.getClothesToWear());
        values.put(WeatherEntry.COLUMN_NAME_FETCH_TIME, weather.getFetchTime());

        long newRowId;
        newRowId = writableDatabase.insert(
                WeatherEntry.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

//    private WeatherData cursorToWeatherData(Cursor cursor) {
//
//        WeatherData weather = new WeatherData(null, null, -9999, -9999);
//        weather.setEntryId(cursor.getString(0));
//        weather.setDateTime(cursor.getString(1));
//        weather.setCondition(cursor.getString(2));
//        weather.setTemperature(cursor.getInt(3));
//        weather.setFeelsLikeTemperature(cursor.getInt(4));
//        weather.setClothesToWear(cursor.getString(5));
//        weather.setFetchTime(cursor.getString(6));
//        return weather;
//    }
}