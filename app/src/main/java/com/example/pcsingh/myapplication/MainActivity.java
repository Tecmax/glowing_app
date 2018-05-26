package com.example.pcsingh.myapplication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.itemList)
    protected RecyclerView itemList;
    @BindArray(R.array.colors)
    protected int[] rainbow;
    @BindArray(R.array.image_list)
    protected String[] imageList;

    @BindView(R.id.new_arrival)
    protected ImageView newArrival;
    @BindView(R.id.lowest_price)
    protected ImageView lowestPrice;
    @BindView(R.id.best_selling)
    protected ImageView bestSelling;
    @BindString(R.string.new_arrival)
    protected String headerImageNewArrival;
    @BindString(R.string.lowest_price)
    protected String headerImageLowestPrice;
    @BindString(R.string.best_selling)
    protected String headerImageBestSelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        itemList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        itemList.setAdapter(new MyAdapter(rainbow, imageList));

        ImageUtil.getInstance().loadImage(headerImageNewArrival, newArrival);
        ImageUtil.getInstance().loadImage(headerImageLowestPrice, lowestPrice);
        ImageUtil.getInstance().loadImage(headerImageBestSelling, bestSelling);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
