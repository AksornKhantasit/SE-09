package com.se9project.se_9_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;


public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    String s,t,g;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, Friend.DATABASE_NAME, null, Friend.DATABASE_VERSION);
        s=context.getPackageResourcePath();
        t=context.getDatabasePath(Friend.DATABASE_NAME).toString();

    }
    public String gettag(){
        return s;
    }
    public String get(){

        return t;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT , %s TEXT, %s TEXT)",
                Friend.TABLE,
                Friend.Column.ID,
                Friend.Column.USERNAME,
                Friend.Column.PASSWORD,
                Friend.Column.EMAIL);

        Log.i(TAG, CREATE_FRIEND_TABLE);

        // create friend table
        db.execSQL(CREATE_FRIEND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Friend.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }



    //CRUD ( CREATE, READ, UPDATE, DELETE )

    //CREATE
    public void addFriend(Friend friend) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(Friend.Column.ID, friend.getId());
        values.put(Friend.Column.USERNAME, friend.getUsername());
        values.put(Friend.Column.PASSWORD, friend.getPassword());
        values.put(Friend.Column.EMAIL, friend.getEmail());

        sqLiteDatabase.insert(Friend.TABLE, null, values);

        sqLiteDatabase.close();
    }

    //READ
    public Friend getFriend(String id) {

        sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query( Friend.TABLE,
                null,
                Friend.Column.ID + " = ? ",
                new String[] { id },
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Friend friend = new Friend();

        friend.setId((int) cursor.getLong(0));
        friend.setUsername(cursor.getString(1));
        friend.setPassword(cursor.getString(2));
        friend.setEmail(cursor.getString(3));

        return friend;
    }
    public List<String> getFriendList() {
        List<String> friends = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Friend.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            friends.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return friends;
    }

    /**
     * Call when need List<Friend> instead of List<String>
     * @return List of friends.
     */
    public List<Friend> getAllFriend() {

        String QUERY_ALL_FRIEND = "SELECT * FROM " + Friend.TABLE;

        List<Friend> friends = new ArrayList<Friend>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Friend friend = new Friend();
            friend.setId((int) cursor.getLong(0));
            friend.setUsername(cursor.getString(1));
            friend.setPassword(cursor.getString(2));
            friend.setEmail(cursor.getString(3));

            friends.add(friend);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();


        return friends;
    }


    //UPDATE
    public void updateFriend(Friend friend) {

        sqLiteDatabase  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Friend.Column.ID, friend.getId());
        values.put(Friend.Column.USERNAME, friend.getUsername());
        values.put(Friend.Column.PASSWORD, friend.getPassword());
        values.put(Friend.Column.EMAIL, friend.getEmail());

        int row = sqLiteDatabase.update(Friend.TABLE,
                values,
                Friend.Column.ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });

        sqLiteDatabase.close();
    }
    public void deleteAll() {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Friend.TABLE,null,null);
        sqLiteDatabase.execSQL("delete * from"+ Friend.TABLE);
        sqLiteDatabase.execSQL("TRUNCATE table" + Friend.TABLE);
        sqLiteDatabase.close();
    }

    //DELETE
    public void deleteFriend(String id) {

        sqLiteDatabase = this.getWritableDatabase();

/*        sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = " + id, null);

        sqLiteDatabase.close();
    }
    public Friend getLogin(String user,String pass){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query( Friend.TABLE,
                null,
                Friend.Column.USERNAME + " = ? AND " +
                        Friend.Column.PASSWORD + " = ?",
                new String[] { user,pass },
                null,
                null,
                null,
                null);


        Friend friend = new Friend();
        if (cursor != null) {
            if(cursor.moveToFirst()) {
                friend.setId((int) cursor.getLong(0));
                friend.setUsername(cursor.getString(1));
                friend.setPassword(cursor.getString(2));
                friend.setEmail(cursor.getString(3));
                return friend;
            }
        }
        return null;


    }
    public boolean checkLogin(String user,String pass){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query( Friend.TABLE,
                null,
                Friend.Column.USERNAME + " = ? AND " +
                        Friend.Column.PASSWORD + " = ?",
                new String[] { user,pass },
                null,
                null,
                null,
                null);

        if (cursor != null) {
            if( cursor.moveToFirst())
                return true;
        }
        return  false;
    }
}
