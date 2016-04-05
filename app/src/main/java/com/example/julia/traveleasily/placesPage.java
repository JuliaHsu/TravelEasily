package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class placesPage extends Activity {
    private ListView placesList;
    private placeAdapter placeAdapter;
    private List<Place> places;
    private Place place;
    private int selectedCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_page);
        processViews();
        processController();

        Intent intent = getIntent();
        place = (Place) intent.getExtras().getSerializable("com.example.julia.traveleasily.Place");
        places = new ArrayList<Place>();
        placeAdapter= new placeAdapter(this,R.layout.place_item,places);
        placesList.setAdapter(placeAdapter);

    }

    private void processViews(){
        placesList= (ListView) findViewById(R.id.placesList);
    }

    private void processController(){
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Place place = placeAdapter.getItem(position);
                if(selectedCount>0){
                    processMenu(place);
                    placeAdapter.setIndex(position,place);
                }else{
                    Intent intent = new Intent("EditPlace");
                    intent.putExtra("position",position);
                    intent.putExtra("com.example.julia.traveleasily.Place",place);
                    startActivityForResult(intent,1);
                }


            }
        };
        placesList.setOnItemClickListener(itemListener);
    }

    private void processMenu(Place place){
        if(place!=null){
            place.setSelected(!place.isSelected());
            if(place.isSelected()){
                selectedCount++;
            }else{
                selectedCount--;
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){

        if(resultCode==RESULT_OK) {

            Place place = (Place) data.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Place");


            if(requestCode==0){
                place.setId(places.size() + 1);
                places.add(place);
                placeAdapter.notifyDataSetChanged();

            }
            else if(requestCode==1){
                int position = data.getIntExtra("position",-1);
                if(position !=-1){

                    places.set(position, place);
                    placeAdapter.notifyDataSetChanged();


                }
            }
        }
    }

    public void addNewPlace(View view){
        Intent intent = new Intent("AddPlace");
        startActivityForResult(intent, 0);
    }


}
