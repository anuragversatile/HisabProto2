package com.mycompany.hisabproto2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;
//TODO:remove static occurrences and pass objects to other activities
public class HomeScreen extends AppCompatActivity {
    public static HomeAdapter homeAdapter;
    public static Graph g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ListView listView=(ListView)findViewById(R.id.homeLV);
        homeAdapter=new HomeAdapter();
        listView.setAdapter(homeAdapter);
        g=new Graph("GRAPH");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void performCalculate(View view){
            //TODO: prohibit going to other activity if there is no valid data in the data ArrayList of HomeAdapter
            ArrayList<DataRecord> rec=homeAdapter.getData();
            for(DataRecord d:rec){
                String from=d.getFrom();
                String to=d.getTo();
                int amount=d.getAmount();
                g.addToGraph(from,to,amount);
            }
            Intent intent = new Intent(this, CalculatedScreen.class);
            startActivity(intent);

    }
    public void onAdd(View view){
        EditText f=(EditText)findViewById(R.id.fromInp);
        String fT=f.getText().toString();
        EditText t=(EditText)findViewById(R.id.toInp);
        String tT=t.getText().toString();
        EditText a=(EditText)findViewById(R.id.amountInp);
        String aT=a.getText().toString();
        if(fT==""||tT==""||aT==""){
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            f.setText("");
            t.setText("");
            a.setText("");
        }
        try {
            int am = Integer.parseInt(aT);
            homeAdapter.addData(fT, tT, am);
            f.setText("");
            t.setText("");
            a.setText("");
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(this,"Invalid data", Toast.LENGTH_SHORT).show();
            f.setText("");
            t.setText("");
            a.setText("");
        }
    }
}
