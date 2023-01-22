package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.myapplication.Doctor.DoctorPage;
import com.example.myapplication.Patient.PatientPage;
import com.example.myapplication.Patient.PatientRegistration;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edusername = (EditText) findViewById(R.id.username);
        EditText edpassword = (EditText) findViewById(R.id.password);
        TextView account = (TextView) findViewById(R.id.account);
        final boolean[] passwordVisible = {false};

        MaterialButton button1 = (MaterialButton) findViewById(R.id.button1);


        edpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right=2;
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX()>=edpassword.getRight()-edpassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=edpassword.getSelectionEnd();
                        if (passwordVisible[0]){
                            edpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            edpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible[0] =false;
                        }else{
                            edpassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                            edpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible[0] =true;

                        }
                        edpassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edusername.getText().toString();
                String password=edpassword.getText().toString();
                Database db1 =new Database(MainActivity.this,"Ronit",null,1);
                if(username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(),"please fill all details",Toast.LENGTH_SHORT).show();
                }else{
                    if(db1.login(username,password)==1){
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString("username", String.valueOf(username));
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, PatientPage.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        String text="Dont have an account ? Sign Up";

        SpannableString s = new SpannableString(text);

        ClickableSpan s1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent launchActivity1=new Intent(MainActivity.this, PatientRegistration.class);
                startActivity(launchActivity1);
            }
        };

        s.setSpan(s1,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        account.setText(s);
        account.setMovementMethod(LinkMovementMethod.getInstance());

       /* account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity1=new Intent(MainActivity.this,Registration.class);
                startActivity(launchActivity1);
            }
        });*/
    }
}