package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class budgetPage extends Activity {
    private ListView budgetList;
    private BudgetAdapter budgetAdapter;
    private List<Budget> budgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);
        processViews();

        //processController();

        budgets=new ArrayList<Budget>();


        budgets.add(new Budget(1,0,0,"Flight",100,"Transportation",3152016,"Spring Break"));

        budgetAdapter = new BudgetAdapter(this, R.layout.budget_item,budgets);
        budgetList = (ListView) findViewById(R.id.budgestListView);
        budgetList.setAdapter(budgetAdapter);


    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_OK) {
            Budget budget = (Budget) data.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Budget");
            budget.setId(budgets.size()+1);
            budgets.add(budget);
            budgetAdapter.notifyDataSetChanged();
        }
    }

    private void processController() {
    }

    private void processViews() {
        budgetList = (ListView) findViewById(R.id.budgestListView);

    }

    public void addBudgetExpense(View view){
        Intent intent = new Intent(this,AddBudget.class);
        startActivityForResult(intent, 0);
    }
}
