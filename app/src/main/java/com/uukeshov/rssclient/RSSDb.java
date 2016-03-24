package com.uukeshov.rssclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uukeshov on 3/19/2016.
 */
public class RSSDb extends SQLiteOpenHelper {

    private static final String LOG_TAG = "RSSDbLog";

    public RSSDb(Context context) {
        super(context, "RSSDB", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table newstable (id integer primary key autoincrement," +
                        "newstitle text," +
                        "newslink text," +
                        "newspubDate text," +
                        "newsenclosure text, " +
                        "newsdescr text)"
        );
    }

    public void addnews(RSS rss) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("newstitle", rss.get_newsTitle());
        cv.put("newslink", rss.get_newsLink());
        cv.put("newspubDate", rss.get_newsPubDate());
        cv.put("newsenclosure", rss.get_newsLinktoImage());
        cv.put("newsdescr", rss.get_newsDescr());
        db.insert("newstable", null, cv);
        db.close();
    }

    public List<RSS> getAllNews() {

        List<RSS> rssList = new ArrayList<RSS>();
        String selectQuery = "SELECT * FROM newstable ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                RSS rss = new RSS();
                rss.set_newsId(Integer.valueOf(cursor.getString(0)));
                rss.set_newsTitle(cursor.getString(1));
                rss.set_newsLink(cursor.getString(2));
                rss.set_newsPubDate(cursor.getString(3));
                rss.set_newsDescr(cursor.getString(4));
                rss.set_newsLinktoImage(cursor.getString(5));
                rssList.add(rss);
            } while (cursor.moveToNext());
        }
        db.close();
        return rssList;
    }

    public void deleteAllNews() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + "newstable");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
