package se.hayumi.dressfortheweather.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import se.hayumi.dressfortheweather.storage.FeedReaderDbHelper;

public final class WeatherReaderContract {
    
    public WeatherReaderContract() {}

    public static abstract class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "WeatherData";
        public static final String COLUMN_NAME_ENTRY_ID = "entryId";
        public static final String COLUMN_NAME_DATE_TIME = "dateTime";
        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final int COLUMN_NAME_TEMPERATURE = 20;
        public static final int COLUMN_NAME_FEELS_LIKE_TEMPERATURE = 25;
        public static final String COLUMN_NAME_CLOTHES_TO_WEAR = "";
        //public static final String COLUMN_NAME_FETCHED_TIME = " ";
    }

    //Metainformation för DataBasen
    private static final String STRING_TYPE = " text";
    private static final String INT_TYPE = "integer";
    private static final String BOOLEAN_TYPE = "boolean";
    private static final String COMMA_SEP = ",";
    private static final String SPACE = " ";

    public static final String SQL_CREATE_TABLE_WEATHER =
            "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                              WeatherEntry.COLUMN_NAME_ENTRY_ID + "INTEGER PRIMARY KEY," +
                              WeatherEntry.COLUMN_NAME_DATE_TIME + SPACE + STRING_TYPE + COMMA_SEP +
                              WeatherEntry.COLUMN_NAME_CONDITION + SPACE + STRING_TYPE + COMMA_SEP +
                              WeatherEntry.COLUMN_NAME_TEMPERATURE + SPACE + INT_TYPE + COMMA_SEP +
                              WeatherEntry.COLUMN_NAME_FEELS_LIKE_TEMPERATURE + " " + INT_TYPE + COMMA_SEP +
                              WeatherEntry.COLUMN_NAME_CLOTHES_TO_WEAR + SPACE + STRING_TYPE + COMMA_SEP + " )";


    public static final String SQL_DELETE_TABLE_WEATHER =
            "DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME;

    public void addValues(String dateTime, String condition, int temperature, int feelsLikeTemperature){

        //Create and/or open the database for writing
        SQLiteDatabase database = WeatherDbHelper.getWritableDatabase();

        database.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(WeatherEntry.COLUMN_NAME_ENTRY_ID, entryId):
        values.put(WeatherEntry.COLUMN_NAME_DATE_TIME, dateTime);
        values.put(WeatherEntry.COLUMN_NAME_CONDITION, condition);
       // values.put(WeatherEntry.COLUMN_NAME_TEMPERATURE, temperature);
        //values.put(WeatherEntry.COLUMN_NAME_FEELS_LIKE_TEMPERATURE, feelsLikeTemperature);

        long newRowId;
       // newRowId = database.insert(WeatherEntry.TABLE_NAME,
         //                          WeatherEntry.COLUMN_NAME_NULLABLE, values);
    }

}