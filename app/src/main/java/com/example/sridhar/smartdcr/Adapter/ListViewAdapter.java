package com.example.sridhar.smartdcr.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sridhar.smartdcr.R;

import java.util.ArrayList;
import java.util.HashMap;



public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    public ListViewAdapter(Activity activity, int textViewResourceId){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.listcolumn, null);

            txtFirst=(TextView) convertView.findViewById(R.id.uniqid);

            txtThird=(TextView) convertView.findViewById(R.id.status);


        }



        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get("Uniqid"));

        txtThird.setText(map.get("Status"));
//        txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }

}