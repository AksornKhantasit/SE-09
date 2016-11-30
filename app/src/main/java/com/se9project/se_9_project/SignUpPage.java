package com.se9project.se_9_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    private Button signUpbtn;
    private EditText emailTxt,passTxt,rePassTxt;
    private EditText userTxt;
    private Bundle s;
    private DBHelper mHelper;
    private int ID = -1;
    public static int i=0;
    BusDB mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        s=savedInstanceState;

        signUpbtn = (Button)findViewById(R.id.bSignUpBtn);
        userTxt = (EditText) findViewById(R.id.userField);
        emailTxt = (EditText)findViewById(R.id.emailField);
        passTxt = (EditText)findViewById(R.id.Password);
        rePassTxt = (EditText)findViewById(R.id.rePassword);
        mHelper = new DBHelper(this);
        if(i==0){
            mDB= new BusDB(this);
            Bus bus= new Bus(8,0,1);

            mDB.addBus(bus);
            Bus bus1= new Bus(8,30,2);
            Bus bus2= new Bus(9,0,3);
            Bus bus3= new Bus(8,30,4);
            Bus bus4= new Bus(10,0,5);
            Bus bus5= new Bus(10,30,6);
            Bus bus6= new Bus(11,0,7);
            Bus bus7= new Bus(11,30,8);
            Bus bus8= new Bus(12,0,9);
            mDB.addBus(bus1);
            mDB.addBus(bus2);
            mDB.addBus(bus3);
            mDB.addBus(bus4);
            mDB.addBus(bus5);
            mDB.addBus(bus6);
            mDB.addBus(bus7);
            mDB.addBus(bus8);
            i++;
        }
        signUpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;


                if(checkPassword(passTxt.getText(),rePassTxt.getText())==true&&checkEmail(emailTxt.getText())==true){
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(SignUpPage.this);
                    builder.setTitle(getString(R.string.app_name));
                    builder.setMessage("Do you want to Sing-up?");

                    builder.setPositiveButton(getString(android.R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Friend friend = new Friend();
                                    friend.setUsername(userTxt.getText().toString());
                                    friend.setPassword(passTxt.getText().toString());
                                    friend.setEmail(emailTxt.getText().toString());

                                    if (ID == -1) {
                                        mHelper.addFriend(friend);
                                    } else {
                                        friend.setId(ID);
                                        //mHelper.updateFriend(friend);

                                    }
                                    finish();
                                }
                            });

                    builder.setNegativeButton(getString(android.R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });


                    builder.show();
                }
                if(!checkEmail(emailTxt.getText())){
                    CharSequence text = "Email incorrect";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                if(!checkPassword(passTxt.getText(),rePassTxt.getText())){
                    CharSequence text = "Password incorrect";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }
    protected boolean checkEmail(CharSequence email){

        String tmp[] = email.toString().split("@");
        if(tmp.length==2){
            String domain = tmp[1];
            if(domain.equals("hotmail.com")||domain.equals("gmail.com")||domain.equals("yahoo.com")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    protected boolean checkPassword(CharSequence password,CharSequence rePassword){
        if(password.toString().length()>6&&password.toString().length()<15){
            if(password.toString().equals(rePassword.toString())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}