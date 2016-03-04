package com.example.julia.traveleasily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Julia on 2016/3/3.
 */
public class tourAdapter extends ArrayAdapter<Itinerary> {
    private int resource;
    private List<Itinerary> itineraries;

    public tourAdapter(Context context, int resource,List<Itinerary> itineraries){
        super(context,resource,itineraries);
        this.resource=resource;
        this.itineraries= itineraries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout tourView;

        final Itinerary itinerary = getItem(position);
        if (convertView == null) {
            // 建立項目畫面元件
            tourView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, tourView, true);
        }
        else {
            tourView = (LinearLayout) convertView;
        }

        ImageView selectedItem = (ImageView) tourView.findViewById(R.id.selectedItem);
        TextView destTitleView = (TextView) tourView.findViewById(R.id.txv_dest);
        destTitleView.setText(itinerary.getDestText());

        selectedItem.setVisibility(itinerary.isSelected()? View.VISIBLE : View.INVISIBLE);
        return tourView;

    }

    public void setIndex(int index, Itinerary itinerary){
        if(index>-0 && index< itineraries.size() );
        itineraries.set(index,itinerary);
        notifyDataSetChanged();
    }

    public Itinerary get(int index){
        return itineraries.get(index);
    }

}
