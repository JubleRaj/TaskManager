package com.example.a15017082.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class addActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etName, etDescription, etSecond;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        etName = (EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etSecond = (EditText)findViewById(R.id.etSecond);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //getting data from the edit text field
                String name =  etName.getText().toString();
                String description =  etDescription.getText().toString();
                String seconds =  etSecond.getText().toString();

                int sec =  Integer.parseInt(seconds);

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(addActivity.this);

                // Insert a task
                db.insertTask(name, description);
                db.close();

                Toast.makeText(addActivity.this, "Inserted!",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(addActivity.this, MainActivity.class);
                startActivity(i);

                //Notification launch
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, sec);

                Intent intent = new Intent(addActivity.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("name", name);
                intent.putExtra("description", description);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        addActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(addActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
