package com.example.julia.traveleasily;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class budgetPage extends Activity {
    private ListView budgetList;
    private BudgetAdapter budgetAdapter;
    private List<Budget> budgets;
    private int selectedCount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);
        processViews();

        processController();
        //Intent intent = getIntent();
        budgets=new ArrayList<Budget>();


        budgets.add(new Budget(1,1,0,0,"Flight",100,"Transportation",3152016,"Spring Break"));
        //budgets.add(new Budget(2,1,0,0,"Flight",100,"r",3152016,"Spring Break"));
      // long tourId = intent.getLongExtra("TourId",1L);
        //System.out.println(tourId);


            budgetAdapter = new BudgetAdapter(this, R.layout.budget_item,budgets);
            budgetList = (ListView) findViewById(R.id.budgestListView);
            budgetList.setAdapter(budgetAdapter);





    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){

        if(resultCode==RESULT_OK) {

            Budget budget = (Budget) data.getExtras().getSerializable(
                    "com.example.julia.traveleasily.Budget");

            if(requestCode==0){
                budget.setId(budgets.size() + 1);
                budgets.add(budget);
                budgetAdapter.notifyDataSetChanged();
            }
            else if(requestCode==1){
                int position = data.getIntExtra("position",-1);
                if(position !=-1){

                    budgets.set(position,budget);
                    budgetAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void processController() {
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Budget budget = budgetAdapter.getItem(position);
                if(selectedCount>0){
                    processMenu(budget);
                    budgetAdapter.setIndex(position,budget);
                }else{
                    Intent intent = new Intent("EditBudget");
                    intent.putExtra("position",position);
                    intent.putExtra("com.example.julia.traveleasily.Budget",budget);
                    startActivityForResult(intent,1);
                }


            }
        };
        budgetList.setOnItemClickListener(itemListener);


        AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                Budget budget = budgetAdapter.getItem(position);
                processMenu(budget);
                budgetAdapter.setIndex(position,budget);
                return true;
            }
        };
        budgetList.setOnItemLongClickListener(itemLongClickListener);
    }

    private void processViews() {
        budgetList = (ListView) findViewById(R.id.budgestListView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addBudgetExpense(View view){
        Intent intent = new Intent("AddBudget");
        startActivityForResult(intent, 0);
    }

    private void processMenu(Budget budget){
        if(budget!=null){
            budget.setSelected(!budget.isSelected());
            if(budget.isSelected()){
                selectedCount++;
            }else{
                selectedCount--;
            }
        }

    }
}
