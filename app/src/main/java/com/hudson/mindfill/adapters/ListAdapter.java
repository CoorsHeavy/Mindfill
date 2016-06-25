package com.hudson.mindfill.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hudson.mindfill.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    Context context;
    ArrayList<Integer> prgmNameList;
    private static LayoutInflater inflater=null;
    public ListAdapter(Activity mainActivity, ArrayList<Integer> prgmNameList) {
        // TODO Auto-generated constructor stub
        this.prgmNameList = prgmNameList;
        context = mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return prgmNameList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        LinearLayout ll;
        ImageView img;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("item.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        String name = "";
        JSONObject obj = null;
        try {
            obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("array");
            JSONObject item = m_jArry.getJSONObject(position);
            name = item.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.listitem, null);
        holder.ll = (LinearLayout) rowView.findViewById(R.id.laid);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        //holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        //holder.ll.setBackgroundColor(helper.getCategoryColor());
        holder.tv.setText(name);
        //holder.img.setImageResource(imageId[position]);

        return rowView;
    }

}