package com.example.a15017082.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017082 on 25/5/2017.
 */

public class TaskArrayAdapter extends ArrayAdapter<Task> {
    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvName, tvDescription;

    public TaskArrayAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables

        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);

        Task task1 = tasks.get(position);
        int id = task1.getId();
        String name = task1.getTaskName();
        String description = task1.getDescription();
        tvName.setText(id + " : " + name);
        tvDescription.setText(description);

        return rowView;
    }
}
