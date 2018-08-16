package com.example.user.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private  DBHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper=new DBHelper(context.getApplicationContext());
    }
    public DatabaseAdapter open(){
        database=dbHelper.getWritableDatabase();
        return this;

    }
    public void close(){
        dbHelper.close();
    }
    private Cursor getAllEntries(){
        String[] colums=new String[]{dbHelper.FN_ID, dbHelper.FN_FAMILY, dbHelper.FN_NAME, dbHelper.FN_PHONE,};
        return database.query(dbHelper.DT_NAME, colums, null,null,null,null,null);
    }
     public List<AppStudent> getStudent(){
        ArrayList<AppStudent> students=new ArrayList<>();
        Cursor cursor=getAllEntries();
        if(cursor.moveToFirst()){
            do{
                Long id=
                        Long.valueOf(cursor.getInt(cursor.getColumnIndex(DBHelper.FN_ID)));
                String family=
                        cursor.getString(cursor.getColumnIndex(DBHelper.FN_FAMILY));
                String name=
                        cursor.getString(cursor.getColumnIndex(DBHelper.FN_NAME));
                Integer phone=
                        Integer.valueOf(cursor.getInt(cursor.getColumnIndex(DBHelper.FN_PHONE)));
                students.add(new AppStudent(id,family, name, phone));
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        return students;
    }
}
