package com.adgvit.allan.retrofittest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adgvit.allan.retrofittest.data.model.Info;

import java.util.Collections;
import java.util.List;

/**
 * Created by Allan on 27-07-2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<Item> data = Collections.emptyList();

    public RecyclerAdapter(Context context, List<Item> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Get model based on position
        Info info = data.get(position);

        //Set items based on models and views
        TextView eventName = holder.txt1;
        eventName.setText(info.getName());
        TextView eventDesc = holder.txt2;
        eventDesc.setText(info.getDescription());
        TextView eventOrg = holder.txt3;
        eventOrg.setText(info.getOrganizer());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;
        public MyViewHolder(View view) {
            super(view);
            txt1 = (TextView) view.findViewById(R.id.eventName);
            txt2 = (TextView) view.findViewById(R.id.eventDesc);
            txt3 = (TextView) view.findViewById(R.id.eventOrg);
        }
    }
}
