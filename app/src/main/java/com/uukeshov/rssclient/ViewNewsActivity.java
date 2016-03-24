package com.uukeshov.rssclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewNewsActivity extends AppCompatActivity {
    NewsListAdapter adapter;
    ListView list;
    final String LOG_TAG = "ViewNewsActivityLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "ViewNewsActivity 1");
        adapter = new NewsListAdapter(ViewNewsActivity.this, getData(), ViewNewsActivity.this.getApplicationContext());
        //getSupportActionBar().setTitle("Новости(" + String.valueOf(adapter.getCount()) + ")");
        if (Integer.valueOf(adapter.getCount()) > 0) {
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            Log.d(LOG_TAG, "ViewNewsActivity 2");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }

    public ArrayList<RSS> getData() {

        RSSDb db = new RSSDb(ViewNewsActivity.this.getApplicationContext());
        final ArrayList<RSS> stringItems = new ArrayList<RSS>();

        ArrayList<RSS> pr = (ArrayList<RSS>) db.getAllNews();

        for (RSS p : pr) {
            stringItems.add(p);
        }
        return stringItems;
    }

}
