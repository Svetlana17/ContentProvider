package com.example.user.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class StudentProvider extends ContentProvider {

    final  String LOG_TAG="Students";
    //BD
    static  final String DB_NAME="student";
    static  final int DB_Version=1;
    //Table

    static final String STUDENT_TABLE="students";
    //поля
    static final String STUDENT_ID="_id";
    static final String STUDENT_FAMILY="family";
    static final String STUDENT_NAME="name";
    static final String STUDENT_PHONE="phone";
    //Create table
    static final String DB_CREATE="create table " + STUDENT_TABLE + "("
            + STUDENT_ID + "integer primary key autoincrement,"
            + STUDENT_FAMILY + " text, "
            + STUDENT_NAME + " text, "
            + STUDENT_PHONE + " integer" + ");";
    //authority
    static final String AUTHORITY ="com.example.user.contentprovider.StudentProvider";
   //path
    static final String STIDENT_PATH="students";

    //Общий Uri
    public static final Uri STUDENT_CONTENT_URI=Uri.parse("content://"
    +AUTHORITY+ "/" + STIDENT_PATH);

    static final String STUDENT_CONTENT_TYPE="vnd.android.cursor.dir/vnd."
            +AUTHORITY + "."+ STIDENT_PATH;
    static final String STRING_CONTENT_ITEM_TYPE="vnd.android.cursor.item/vnd."
            + AUTHORITY+ "." + STIDENT_PATH;
    static final int URI_STUDENTS=1;
    static final  int URI_STUDENTS_ID=2;

    private  static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, STIDENT_PATH, URI_STUDENTS);
        uriMatcher.addURI(AUTHORITY, STIDENT_PATH + "/#", URI_STUDENTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper=new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortedOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS:
                Log.d(LOG_TAG, "URI_STUDENTS");
                if (TextUtils.isEmpty(sortedOrder)) {
                    sortedOrder = STUDENT_NAME + " ASC";
                }
                break;
            case URI_STUDENTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_STUDENTS_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = STUDENT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + STUDENT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URL:" + uri);
        }
        db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query(STUDENT_TABLE, projection, selection, selectionArgs, null,null,sortedOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), STUDENT_CONTENT_URI);


        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
