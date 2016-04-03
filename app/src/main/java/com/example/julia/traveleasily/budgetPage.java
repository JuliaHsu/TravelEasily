package com.example.julia.traveleasily;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class budgetPage extends Activity {
    private ListView budgetList;
    private BudgetAdapter budgetAdapter;
    private List<Budget> budgets;
    private int selectedCount =0;
    private double expense=0;
    private  Budget budget;
    private TextView txvExpense;

     

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_page);
        processViews();

        processController();
        Intent intent = getIntent();
        budget = (Budget) intent.getExtras().getSerializable(
                "com.example.julia.traveleasily.Budget");

        budgets=new ArrayList<Budget>();


        //budgets.add(new Budget(1,1,0,0,"Flight",100,"Transportation",3152016,"Spring Break"));
        //budgets.add(new Budget(2,1,0,0,"Flight",100,"r",3152016,"Spring Break"));
        long tourId = intent.getLongExtra("TourId",1L);
        //System.out.println(tourId);


            budgetAdapter = new BudgetAdapter(this, R.layout.budget_item,budgets);
            budgetList = (ListView) findViewById(R.id.budgestListView);
            budgetList.setAdapter(budgetAdapter);
            setExpense();





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

                    budgets.set(position, budget);
                    budgetAdapter.notifyDataSetChanged();


                }
            }
            expense = expense +budget.getAmount();
            setExpense();
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
                    expense=expense-budget.getAmount();
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
        txvExpense = (TextView) findViewById(R.id.txvExpenseRes);


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

    public void setExpense(){
        txvExpense.setText(Double.toString(expense));

    }

    public void budgetDOne(View view){
        if(view.getId()==R.id.btnBudgetDone){


           finish();
        }
    }

    public void deleteBudget(View view){
        if(selectedCount!=0){
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            String message = getString(R.string.deleteBudget);
            d.setTitle(R.string.delete).setMessage(String.format(message, selectedCount));
            d.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // delete the tours you selected
                    //Get the index from tourAdapter
                    int index = budgetAdapter.getCount() - 1;
                    //keep tracking all of the tours is selecting or not
                    while (index > -1) {
                        Budget budget = budgetAdapter.get(index);


                        if (budget.isSelected()) {
                            expense = expense - budget.getAmount();
                            System.out.println(expense);

                            budgetAdapter.remove(budget);
                            setExpense();
                        }

                        index--;
                    }

                    // inform the data is changed
                    budgetAdapter.notifyDataSetChanged();
                    selectedCount = 0;
                    processMenu(null);
                }

            });

            d.setNegativeButton(android.R.string.no, null);
            d.show();

        }
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
