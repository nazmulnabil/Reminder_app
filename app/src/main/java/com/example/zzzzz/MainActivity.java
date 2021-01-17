package com.example.zzzzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.widget.Toast.*;


public class MainActivity extends AppCompatActivity {

    private TimePicker timePicker;
    TextInputLayout textInputLayout;
    TextInputEditText editText;
    private Button buttonset;
    String et;
    int i=0;

     AlarmDBHelper alarmDBHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textInputLayout=findViewById(R.id.textinputlayoutid);
        editText=findViewById(R.id.cityetid);


        timePicker = findViewById(R.id.tp);


        final ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();


        buttonset = findViewById(R.id.reminderbtn);
        buttonset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= 23) {

                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );

                }else {

                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0);

                }


                while (i<=20){
                    String data=textInputLayout.getEditText().getText().toString().trim();

                    alarmDBHelper=new AlarmDBHelper(MainActivity.this);
                    AlarmlistActivity alarmlist=new AlarmlistActivity();
                    // cursor=alarmDBHelper.displaydata();

                    Intent intent = new Intent(getApplicationContext(), MyReceiver.class);



                    intent.putExtra("edt",data);

                    PendingIntent pendingIntent = PendingIntent.
                            getBroadcast(getApplicationContext(), i, intent, 0);




                    AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    long timeinMillis=calendar.getTimeInMillis();
                    alarmManager.set(AlarmManager.RTC_WAKEUP,timeinMillis,pendingIntent);

                    intentArray.add(pendingIntent);
                    i++;

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
                    String time = sdf.format(calendar.getTime());

                    alarmDBHelper.insertdata(data,time);
                  Toast.makeText(MainActivity.this,"reminder saved",LENGTH_SHORT).show();
                    break;

                }



            }
        });


    }

}