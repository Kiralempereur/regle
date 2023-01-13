package com.example.bloodmenstrue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.PeriodicSync;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class pagec extends AppCompatActivity {
  Button birth,today,calculate;
  TextView resultat;
  DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pagec);
        birth= findViewById(R.id.birth);
        today= findViewById(R.id.today);
        calculate= findViewById(R.id.calculate);
        resultat= findViewById(R.id.resultat);

        Calendar calendar= Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String date = SimpleDateFormat.getInstance().format(Calendar.getInstance().getTime());
        today.setText(date);

        birth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        pagec.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , dateSetListener1, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        android.R.color.holo_red_dark));
                datePickerDialog.show();
            }
        });
        dateSetListener1= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month +1;
                String date = day + "/" + month + "/" + year;
                birth.setText(date);
            }
        };


        today.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        pagec.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , dateSetListener2, year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        android.R.color.holo_red_dark));
                datePickerDialog.show();
            }
        });
        dateSetListener2= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month +1;
                String date = day + "/" + month + "/" + year;
                today.setText(date);
            }
        };
        
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDate= birth.getText().toString();
                String eDate= today.getText().toString();
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yy");
                try {

                    Date date1 = simpleDateFormat1.parse(sDate);
                    Date date2 = simpleDateFormat1.parse(eDate);



                    long startDate = date1.getTime();
                    long endDate = date2.getTime();
                    if (startDate <= endDate) {
                     Period period = new Period(startDate,endDate,PeriodType.yearMonthDay());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            int years = period.getYears();
                            int months = period.getMonths();
                            int days = period.getDays();
                            resultat.setText(years + "years | " + months + "months | " + days + "days");
                        } else {
                            Toast.makeText(getApplicationContext()
                                    , "Birth Date should not be larger than today date"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    
                }
            }
        });
    }
}