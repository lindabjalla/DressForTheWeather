package se.hayumi.dressfortheweather.storage;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    public FeedReaderContract() {}

    public static abstract class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "weather";
        public static final String COLUMN_NAME_ENTRY_ID = "entryId";
        public static final String COLUMN_NAME_DATE_TIME = "dateTime";
        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_FEELS_LIKE = "feelsLike";
        public static final String COLUMN_NAME_CLOTHES_TO_WEAR = "clothesToWear";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_DATE_TIME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CONDITION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_TEMPERATURE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_FEELS_LIKE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CLOTHES_TO_WEAR + TEXT_TYPE + COMMA_SEP +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static String getSqlCreateEntries() {
        return SQL_CREATE_ENTRIES;
    }

    public static String getSqlDeleteEntries() {
        return SQL_DELETE_ENTRIES;
    }
}
