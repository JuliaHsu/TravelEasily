package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TourPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_page);
    }
    public void addNewTours(View view){
        Intent intent = new Intent(this, AddNewTour.class);
        startActivity(intent);
    }
}
