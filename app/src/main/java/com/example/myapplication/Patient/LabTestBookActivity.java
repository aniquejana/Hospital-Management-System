package com.example.myapplication.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database;
import com.example.myapplication.R;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpin,eddate,edtime,edotype;
    Button btnBooking;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.name2);
        edaddress = findViewById(R.id.address);
        edcontact = findViewById(R.id.contactnumber);
        edpin = findViewById(R.id.pincode);
        eddate = findViewById(R.id.date);
        edtime = findViewById(R.id.time);
        edotype = findViewById(R.id.otype);
        btnBooking = findViewById(R.id.button2);

        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();


                Database db =new Database(getApplicationContext(),"Patient",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(), Integer.parseInt(edpin.getText().toString()),eddate.getText().toString(),edtime.getText().toString(),edotype.getText().toString());
                db.removeCart(username,"lab");
                Toast.makeText(LabTestBookActivity.this, "Your Booking is Done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,PatientPage.class));
            }
        });

    }
}