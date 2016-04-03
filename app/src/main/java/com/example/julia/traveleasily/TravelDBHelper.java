package com.example.julia.traveleasily;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


/**
 * Created by Julia on 2016/4/2.
 */
public class TravelDBHelper extends SQLiteOpenHelper {
    public static final String dataBaseName = "TravelData.db";
    public static final int VERSION= 7;
    private static SQLiteDatabase database;
    public TravelDBHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, name, factory, version);
    }
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new TravelDBHelper(context, dataBaseName,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBItem.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBItem.TABLE_NAME);
        onCreate(db);
    }

}