package com.example.julia.traveleasily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Julia on 2016/3/5.
 */
public class BudgetAdapter extends ArrayAdapter<Budget> {
    private int resource;
    private List<Budget> budgets;

    public BudgetAdapter(Context context, int resource,List<Budget> budgets) {
        super(context, resource, budgets);
        this.resource = resource;
        this.budgets = budgets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout budgetView;
        final Budget budget = getItem(position);
        if (convertView == null) {
            // 建立項目畫面元件
            budgetView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, budgetView, true);
        }
        else {
            budgetView = (LinearLayout) convertView;
        }

        TextView categoryView = (TextView) budgetView.findViewById(R.id.txv_budgetCategory);
        categoryView.setText(budget.getCategory());
        TextView amountView = (TextView) budgetView.findViewById(R.id.txv_budgetAmount);
        amountView.setText(String.valueOf(budget.getAmount()));

        return budgetView;

    }


    public void setIndex(int index, Budget budget){
        if(index>=0 && index< budgets.size() );
        budgets.set(index,budget);
        notifyDataSetChanged();
    }

    public Budget get(int index){
        return budgets.get(index);
    }
}
