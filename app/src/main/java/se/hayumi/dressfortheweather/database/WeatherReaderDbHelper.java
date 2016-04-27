package se.hayumi.dressfortheweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherReaderDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "DressForTheWeather_Database.db";

    public WeatherReaderDbHelper(Context context) { //DB_Name, Factory, DB_Version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(WeatherReaderContract.getSqlCreateEntries());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(WeatherReaderContract.getSqlDeleteTableWeather());
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }

//    public boolean insertContact(String name){
//        SQLiteDatabase database = this.getWritableDatabase();
//
//    }

}