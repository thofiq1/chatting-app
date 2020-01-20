package com.example.thofiq;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final Integer[] mob;

    public MyListAdapter(Activity context, String[] name, Integer[] mob)
        { super(context,R.layout.activity_list,name);
        this.context=context;
        this.name=name;
        this.mob=mob;

    }

@Override
public View getView(int position, View convertView, ViewGroup Parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        android.view.View rowView = inflater.inflate(R.layout.activity_list,null,true);

        TextView Contactname =rowView.findViewById(R.id.t1);
        TextView Contactno=rowView.findViewById(R.id.t2);

        Contactname.setText(name[position]);
        Contactno.setText(mob[position]);

    return rowView;
    }
}