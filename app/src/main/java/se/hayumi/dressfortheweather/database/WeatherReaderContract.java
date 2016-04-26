package se.hayumi.dressfortheweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class WeatherReaderContract {

    public WeatherReaderContract() {}

    public static abstract class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "WeatherData";
        public static final String WEATHER_ID = "weatherId";
        public static final String DATE_TIME = "dateTime";
        public static final String CONDITION = "condition";
        public static final int TEMPERATURE = 20;
        public static final int FEELS_LIKE_TEMPERATURE = 25;
        public static final boolean NEEDS_UMBRELLA = true;
        public static final String SUITABLE_CLOTHES = "";
    }

    //Metainformation för DataBasen
    private static final String STRING_TYPE = " text";
    private static final String INT_TYPE = "integer";
    private static final String BOOLEAN_TYPE = "boolean";
    private static final String COMMA_SEP = ",";
    //private static final String SPACE = " ";

    private static final String SQL_CREATE_TABLE_WEATHER =
            "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                    WeatherEntry.WEATHER_ID + "INTEGER PRIMARY KEY," +
                    WeatherEntry.DATE_TIME + " " + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.CONDITION + " " + STRING_TYPE + COMMA_SEP +
                    WeatherEntry.TEMPERATURE + " " + INT_TYPE + COMMA_SEP +
                    WeatherEntry.FEELS_LIKE_TEMPERATURE + " " + INT_TYPE + COMMA_SEP +
                    WeatherEntry.NEEDS_UMBRELLA + " " + BOOLEAN_TYPE + COMMA_SEP +
                    WeatherEntry.SUITABLE_CLOTHES + " " + STRING_TYPE + COMMA_SEP + " )";


    private static final String SQL_DELETE_TABLE_WEATHER =
            "DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME;

}