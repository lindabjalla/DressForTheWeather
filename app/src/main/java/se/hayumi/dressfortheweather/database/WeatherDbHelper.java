package se.hayumi.dressfortheweather.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDbHelper extends SQLiteOpenHelper{

    )
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DressForTheWeather_Database.db";

    public WeatherDbHelper(Context context) { //DB_Name, Factory, DB_Version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(SQL_CREATE_TABLE_WEATHER);
        onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSql(SQL_DELETE_TABLE_WEATHER);
        onCreate(database);
    }

    public boolean insertContact(String name){
        SQLiteDatabase database = this.getWritableDatabase();

    }

}
