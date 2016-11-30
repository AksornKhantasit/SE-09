package com.se9project.se_9_project;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

public class data_page extends ListActivity {

    DBHelper mHelper;
    List<Friend> friends;
    BusDB mDB;
    List<Bus> busList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper=new DBHelper(this);
        mDB= new BusDB(this);
        List<String> bb = mDB.getBusList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, bb);
        setListAdapter(adapter);
    }
}
