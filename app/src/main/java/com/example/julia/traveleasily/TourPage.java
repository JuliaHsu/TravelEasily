package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TourPage extends Activity {
    private ListView tourList;
    private ArrayList<String> tourData = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_page);

        processViews();
        processController();

        tourData.add("Boston");
        tourData.add("NYC");
        tourData.add("Seattle");

        int layoutId= android.R.layout.simple_list_item_1;
        adapter =new ArrayAdapter<String>(this,layoutId,tourData);
        tourList = (ListView) findViewById(R.id.tourList);
        tourList.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            String destText = data.getStringExtra("destText");

            if(requestCode==0){
                this.tourData.add(destText);
                adapter.notifyDataSetChanged();
            }

            else if(requestCode==1){
                int position = data.getIntExtra("position",-1);
                if(position !=-1){
                    this.tourData.set(position,destText);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }



    private void processViews() {
        tourList = (ListView) findViewById(R.id.tourList);
    }

    private void processController() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent("EditItem");
                intent.putExtra("position",position);
                intent.putExtra("destText", tourData.get(position));
                startActivityForResult(intent,1);

            }
        };
        tourList.setOnItemClickListener(itemListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addNewTours(View view){
        Intent intent = new Intent("EditItem");
        startActivityForResult(intent, 0);
    }
}
