package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddBudget extends AppCompatActivity {
    private EditText edtItem,edtAmount,edtDate,edtNote;
    private Budget budget;
    private Spinner budgetCatSpinner;
    ArrayAdapter<String> categoryList;
    private String categories[]={"Transportation","Occupancy","Tickets"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        processViews();
        categoryList = new ArrayAdapter<String>(AddBudget.this,android.R.layout.simple_spinner_item,categories);
        budgetCatSpinner.setAdapter(categoryList);
        budgetCatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String category = categories[position];
                budget.setCategory(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Intent intent = getIntent();
        String action = intent.getAction();

        if(action.equals("EditBudget")){
            budget=(Budget) intent.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Budget");
            System.out.println(budget.getCategory());

            edtItem.setText(budget.getItem());
            edtAmount.setText(Integer.toString(budget.getAmount()));
            budgetCatSpinner.setAdapter(categoryList);
            edtDate.setText(String.valueOf(budget.getDate()));
            edtNote.setText(budget.getNote());

        }else{
            budget = new Budget();
        }




    }





    private void processViews() {
        edtItem = (EditText) findViewById(R.id.edtBudgetItem);
        edtAmount = (EditText) findViewById(R.id.edtBudgetAmount);
        budgetCatSpinner = (Spinner) findViewById(R.id.budgetCatSpinner);
        edtDate=(EditText) findViewById(R.id.edtBudgetDate);
        edtNote=(EditText) findViewById(R.id.edtBudgetNote);
    }

    public void onSubmitBudget(View view){
        if(view.getId()==R.id.btnBudgetDone){

            String item = edtItem.getText().toString();
            int amount = Integer.parseInt(edtAmount.getText().toString());

            long date = Long.parseLong(edtDate.getText().toString());
            String note = edtNote.getText().toString();

            budget.setItem(item);
            budget.setAmount(amount);

            budget.setDate(date);
            budget.setNote(note);

            Intent result = getIntent();
            result.putExtra("com.example.julia.traveleasily.Budget",budget);
            setResult(Activity.RESULT_OK,result);
        }
        finish();
    }


}
