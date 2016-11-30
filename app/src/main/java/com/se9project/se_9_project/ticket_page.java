package com.se9project.se_9_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ticket_page extends AppCompatActivity {
    public Bundle bundle;
    public String Qhour;
    public String Qmin;
    public String Qbusno;
    TextView user;
    TextView time;
    TextView busno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_page);
        bundle = getIntent().getExtras();
        Qhour=bundle.getString("hour");
        Qmin=bundle.getString("min");
        Qbusno=bundle.getString("busno");
        user=(TextView)findViewById(R.id.txtUsername);
        time=(TextView)findViewById(R.id.txtTime);
        busno=(TextView)findViewById(R.id.txtBusNo);
        if(MainActivity.account!=null){
            user.setText(MainActivity.account.getUsername());
        }
        time.setText(Qhour+":"+Qmin);
        busno.setText(Qbusno);
    }
}
