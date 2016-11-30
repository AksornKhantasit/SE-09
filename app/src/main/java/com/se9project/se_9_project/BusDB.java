package com.se9project.se_9_project;

/**
 * Created by jenny_000 on 11/27/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BusDB extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public BusDB(Context context) {
        super(context, Bus.DATABASE_NAME, null, Bus.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BUS_TABLE = String.format("CREATE TABLE %s " +
                        "( %s TEXT, %s TEXT, %s  TEXT PRIMARY KEY , %s TEXT)",
                Bus.TABLE,
                Bus.Column.HOUR,
                Bus.Column.MINUTE,
                Bus.Column.NUMBER,
                Bus.Column.AMOUNT);

        Log.i(TAG, CREATE_BUS_TABLE);
        db.execSQL(CREATE_BUS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_BUS_TABLE = "DROP TABLE IF EXISTS " + Bus.TABLE;
        db.execSQL(DROP_BUS_TABLE);
        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);
        onCreate(db);
    }
    public void addBus(Bus bus) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( Bus.Column.NUMBER, bus.getNumber());
        values.put( Bus.Column.AMOUNT, bus.getAmount());
        values.put( Bus.Column.HOUR, bus.getHour());
        values.put( Bus.Column.MINUTE, bus.getMinute());

        sqLiteDatabase.insert(Bus.TABLE, null, values);

        sqLiteDatabase.close();
    }
    public Bus getBus(int num) {

        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query( Bus.TABLE,
                null,
                Bus.Column.NUMBER + " = ? ",
                new String[] {""+num},
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Bus bus = new Bus();
        bus.setHour((int) cursor.getLong(0));
        bus.setMinute((int) cursor.getLong(1));
        bus.setNumber((int) cursor.getLong(2));
        bus.setAmount((int) cursor.getLong(3));


        return bus;
    }
    public List<String> getBusList() {
        List<String> bus = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Bus.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        int m,m1;
        long h,h1;
//
//        while(!cursor.isAfterLast()) {
//            h=cursor.getLong(0);
//            m=Integer.parseInt(cursor.getString(1));
//            if(m==0){
//                h1=h-1;
//                m1=59;
//                h=h;
//                m=30;
//            }else {
//                h1=h;
//                m1=29;
//                m=0;
//            }
//            bus.add("Time: "  +h+":"+m+"-"+h1+ ":" +m1 +" No: " +
//                    cursor.getString(2) + " Amount: " +
//                    cursor.getString(3));
//            cursor.moveToNext();
//        }
        while(!cursor.isAfterLast()) {

            bus.add("Time: "+cursor.getLong(0) + ":" +
                    cursor.getString(1) + " No: " +
                    cursor.getString(2) + " Amount: " +
                    cursor.getString(3));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return bus;
    }
    public List<Bus> getAllBus() {

        String QUERY_ALL_FRIEND = "SELECT * FROM " + Bus.TABLE;

        List<Bus> buslist = new ArrayList<Bus>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Bus bus = new Bus();
            bus.setHour((int) cursor.getLong(0));
            bus.setMinute((int) cursor.getLong(1));
            bus.setNumber((int) cursor.getLong(2));
            bus.setAmount((int) cursor.getLong(3));

            buslist.add(bus);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();


        return buslist;
    }
    public void updateBus(Bus bus) {

        sqLiteDatabase  = this.getWritableDatabase();
        bus.addAmount();
        ContentValues values = new ContentValues();
        values.put( Bus.Column.NUMBER, bus.getNumber());
        values.put( Bus.Column.AMOUNT, bus.getAmount());
        values.put( Bus.Column.HOUR, bus.getHour());
        values.put( Bus.Column.MINUTE, bus.getMinute());

        int row = sqLiteDatabase.update(Bus.TABLE,
                values,
                Bus.Column.NUMBER + " = ? ",
                new String[] { String.valueOf(bus.getNumber()) });

        sqLiteDatabase.close();
    }
    public int findBus(int hour,int min) {
        int h,m;
        if(min>30&&min<60){
            h=hour+1;
            m=0;
        }else {
            h=hour;
            m=30;
        }
        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query( Bus.TABLE,
                null,
                Bus.Column.HOUR + " = ?  AND "+Bus.Column.MINUTE+" = ?",
                new String[] {""+h,""+m},
                null,
                null,
                null,
                null);
        Bus bus = new Bus();
        if(Integer.parseInt(bus.getAmount())>10)
            return -1;
        if (cursor != null) {
           if( cursor.moveToFirst()) {



               bus.setHour((int) cursor.getLong(0));
               bus.setMinute((int) cursor.getLong(1));
               bus.setNumber((int) cursor.getLong(2));
               bus.setAmount((int) cursor.getLong(3));


               return Integer.parseInt(bus.getNumber());
           }
        }


        return 0;
    }
}
