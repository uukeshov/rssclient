package com.uukeshov.rssclient;

/**
 * Created by uukeshov on 3/19/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uukeshov on 3/5/2016.
 */
public class NewsListAdapter extends BaseAdapter implements
        OnClickListener {


    final String LOG_TAG = "NewsListAdapter";
    private Activity activity;
    private String[] data;
    private ArrayList<RSS> rData = new ArrayList<RSS>();
    private static LayoutInflater inflater = null;
    private Context mContext;


    public NewsListAdapter(Activity a, ArrayList<RSS> rD,
                           Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return rData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
    }

    /* Create a holder Class to contain inflated xml file elements */
    public static class ViewHolder {

        public TextView text;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public ImageView image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            /* Inflate tabitem.xml file for each row ( Defined below ) */
            vi = inflater.inflate(R.layout.list_view, null);

            // View Holder Object to contain tabitem.xml file elements /
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text1 = (TextView) vi.findViewById(R.id.text1);
            holder.text2 = (TextView) vi.findViewById(R.id.text2);
            holder.text3 = (TextView) vi.findViewById(R.id.text3);
            //s  holder.image = (ImageView) vi.findViewById(R.id.image);

            /* Set holder with LayoutInflater */
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        RSS item = rData.get(position);
        // Context context = parent.getContext();

        if (item.get_newstext().length() < 50) {
            holder.text.setText(item.get_newstext());
        } else if (item.get_newstext().length() > 50) {
            holder.text.setText(item.get_newstext().substring(0, 49) + " ...");
        }
        // holder.text1.setText(item.get_newspubDate());
        holder.text1.setText(item.get_newspubDate());

        /* Set Item Click Listner for LayoutInflater for each row ***/
        vi.setOnClickListener(new OnItemClickListener(position, item.get_newstitle(), Integer.toString(item.get_noteId()), item.get_noteTheme(), String.valueOf(item.get_latitude()), String.valueOf(item.get_longitude())));
        return vi;
    }

    /* Called when Item click in ListView ***/
    private class OnItemClickListener implements OnClickListener {
        private int mPosition;
        private String note_t;
        private String note_i;
        private String note_th;
        private String note_lat;
        private String note_lon;

        OnItemClickListener(int position, String note_text, String note_id, String note_theme, String latitude, String longitude) {
            mPosition = position;
            note_t = note_text;
            note_i = note_id;
            note_th = note_theme;
            note_lat = latitude;
            note_lon = longitude;
        }

        @Override
        public void onClick(View arg0) {

            Intent myIntent = new Intent(mContext, ViewNewsActivity.class);
            //добавитьпередачу текста
            myIntent.putExtra("mPosition", String.valueOf(mPosition));
            myIntent.putExtra("note_text", note_t);
            myIntent.putExtra("note_id", String.valueOf(note_i));
            myIntent.putExtra("note_theme", note_th);
            myIntent.putExtra("latitude", String.valueOf(note_lat));
            myIntent.putExtra("longitude", String.valueOf(note_lon));
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(myIntent);

        }
    }


}
