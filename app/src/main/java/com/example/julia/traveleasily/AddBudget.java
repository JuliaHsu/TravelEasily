package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddBudget extends AppCompatActivity {
    private EditText edtItem,edtAmount,edtCategory,edtDate,edtNote;
    private Budget budget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
        processViews();
        Intent intent = getIntent();
        budget = new Budget();
    }

    private void processViews() {
        edtItem = (EditText) findViewById(R.id.edtBudgetItem);
        edtAmount = (EditText) findViewById(R.id.edtBudgetAmount);
        edtCategory=(EditText) findViewById(R.id.edtBudgetCategory);
        edtDate=(EditText) findViewById(R.id.edtBudgetDate);
        edtNote=(EditText) findViewById(R.id.edtBudgetNote);
    }

    public void onSubmitBudget(View view){
        if(view.getId()==R.id.btnBudgetDone){
            String item = edtItem.getText().toString();
            int amount = Integer.parseInt(edtAmount.getText().toString());
            String category = edtCategory.getText().toString();
            long date = Long.parseLong(edtDate.getText().toString());
            String note = edtNote.getText().toString();

            budget.setItem(item);
            budget.setAmount(amount);
            budget.setCategory(category);
            budget.setDate(date);
            budget.setNote(note);

            Intent result = getIntent();
            result.putExtra("com.example.julia.traveleasily.Budget",budget);
            setResult(Activity.RESULT_OK,result);
        }
        finish();
    }
}
