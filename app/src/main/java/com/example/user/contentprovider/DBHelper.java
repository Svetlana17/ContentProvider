package com.example.user.contentprovider;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "students";
    private Context context;

   public static String DT_NAME = "students";
    public static String FN_ID = "id";
    public static String FN_FAMILY = "family";
    public static String FN_NAME = "name";
    public static String FN_PHONE = "phone";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table "+ DT_NAME + " ("
                +FN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        +FN_FAMILY+ " TEXT,"
                +FN_NAME+ " TEXT,"
                +FN_PHONE + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS  students");
    onCreate(sqLiteDatabase);
    }

    public void addStudent(AppStudent student){
        SQLiteDatabase db=getWritableDatabase();
        assert  db!=null;
        ContentValues v=new ContentValues();
        v.put(FN_FAMILY,student.getFamily());
        v.put(FN_NAME,student.getName());
        v.put(FN_PHONE,student.getPhone());
        db.insert(DT_NAME, null,v);
    }

    public long getSize(){
        SQLiteDatabase db=getReadableDatabase();
        long cnt= DatabaseUtils.queryNumEntries(db, DT_NAME);
        return  cnt;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=getWritableDatabase();
        return db.query(DB_NAME, null, null, null, null, null, null);
    }
}
