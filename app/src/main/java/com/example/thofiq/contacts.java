package com.example.thofiq;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class contacts extends AppCompatActivity {


    ListView l1,lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        l1=(ListView)findViewById(R.id.Listview);
        get(l1);
    }
public void get(View v)
{
    Cursor Cursor= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
    startManagingCursor(Cursor);

String [] from ={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};


Integer[] to ={android.R.id.text1,android.R.id.text2};
    ContentResolver cr = getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null);
    Integer phnnumbers[]=new Integer[300];

    String names[]=new String[300];
    int i=0;
    if (cur.getCount() > 0) {
        while (cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phoneNumber =  cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            names[i]=name;
            phnnumbers[i]=Integer.parseInt(phoneNumber);
            i++;


        }
    }
    Log.e("aaaaaaaa",phnnumbers[3].toString());

//    SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,Cursor,from,to);
//
//    l1.setAdapter(simpleCursorAdapter);
//    l1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    Toast.makeText(contacts.this,from[2],Toast.LENGTH_LONG).show();
//MyListAdapter adapter=new MyListAdapter(this,from,to);
//lv=findViewById(R.id.Listview);
//lv.setAdapter(adapter);

}

}




