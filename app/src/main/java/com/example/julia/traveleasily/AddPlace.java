package com.example.julia.traveleasily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPlace extends AppCompatActivity {
    private EditText edtPlaceName, edtAddress, edtPhoneNumber,edtWebsite, edtNote, edtPhoto;
    private String fileName;
    private ImageView picture;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        processViews();
    }

    private void processViews(){
        edtPlaceName = (EditText) findViewById(R.id.edtPlaceName);
        edtAddress=(EditText) findViewById(R.id.edtPlaceAdd);
        edtPhoneNumber=(EditText) findViewById(R.id.edtPlacePhone);
        edtWebsite=(EditText) findViewById(R.id.edtPlaceWeb);
        edtNote = (EditText) findViewById(R.id.edtPlaceNote);
        edtPhoto=(EditText) findViewById(R.id.edtPlacePhoto);


    }
}
