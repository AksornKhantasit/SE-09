package com.se9project.se_9_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {

    private EditText edtHour;
    private EditText edtMin;
    private EditText edtBusNo;

    private Button btnOk;
    private Button btnCalcel;
    private Button btncheck;
    private Button btntime;
    private int bus;
    private Context mContext;
    private BusDB mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        edtHour = (EditText)findViewById(R.id.edtHour);
        edtMin = (EditText)findViewById(R.id.edtMin);
        edtBusNo = (EditText)findViewById(R.id.edtBusNo);
        btntime = (Button)findViewById(R.id.timetb);

        mDB= new BusDB(this);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCalcel = (Button)findViewById(R.id.btnCancel);
        btncheck = (Button)findViewById(R.id.checkbut);
        mContext = this;

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try{
                    bus = mDB.findBus(Integer.parseInt(edtHour.getText().toString()),Integer.parseInt(edtMin.getText().toString()));
                    edtBusNo.setText(String.valueOf(bus));}
                    catch (Exception e){
                        Toast.makeText(mContext, "please enter Time", Toast.LENGTH_SHORT).show();
                    }


            }
        });
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this,data_page.class);
                startActivity(intent);
            }
        });



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bus>0){
                    mDB.updateBus(mDB.getBus(bus));
                Intent intent = new Intent(login_page.this,ticket_page.class);
                intent.putExtra("hour",edtHour.getText().toString());
                intent.putExtra("min",edtMin.getText().toString());
                intent.putExtra("busno",edtBusNo.getText().toString());
                startActivity(intent);}
                else if(bus==0){
                    Toast.makeText(mContext, "Don't have Bus in this Time!", Toast.LENGTH_SHORT).show();
                }
                else if(bus==-1){
                    Toast.makeText(mContext, "This bus is Full!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_page.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
