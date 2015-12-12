package com.mycompany.hisabproto2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.*;

/**
 * Created by Abhishek PC on 10-Dec-15.
 */
public class CalculatedScreen extends AppCompatActivity {
    private HomeAdapter homeAdapter;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculated_screen);
        homeAdapter=new HomeAdapter();
        ListView listView=(ListView)findViewById(R.id.calcLV);
        homeAdapter=new HomeAdapter();
        listView.setAdapter(homeAdapter);
        List<Vertex> vert=HomeScreen.g.performCalculate();
        int pos=HomeScreen.g.getPos();
        int i=0;
        while(i<pos){
            Vertex v=vert.get(i);
            List<Edge> ed=v.getOutGoing();
            for(Edge e:ed){
                String to=e.getTo().getName();
                int amount=e.getAmount();
                String from=v.getName();
                homeAdapter.addData(from,to,amount);
            }
            i++;
        }
    }
}
