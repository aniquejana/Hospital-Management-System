package com.example.myapplication.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {

    private DatePickerDialog d;
    private TimePickerDialog t;
    private MaterialButton dateButton,timeButton,btnbook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        EditText ed1, ed2, ed3, ed4;
        TextView tv;

        tv = findViewById(R.id.signup1);
        ed1 = findViewById(R.id.name1);
        ed2 = findViewById(R.id.email1);
        ed3 = findViewById(R.id.newpassword1);
        ed4 = findViewById(R.id.confirmpassword1);
        dateButton=findViewById(R.id.button11);
        timeButton=findViewById(R.id.button111);
        btnbook=findViewById(R.id.button2);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();

        String title = it.getStringExtra("text1");
        String fname = it.getStringExtra("text2");
        String addr = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fname);
        ed2.setText(addr);
        ed3.setText(contact);
        ed4.setText("Consultation fees : " + fees + "/-");

        datePick();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.show();
            }
        });

        timePick();

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.show();
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookAppointment.this,"Appointment booked successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void datePick(){
        DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };

        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        int style=AlertDialog.THEME_HOLO_DARK;

        d=new DatePickerDialog(this,style,d1,year,month,day);
        d.getDatePicker().setMinDate(c.getTimeInMillis()+86400000);


    }

    private void timePick(){
        TimePickerDialog.OnTimeSetListener t1=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar c=Calendar.getInstance();
        int hrs=c.get(Calendar.HOUR);
        int mins=c.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;

        t=new TimePickerDialog(this,style,t1,hrs,mins,true);

    }
}