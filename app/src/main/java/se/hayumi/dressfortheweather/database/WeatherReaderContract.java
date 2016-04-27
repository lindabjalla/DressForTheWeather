package se.hayumi.dressfortheweather.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import se.hayumi.dressfortheweather.storage.FeedReaderDbHelper;

import android.provider.BaseColumns;

public final class WeatherReaderContract {

    public WeatherReaderContract() {}

    public static abstract class WeatherEntry implements BaseColumns {

        public static final String TABLE_NAME = "Weather";
        public static final String COLUMN_NAME_ENTRY_ID = "entryId";
        public static final String COLUMN_NAME_DATE_TIME = "dateTime";
        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_FEELS_LIKE_TEMPERATURE = "feelsLikeTemperature";
        public static final String COLUMN_NAME_CLOTHES_TO_WEAR = "clothesToWear";
        public static final String COLUMN_NAME_FETCH_TIME = "fetchTime";
    }

    private static final String STRING_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
//    private static final String SPACE = " ";

    private static final String SQL_CREATE_TABLE_WEATHER =
            "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                    WeatherEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_ENTRY_ID + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_DATE_TIME + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_CONDITION + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_TEMPERATURE + INT_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_FEELS_LIKE_TEMPERATURE + INT_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_CLOTHES_TO_WEAR + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.COLUMN_NAME_FETCH_TIME +
                    " )";


    private static final String SQL_DELETE_TABLE_WEATHER =
            "DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME;

    public static String getSqlCreateEntries() {
        return SQL_CREATE_TABLE_WEATHER;
    }

    public static String getSqlDeleteTableWeather() {
        return SQL_DELETE_TABLE_WEATHER;
    }
}