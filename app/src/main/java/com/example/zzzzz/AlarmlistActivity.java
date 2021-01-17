package com.example.zzzzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_ACTIVITY;
import static com.example.zzzzz.AlarmContract.AlarmEntry.COL_TIME;
import static com.example.zzzzz.AlarmContract.AlarmEntry.TABLE_NAME;

public class AlarmlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addbutton;
    ArrayList<String> activitivitylist,timelist;
   AlarmDBHelper alarmDBHelper;
    AlarmlistAdapter alarmAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);
        recyclerView=findViewById(R.id.recyclerview);
        addbutton=findViewById(R.id.addbutton);

        alarmDBHelper=new AlarmDBHelper(this);

        activitivitylist=new ArrayList<>();
        timelist=new ArrayList<>();
        getalldata();
        Cursor cur_sor=displaydata();
        alarmAdapter=new AlarmlistAdapter(AlarmlistActivity.this,activitivitylist,timelist,cur_sor);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(alarmAdapter);
     // alarmAdapter.notifyDataSetChanged();

       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
             deleteitem((long) viewHolder.itemView.getTag());
               int position = viewHolder.getAdapterPosition();
               activitivitylist.remove(position);
               alarmAdapter.notifyDataSetChanged();


           }
       }).attachToRecyclerView(recyclerView);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AlarmlistActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public Cursor displaydata(){

        Cursor cursor=null;

        SQLiteDatabase sqLiteDatabase=alarmDBHelper.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery ("SELECT *  FROM "+TABLE_NAME,null);
        return cursor;


    }
    public Cursor getalldata() {


       Cursor cur=displaydata();

        if(cur.getCount()==0){

            Toast.makeText(this,"No reminder exists",Toast.LENGTH_SHORT).show();
        }  else {
            while (cur.moveToNext()){

                activitivitylist.add(cur.getString(1));
                timelist.add(cur.getString(2));



            }



        }

      return cur;

    }





    public void deleteitem(long id){
        SQLiteDatabase sqLiteDatabase=alarmDBHelper.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, AlarmContract.AlarmEntry._ID + "=" + id, null);
       //alarmAdapter.swapCursor(displaydata());
//alarmAdapter.notifyDataSetChanged();




    }


}


