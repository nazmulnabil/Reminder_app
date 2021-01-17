package com.example.zzzzz;

import android.provider.BaseColumns;

public class AlarmContract {
    private  AlarmContract(){

    }

    public static final class AlarmEntry implements BaseColumns {


        public static final String TABLE_NAME="AlarmTable";

        public static final String COL_ACTIVITY="colactivity";
        public static final String COL_TIME="Time";



    }








}
