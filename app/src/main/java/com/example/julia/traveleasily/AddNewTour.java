package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddNewTour extends AppCompatActivity {

    private EditText edtDepart, edtDest, edtAirBus, edtDateFrom, edtDateTo, edtNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tour);
        processViews();
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
            String dateFromText = edtDateFrom.getText().toString();
            String dateToText = edtDateTo.getText().toString();
            String noteText = edtNote.getText().toString();

            Intent result = getIntent();
            result.putExtra("departText",departText);
            result.putExtra("destText",destText);
            result.putExtra("airBusText",airBusText);
            result.putExtra("dateFromText",dateFromText);
            result.putExtra("dateToText",dateToText);
            result.putExtra("noteText",noteText);

            setResult(Activity.RESULT_OK,result);
        }

        finish();
    }


}
