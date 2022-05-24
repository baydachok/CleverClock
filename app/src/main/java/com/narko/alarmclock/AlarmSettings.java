    package com.narko.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

    public class AlarmSettings extends AppCompatActivity {

// Боже, как же я ОХУЕНЕН
        TextView periodCheck;
        Button set;
        Button cancel;
        TimePicker timePicker;
        PendingIntent pn1, pn2;
        Intent alarmIntent, valueIntent, alarmSignalIntent ;

        AlarmManager alarmManager;
        int HourNow;
        int MinuteNow;
        int SecondNow;
        Calendar time;



        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alarm_settings);


            periodCheck = findViewById(R.id.textView);
            set = findViewById(R.id.btnSet);
            cancel = findViewById(R.id.btnCancel);
            timePicker = this.findViewById(R.id.timePicker);
            alarmIntent = new Intent(AlarmSettings.this, AlarmReceiver.class);
            valueIntent = new Intent(AlarmSettings.this, AlarmClock.class);
            alarmSignalIntent = new Intent(AlarmSettings.this, AlarmSignal.class    );
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            pn1 = PendingIntent.getBroadcast(AlarmSettings.this, 0, alarmIntent, 0);
            pn2 =  PendingIntent.getBroadcast(AlarmSettings.this, 0, new Intent("DoNothing"), 0);
            time = Calendar.getInstance();
             HourNow = time.get(Calendar.HOUR_OF_DAY);
             MinuteNow = time.get(Calendar.MINUTE);
             SecondNow = time.get(Calendar.SECOND);

            timePicker.setIs24HourView(true);


            set.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    int HourAfter = timePicker.getHour();
                    int MinuteAfter = timePicker.getMinute();
                    String MinuteOutput = "0";
                    if(MinuteAfter/10 !=0) MinuteOutput = String.valueOf(MinuteAfter);
                    else MinuteOutput = MinuteOutput + MinuteAfter;
                    valueIntent.putExtra("time", String.valueOf(HourAfter + ":" + MinuteOutput ));
                    messageOutput((int)calculateTime(HourNow,MinuteNow,HourAfter,MinuteAfter));
                    AlarmManager.AlarmClockInfo ac= new AlarmManager.AlarmClockInfo(System.currentTimeMillis() + calculateTime(HourNow,MinuteNow,HourAfter,MinuteAfter, SecondNow),
                                    pn2);
                    //alarmManager.setAlarmClock(ac,pn1);
                    startActivity(valueIntent);


                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(alarmManager != null)
                alarmManager.cancel(pn1);

                }
            });

        }
// Даааааа, на отладку этой параши ушёл не один вечер...
        private void messageOutput(int miles) {
            int Hour = miles/3600000;
            int Minute = (miles - Hour*3600000)/60000;
                Toast.makeText(this, "будильник сработает через " + Hour+ " ч. " + Minute + " мин." , Toast.LENGTH_SHORT).show();


        }
        private long calculateTime(int HourNow, int MinuteNow, int HourAfter, int MinuteAfter, int SecondNow){
            long millisecondsNow = HourNow*3600000 + MinuteNow*60000;
            long millisecondsAfter = HourAfter*3600000 + MinuteAfter*60000;
            if(millisecondsNow>millisecondsAfter) return (24*3600000-(millisecondsNow-millisecondsAfter)) - (60-SecondNow)*1000;
            else{
                if(millisecondsAfter-millisecondsNow == 0) return (23*3600000 + 59*60000) - (60-SecondNow)*1000;
                else return (millisecondsAfter-millisecondsNow) - (60-SecondNow)*1000;
            }

        }
        private long calculateTime(int HourNow, int MinuteNow, int HourAfter, int MinuteAfter){
            long millisecondsNow = HourNow*3600000 + MinuteNow*60000;
            long millisecondsAfter = HourAfter*3600000 + MinuteAfter*60000;
            if(millisecondsNow>millisecondsAfter) return 24*3600000-(millisecondsNow-millisecondsAfter);
            else{
                if(millisecondsAfter-millisecondsNow == 0) return 23*3600000 + 59*60000;
                else return millisecondsAfter-millisecondsNow;
            }

        }

    }