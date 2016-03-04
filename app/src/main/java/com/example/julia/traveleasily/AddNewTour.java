package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddNewTour extends AppCompatActivity {

    private EditText edtDepart, edtDest, edtAirBus, edtDateFrom, edtDateTo, edtNote;
    private Itinerary itinerary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tour);
        processViews();

        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals("EditItem")){
           itinerary=(Itinerary) intent.getExtras().getSerializable(
                   "com.example.julia.traveleasily.Itinerary");
            edtDepart.setText(itinerary.getDepartText());
            edtDest.setText(itinerary.getDestText());
            edtAirBus.setText(itinerary.getAirBusText());
            edtDateFrom.setText(String.valueOf(itinerary.getDateFrom()));
            edtDateTo.setText((String.valueOf(itinerary.getDateTo())));
            edtNote.setText(itinerary.getNote());

        }else{
            itinerary = new Itinerary();
        }
    }



    private void processViews() {
        edtDepart = (EditText) findViewById(R.id.edtDepart);
        edtDest = (EditText) findViewById(R.id.edtDest);
        edtAirBus = (EditText) findViewById(R.id.edtAirBus);
        edtDateFrom = (EditText) findViewById(R.id.edtDateFrom);
        edtDateTo = (EditText) findViewById(R.id.edtDateTo);
        edtNote = (EditText) findViewById(R.id.edtNote);
    }

    public void onSubmit(View view){
        if(view.getId()==R.id.btnDone){
            String departText = edtDepart.getText().toString();
            String destText = edtDest.getText().toString();
            String airBusText = edtAirBus.getText().toString();
            long dateFromText = Long.parseLong(edtDateFrom.getText().toString()) ;
            long dateToText = Long.parseLong(edtDateTo.getText().toString());
            String noteText = edtNote.getText().toString();

            itinerary.setDepartText(departText);
            itinerary.setDestText(destText);
            itinerary.setAirBusText(airBusText);
            itinerary.setDateFrom(dateFromText);
            itinerary.setDateFrom(dateFromText);
            itinerary.setDateTo(dateToText);
            itinerary.setNote(noteText);

            Intent result = getIntent();
            result.putExtra("com.example.julia.traveleasily.Itinerary",itinerary);

            setResult(Activity.RESULT_OK,result);
        }

        finish();
    }


}
