package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class IndexPage extends Activity {
    private List<Itinerary> itineraries;
    private tourAdapter tourAdapter;
    private EditText edtTourName;
    private Itinerary itinerary;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);
        processViews();
        itineraries=new ArrayList<Itinerary>();
        tourAdapter = new tourAdapter(this, R.layout.singleitem,itineraries);
        Intent intent=getIntent();
        itinerary=(Itinerary) intent.getExtras().getSerializable("com.example.julia.traveleasily.Itinerary");



    }

    private void processViews() {
        edtTourName=(EditText) findViewById(R.id.edtTourName);

        edtTourName.setText("test");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode==RESULT_OK){
           itinerary = (Itinerary) data.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Itinerary");

            if(requestCode==0){

                itinerary.setId(itineraries.size() + 1);
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

    public void viewItinerary(View view){
        Intent intent = new Intent("EditItem");

        intent.putExtra("com.example.julia.traveleasily.Itinerary", itinerary);
        //System.out.println(itinerary.getDestText());

        startActivityForResult(intent, 1);


    }
    public void goToBudget(View view){
        Intent intent = new Intent(this, budgetPage.class);
        //intent.putExtra("TourId",itinerary.getId());
        startActivity(intent);
    }
}
