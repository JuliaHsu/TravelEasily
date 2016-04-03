package com.example.julia.traveleasily;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 2016/4/2.
 */
public class DBItem {
    public static String TABLE_NAME = "TravelData";
    public static final String KEY_ID = "_id";

    public static final String I_DEPART_COLUMN = "i_depart";
    public static final String I_DEST_COLUMN = "i_dest";
    public static final String I_TRANS_COLUMN = "i_trans";
    public static final String I_DATE_FROM_COLUMN = "i_dateFrom";
    public static final String I_DATE_TO_COLUMN = "i_dateTO";
    public static final String I_NOTE_COLUMN = "i_note";

    public static final String B_ITEM_COLUMN = "b_item";
    public static final String B_AMOUNT_COLUMN = "b_amount";
    public static final String B_CATEGORY_COLUMN = "b_category";
    public static final String B_DATE_COLUMN = "b_date";
    public static final String B_NOTE_COLUMN = "b_note";
    public static final String B_EXPENSE_COLUMN = "b_expense";


    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME +"("+KEY_ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ I_DEPART_COLUMN+
            " TEXT NOT NULL, " + I_DEST_COLUMN + " TEXT NOT NULL, " + I_TRANS_COLUMN + " TEXT NOT NULL, " +
            I_DATE_FROM_COLUMN + " INTEGER NOT NULL, " + I_DATE_TO_COLUMN + " INTEGER NOT NULL, " +
            I_NOTE_COLUMN + " TEXT NOT NULL,"  +  B_ITEM_COLUMN + " TEXT, " +
            B_AMOUNT_COLUMN+" REAL, " +  B_CATEGORY_COLUMN + " TEXT, " + B_DATE_COLUMN+
            " INTEGER, " + B_NOTE_COLUMN+" TEXT, " + B_EXPENSE_COLUMN+" REAL)";





    private SQLiteDatabase db;
    public DBItem(Context context){
        db = TravelDBHelper.getDatabase(context);
    }
    public void close(){
        db.close();
    }

    public Itinerary insert( Itinerary itinerary){
        ContentValues cv = new ContentValues();
        //cv.put(I_TOUR_ID_COLUMN, itinerary.getId());
        //System.out.println("tourId" + itinerary.getId());
        cv.put(I_DEPART_COLUMN, itinerary.getDepartText());
        cv.put(I_DEST_COLUMN, itinerary.getDestText());
        cv.put(I_TRANS_COLUMN, itinerary.getAirBusText());
        cv.put(I_DATE_FROM_COLUMN,itinerary.getDateFrom());
        cv.put(I_DATE_TO_COLUMN, itinerary.getDateTo());
        cv.put(I_NOTE_COLUMN, itinerary.getNote());

        long id = db.insert(TABLE_NAME, null, cv);

        itinerary.setId(id);


        return itinerary;
    }

    public boolean update(Itinerary itinerary){
        ContentValues cv = new ContentValues();


        cv.put(I_DEPART_COLUMN, itinerary.getDepartText());
        cv.put(I_DEST_COLUMN, itinerary.getDestText());
        cv.put(I_TRANS_COLUMN, itinerary.getAirBusText());
        cv.put(I_DATE_FROM_COLUMN, itinerary.getDateFrom());
        cv.put(I_DATE_TO_COLUMN, itinerary.getDateTo());
        cv.put(I_NOTE_COLUMN, itinerary.getNote());
        String where =KEY_ID+"=" +itinerary.getId();

        return db.update(TABLE_NAME, cv, where, null) > 0;

    }

    public boolean delete(long id){
        String where = KEY_ID + "=" + id;
        return db.delete(TABLE_NAME, where, null) > 0;

    }

    public List<Itinerary> getAll(){
        List <Itinerary> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getItineraryRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public Itinerary get(long id){
        Itinerary itinerary = null;
        String where =KEY_ID+ "=" +id;
        Cursor result = db.query(TABLE_NAME, null, where, null, null, null, null, null);
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            itinerary = getItineraryRecord(result);

        }
        result.close();
        return itinerary;
    }

    public Itinerary getItineraryRecord(Cursor cursor){
        Itinerary result = new Itinerary();
        result.setId(cursor.getLong(0));
        result.setDepartText(cursor.getString(1));
        result.setDestText(cursor.getString(2));
        result.setAirBusText(cursor.getString(3));
        result.setDateFrom(cursor.getLong(4));
        result.setDateTo(cursor.getLong(5));
        result.setNote(cursor.getString(6));



        return result;
    }

    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

}
