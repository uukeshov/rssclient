package com.uukeshov.rssclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "MainActivityLog";
    private ProgressBar progressBar;
    private onbuttonclickHttpPost mTask = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;
    public String title;
    public String link;
    public String path;
    public String description;
    public String pubDate;
    public String text;
    public int count;
    public boolean flag = false;
    public String urlString = "http://www.vesti.ru/vesti.rss";
    //"http://api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=1&mode=json&APPID=604e5568dfa8cd2968bb2766fcf4ac8b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        downloadPage();
    }

    private void downloadPage() {
        if (mTask != null
                && mTask.getStatus() != onbuttonclickHttpPost.Status.FINISHED) {
            mTask.cancel(true);
        }
        mTask = (onbuttonclickHttpPost) new onbuttonclickHttpPost().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTask != null
                && mTask.getStatus() != onbuttonclickHttpPost.Status.FINISHED) {
            mTask.cancel(true);
            mTask = null;
        }
    }

    public class onbuttonclickHttpPost extends AsyncTask<String, Void, Void> {
        public class execute {
        }

        @Override
        protected void onPreExecute() {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                RSSDb db = new RSSDb(MainActivity.this);
                db.deleteAllNews();

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                InputStream stream = conn.getInputStream();
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser myparser = xmlFactoryObject.newPullParser();
                myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                myparser.setInput(stream, null);

                try {

                    // перебираем все теги
                    while (myparser.getEventType() != XmlPullParser.END_DOCUMENT) {
                        String tagname = myparser.getName();

                        switch (myparser.getEventType()) {
                            // если это начало документа
                            case XmlPullParser.START_TAG:
                                break;
                            case XmlPullParser.TEXT:
                                text = myparser.getText();
                                break;

                            case XmlPullParser.END_TAG:

                                if (tagname.equalsIgnoreCase("item") && flag == false) {
                                    flag = true;
                                } else if (tagname.equalsIgnoreCase("title") && flag == true) {
                                    Log.d(LOG_TAG, "END_TAG 1: имя = " + myparser.getName() + " text " + text);
                                    title = text;
                                    count = 0;
                                } else if (tagname.equalsIgnoreCase("link") && flag == true) {
                                    Log.d(LOG_TAG, "END_TAG 2: имя = " + myparser.getName() + " text " + text);
                                    link = text;
                                } else if (tagname.equalsIgnoreCase("description") && flag == true) {
                                    Log.d(LOG_TAG, "END_TAG 3: имя = " + myparser.getName() + " text " + text);
                                    description = text;
                                } else if (tagname.equalsIgnoreCase("pubDate") && flag == true) {
                                    Log.d(LOG_TAG, "END_TAG 4: имя = " + myparser.getName() + " text " + text);
                                    pubDate = text;
                                } else if (tagname.equalsIgnoreCase("enclosure") && flag == true) {

                                    text = myparser.getAttributeValue(0);
                                    if (text.substring(0, 4).equalsIgnoreCase("//cd")) {
                                        text = "http:" + text;
                                    }
                                    if (count == 0) {
                                        Log.d(LOG_TAG, "END_TAG 5: имя = " + myparser.getName() + " text " + text);
                                        path = text;
                                        RSS rss = new RSS(title, link, pubDate, description, path);
                                        Log.d(LOG_TAG, "END_TAG 6: имя = " + title + link + pubDate + description + path);
                                        db.addnews(rss);
                                        count = 1;
                                    }

                                }

                                break;

                            default:
                                break;
                        }
                        // переходим к следующему элементу

                        myparser.next();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            } catch (
                    Exception e
                    )

            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressBar.setProgress(0);
            Intent i = new Intent(getApplicationContext(), ViewNewsActivity.class);
            startActivity(i);
        }
    }
}