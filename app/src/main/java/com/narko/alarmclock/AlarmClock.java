package com.narko.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlarmClock extends AppCompatActivity {
// Вот это всё скоро будет нахуй не нужно)
    private List<ListClock> listClocks;
    private ListView listContainer;
    private AlarmClockAdapter adapter;
    private Intent settingsIntent;
    private Bundle arguments;
    private Button addAlarmClock;
    private String time =  "";
// Твои дни сочтены, ХУЕТА КОТОРАЯ ЕБАЛА МНЕ МОЗГ УЖЕ 2 МЕСЯЦА!!!!!!!!!!!!!!!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmclock);

        listClocks = new ArrayList();
        settingsIntent = new Intent(this, AlarmSettings.class);
        listContainer = findViewById(R.id.countriesList);
        adapter = new AlarmClockAdapter(this, R.layout.alarm_clock_list_item, listClocks);
        arguments = getIntent().getExtras();
        addAlarmClock = findViewById(R.id.newAlarmClock);

        listContainer.setAdapter(adapter);
        if(arguments != null)

        addAlarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(settingsIntent);
            }
        });



    }


}
