package com.example.julia.traveleasily;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class TourPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_page);
    }
    public void clickNew(View view){
        finish();
    }
}
