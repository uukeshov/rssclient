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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            //s  holder.image = (ImageView) vi.findViewById(R.id.image);

            /* Set holder with LayoutInflater */
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
        RSS item = rData.get(position);
        // Context context = parent.getContext();
        Log.d(LOG_TAG, "--- onCreate database 1 ---" + item.get_newsTitle());
        if (item.get_newsTitle().length() < 50) {
            holder.text.setText(item.get_newsTitle());
        } else if (item.get_newsTitle().length() > 50) {
            holder.text.setText(item.get_newsTitle().substring(0, 49) + " ...");
        }

        holder.text1.setText(item.get_newsPubDate());
        holder.text2.setText(item.get_newsLink());

     /* Set Item Click Listner for LayoutInflater for each row ***/
        vi.setOnClickListener(new OnItemClickListener(position, item.get_newsTitle(), item.get_newsLink(), item.get_newsPubDate(), item.get_newsLinktoImage(), item.get_newsDescr()));
        return vi;
    }

    /* Called when Item click in ListView ***/
    private class OnItemClickListener implements OnClickListener {
        private int mPosition;
        private String _newstitle;
        private String _newslink;
        private String _newspubDate;
        private String _newsenclosure;
        private String _newstext;

        OnItemClickListener(int position, String newstitle, String newslink, String newspubDate, String newsenclosure, String newstext) {
            mPosition = position;
            _newstitle = newstitle;
            _newslink = newslink;
            _newspubDate = newspubDate;
            _newsenclosure = newsenclosure;
            _newstext = newstext;
        }

        @Override
        public void onClick(View arg0) {

            Intent myIntent = new Intent(mContext, ViewNewsActivity.class);
            //добавитьпередачу текста
            myIntent.putExtra("mPosition", String.valueOf(mPosition));
            myIntent.putExtra("newstitle", _newstitle);
            myIntent.putExtra("newslink", _newslink);
            myIntent.putExtra("newspubDate", _newspubDate);
            myIntent.putExtra("newsenclosure", _newsenclosure);
            myIntent.putExtra("newstext", _newstext);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(myIntent);

        }
    }
}
