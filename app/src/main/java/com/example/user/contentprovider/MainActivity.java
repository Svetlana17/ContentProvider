package com.example.user.contentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView liststudent;
    ArrayAdapter <AppStudent> studentArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onResume(){
        super.onResume();
        DatabaseAdapter adapter =new DatabaseAdapter(this);
        adapter.open();

        List<AppStudent> student=adapter.getStudent();

        studentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, student);
        liststudent.setAdapter(studentArrayAdapter);
        adapter.close();
    }
}
