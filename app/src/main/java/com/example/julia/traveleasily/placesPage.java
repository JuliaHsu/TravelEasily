package com.example.julia.traveleasily;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class placesPage extends Activity {
    private ListView placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_page);
    }
}
