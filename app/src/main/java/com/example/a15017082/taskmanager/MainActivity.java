package com.example.a15017082.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ArrayAdapter aa;
    ArrayList<Task> task;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);

        DBHelper db = new DBHelper(MainActivity.this);
        task = db.getAllTask();

        aa = new TaskArrayAdapter(this, R.layout.row, task);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();

        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(MainActivity.this, addActivity.class);
                startActivity(i);
            }
        });

    }
}
