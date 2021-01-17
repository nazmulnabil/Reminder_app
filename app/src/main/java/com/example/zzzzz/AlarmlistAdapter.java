package com.example.zzzzz;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmlistAdapter extends RecyclerView.Adapter<AlarmlistAdapter.AlarmViewHolder> {



    private Context context;
    private Cursor cursor;
    ArrayList<String> activities,list_of_times;

    public AlarmlistAdapter(Context context, ArrayList<String> activities, ArrayList<String>  list_of_times, Cursor cursor) {
        this.context = context;
        this.activities = activities;
        this.list_of_times = list_of_times;
        this.cursor=cursor;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        View v=layoutInflater.inflate(R.layout.sample_view,parent,false);




        return new AlarmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
      //if(cursor!=null)
        if (!cursor.moveToPosition(position)) {
            return;
        }
          long id=cursor.getLong(0);
        holder.activitylist_tv.setText(activities.get(position));
        holder.timelist_tv.setText(list_of_times.get(position));
         holder.itemView.setTag(id);

       

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }






    public  class AlarmViewHolder extends RecyclerView.ViewHolder {

        public TextView activitylist_tv,timelist_tv;



        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);

            activitylist_tv= itemView.findViewById(R.id.activity);
            timelist_tv=itemView.findViewById(R.id.Timesaved);

        }
    }

    public void swapCursor(Cursor newCursor) {
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }


 }
}

