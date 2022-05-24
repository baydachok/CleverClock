package com.narko.alarmclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewTest extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewRecyclerAdapter recyclerAdapter;
    private SharedPreferences aPreferences;
    private Intent settingsIntent;
    private Bundle arguments;
    static int countDataItems = 0;
    private Button btnAddAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        ArrayList<String> timeValues = new ArrayList<>();
        arguments = getIntent().getExtras();
        initRecyclerView();

        // НА ВСЯКИЙ СЛУЧАЙ НАПОМИНАЮ, ЧТО В СТЕКЕ ДОХУЯ ВСЯКОЙ ТЕСТОВОЙ ХЕРНИ, ОЧИСТИ ЕГО НАХУЙ!

        //dataInitialisation(timeValues);
        if (arguments != null) {
            timeValues.add(arguments.getString("time"));
            dataSave(timeValues);
            timeValues = dataLoad();

        }

        recyclerAdapter = new NewRecyclerAdapter(this, timeValues);
        recyclerView.setAdapter(recyclerAdapter);
        btnAddAlarm = (Button) findViewById(R.id.buttonAdd);
        settingsIntent = new Intent(this, AlarmSettings.class);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<String> dataSave(ArrayList<String> timeValues) {

        aPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor aEditor = aPreferences.edit();
        for (int i = countDataItems; i < timeValues.size(); i++) {
            aEditor.putString("time" + i, timeValues.get(i));
        }
        countDataItems = countDataItems + timeValues.size();
        aEditor.commit();
        return timeValues;
    }

    private ArrayList<String> dataLoad() {
        ArrayList<String> loadData = new ArrayList<>();
        for (int i = 0; i < countDataItems; i++) {
            String time = aPreferences.getString("time" + i, "unknown");
            loadData.add(time);

        }

        return loadData;
    }

    //временный метод инициализации данных
    private void dataInitialisation(ArrayList<String> times) {
        times.add("d1sd");
        times.add("1ds2");
        times.add("1d2s");
        times.add("d1s");
        times.add("dfd");
        times.add("ds2d");
        times.add("dff");
        times.add("ds4df");
        times.add("12s");
        times.add("ds1");
        times.add("d23s");
        times.add("d23");

    }
}