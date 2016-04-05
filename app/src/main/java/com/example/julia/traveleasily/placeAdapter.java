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
 * Created by Julia on 2016/3/31.
 */
public class placeAdapter extends ArrayAdapter<Place> {
    private int resource;
    private List<Place> placesList;

    public placeAdapter(Context context,int resource,List<Place> placesList){
        super(context,resource,placesList);
        this.resource = resource;
        this.placesList=placesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout placeView;
        final Place place = getItem(position);
        if (convertView == null) {
            // 建立項目畫面元件
            placeView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater)
                    getContext().getSystemService(inflater);
            li.inflate(resource, placeView, true);
        } else {
            placeView = (LinearLayout) convertView;
        }
        ImageView placePhoto=(ImageView) placeView.findViewById(R.id.imgPlace);
        TextView placeName=(TextView) placeView.findViewById(R.id.txv_placesName);
        return placeView;
    }

    public void setIndex(int index, Place place){
        if(index>=0 && index< placesList.size() );
        placesList.set(index,place);
        notifyDataSetChanged();
    }
    public Place get(int index){
        return placesList.get(index);
    }


}
