package se.hayumi.dressfortheweather.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherReaderDbHelper extends SQLiteOpenHelper{

    //Database Info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DressForTheWeather_Database.db";

    //Table Name
    public static final String TABLE_NAME = "WeatherData";

    //Weather Table Columns
    private static final String COLUMN_NAME_ENTRY_ID = "entryId";
    private static final String COLUMN_NAME_DATE_TIME = "dateTime";
    private static final String COLUMN_NAME_CONDITION = "condition";
    private static final int COLUMN_NAME_TEMPERATURE = 20;
    private static final int COLUMN_NAME_FEELS_LIKE_TEMPERATURE = 25;
    //private static final String COLUMN_NAME_CLOTHES_TO_WEAR = "";
    //public static final String COLUMN_NAME_FETCHED_TIME = " ";

    //Metainformation för DataBasen
    private static final String STRING_TYPE = " text";
    private static final String INT_TYPE = "integer";
    private static final String BOOLEAN_TYPE = "boolean";
    private static final String COMMA_SEP = ",";
    private static final String SPACE = " ";

    public WeatherReaderDbHelper(Context context) { //DB_Name, Factory, DB_Version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String CREATE_WEATHER_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                                        COLUMN_NAME_ENTRY_ID + "INTEGER PRIMARY KEY," +
                                        COLUMN_NAME_DATE_TIME + SPACE + STRING_TYPE + COMMA_SEP +
                                        COLUMN_NAME_CONDITION + SPACE + STRING_TYPE + COMMA_SEP +
                                        COLUMN_NAME_TEMPERATURE + SPACE + INT_TYPE + COMMA_SEP +
                                        COLUMN_NAME_FEELS_LIKE_TEMPERATURE + " " + INT_TYPE + COMMA_SEP + " )";
                                        //COLUMN_NAME_CLOTHES_TO_WEAR + SPACE + STRING_TYPE + COMMA_SEP + " )";
        
        database.execSQL(CREATE_WEATHER_TABLE);
        onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String SQL_DELETE_TABLE_WEATHER = "DROP TABLE IF EXISTS " + TABLE_NAME;

        database.execSQL(SQL_DELETE_TABLE_WEATHER);
        onCreate(database);
    }

    public boolean insertContact(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        return false;
    }

}
