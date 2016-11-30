package com.se9project.se_9_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity{
//    Button suBtn;
//    Button loBtn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        suBtn = (Button)findViewById(R.id.signUpBtn);
//        suBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AddFriend.class));
//            }
//        });
//        loBtn=(Button)findViewById(R.id.loginBtn);
//        loBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, login_page.class);
//                startActivity(intent);
//            }
//        });
//    }
//}

import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.content.Intent;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Button suBtn;
    private Button loBtn;
    private EditText mUsername;
    private EditText mPassword;
    private DBHelper mHelper;
    private Context mContext;
    public static Friend account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = (EditText) findViewById(R.id.usernameField);
        mPassword = (EditText) findViewById(R.id.passwordField);
        mContext = this;
        mHelper= new DBHelper(this);


        suBtn = (Button)findViewById(R.id.signUpBtn);
        suBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpPage.class));
            }
        });
        loBtn=(Button)findViewById(R.id.loginBtn);
        loBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHelper.checkLogin(mUsername.getText().toString(),mPassword.getText().toString())){
                    account=mHelper.getLogin(mUsername.getText().toString(),mPassword.getText().toString());
                    Intent intent = new Intent(MainActivity.this, login_page.class);
                    startActivity(intent);
                }
                else {
                    String message = "login Missing";
                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
