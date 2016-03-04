package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TourPage extends Activity {
    private ListView tourList;
    //private ArrayList<String> tourData = new ArrayList<>();
    // private ArrayAdapter<String> adapter;
    private tourAdapter tourAdapter;
    private List<Itinerary> itineraries;
    private int selectedCount =0;
    private Button btnAdd, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_page);

        processViews();
        processController();

        itineraries=new ArrayList<Itinerary>();

        itineraries.add(new Itinerary(1,3112016,3192016,"OKC","Boston","American Airline","Spring Break"));

        tourAdapter = new tourAdapter(this, R.layout.singleitem,itineraries);
        tourList = (ListView) findViewById(R.id.tourList);
        tourList.setAdapter(tourAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode==RESULT_OK){
            Itinerary itinerary = (Itinerary) data.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Itinerary");

            if(requestCode==0){
                itinerary.setId(itineraries.size()+1);
                itineraries.add(itinerary);
                tourAdapter.notifyDataSetChanged();
            }

            else if(requestCode==1){
                int position = data.getIntExtra("position",-1);
                if(position !=-1){
                   itineraries.set(position,itinerary);
                    tourAdapter.notifyDataSetChanged();
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
                Itinerary itinerary = tourAdapter.getItem(position);
                if(selectedCount>0){
                    processMenu(itinerary);
                    tourAdapter.setIndex(position,itinerary);
                }else{
                    Intent intent = new Intent("EditItem");
                    intent.putExtra("position",position);
                    intent.putExtra("com.example.julia.traveleasily.Itinerary",itinerary);
                    startActivityForResult(intent,1);
                }


            }
        };
        tourList.setOnItemClickListener(itemListener);


        AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Itinerary itinerary = tourAdapter.getItem(position);
                processMenu(itinerary);
                tourAdapter.setIndex(position,itinerary);
                return true;
            }
        };
        tourList.setOnItemLongClickListener(itemLongClickListener);
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
        Intent intent = new Intent("AddItem");
        startActivityForResult(intent, 0);
    }
    private void processMenu(Itinerary itinerary){
        if(itinerary!=null){
            itinerary.setSelected(!itinerary.isSeleced());
            if(itinerary.isSeleced()){
                selectedCount++;
            }else{
                selectedCount--;
            }
        }

    }
    
}
