package com.example.pcsingh.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.itemList)
    protected RecyclerView itemList;
    @BindArray(R.array.colors)
    protected int [] rainbow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        itemList.setLayoutManager(new LinearLayoutManager(this));
        itemList.setAdapter(new MyAdapter(rainbow));
    }
}
