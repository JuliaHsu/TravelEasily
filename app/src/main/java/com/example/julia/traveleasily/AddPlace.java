package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPlace extends AppCompatActivity {
    private EditText edtPlaceName, edtAddress, edtPhoneNumber,edtWebsite, edtNote, edtPhoto;
    private String fileName;
    private ImageView picture;
    private Place place;
    private int position;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        processViews();
        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals("EditPlace")){
            place=(Place) intent.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Place");
            position = intent.getIntExtra("position",-1);
            edtPlaceName.setText(place.getPlaceName());
            edtAddress.setText(place.getPlaceAddress());
            edtPhoneNumber.setText(place.getPhoneNumber());
            edtWebsite.setText(place.getWebsite());
            edtNote.setText(place.getNote());

        }
        else{
            place = new Place();
        }
    }

    private void processViews(){
        edtPlaceName = (EditText) findViewById(R.id.edtPlaceName);
        edtAddress=(EditText) findViewById(R.id.edtPlaceAdd);
        edtPhoneNumber=(EditText) findViewById(R.id.edtPlacePhone);
        edtWebsite=(EditText) findViewById(R.id.edtPlaceWeb);
        edtNote = (EditText) findViewById(R.id.edtPlaceNote);
        edtPhoto=(EditText) findViewById(R.id.edtPlacePhoto);


    }

    public void onSubmitPlace(View view){
        if(view.getId()==R.id.btnPlaceDone){
            String placeName= edtPlaceName.getText().toString();
            String placeAddress=edtAddress.getText().toString();
            String placePhoneNumber=edtPhoneNumber.getText().toString();
            String placeWebsite=edtWebsite.getText().toString();
            String placeNote=edtNote.getText().toString();
            place.setPlaceName(placeName);
            place.setPlaceAddress(placeAddress);
            place.setPhoneNumber(placePhoneNumber);
            place.setWebsite(placeWebsite);
            place.setNote(placeNote);

            Intent result = getIntent();
            result.putExtra("com.example.julia.traveleasily.Place",place);
            result.putExtra("position",position);
            setResult(Activity.RESULT_OK, result);
        }
        finish();
    }
}
