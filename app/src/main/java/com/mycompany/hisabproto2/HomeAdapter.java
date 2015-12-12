package com.mycompany.hisabproto2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.*;
/**
 * Created by Abhishek PC on 10-Dec-15.
 */
public class HomeAdapter extends BaseAdapter {
    private ArrayList<DataRecord> data=new ArrayList<>();
    public ArrayList<DataRecord> getData(){return data;}
    public boolean addData(String from,String to,int amount){
        data.add(new DataRecord(from,to,amount));
        return true;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            convertView=inflater.inflate(R.layout.hisab_list,parent,false);
        }
        DataRecord d=data.get(position);
        TextView fV=(TextView)convertView.findViewById(R.id.fromHL);
        fV.setText(d.getFrom());
        TextView tV=(TextView)convertView.findViewById(R.id.toHL);
        tV.setText(d.getTo());
        TextView aV=(TextView)convertView.findViewById(R.id.amountHL);
        aV.setText(Integer.toString(d.getAmount()));
        return convertView;
    }
}
