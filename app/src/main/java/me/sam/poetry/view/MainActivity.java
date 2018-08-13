package me.sam.poetry.view;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import me.sam.poetry.R;
import me.sam.poetry.base.BaseActivity;
import me.sam.poetry.data.database.MyDatabase;
import me.sam.poetry.data.model.Poetry;

public class MainActivity extends BaseActivity {

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);


        RecyclerView recyclerView = findViewById(R.id.recycler_view_poetry);

        recyclerView.setHasFixedSize(true);
        PoetryAdapter poetryAdapter = new PoetryAdapter();
        recyclerView.setAdapter(poetryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addData(poetryAdapter);
        poetryAdapter.notifyDataSetChanged();

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    vibrator.vibrate(40);
                }
            }
        });
    }

    private void addData(PoetryAdapter poetryAdapter) {

        MyDatabase db = new MyDatabase(this);

        Cursor cursor = db.getPoetry();

        ArrayList<Poetry> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Poetry poetry = new Poetry();
            poetry.setTitle(cursor.getString(cursor.getColumnIndex("rhythmic")));
            poetry.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            poetry.setContent(cursor.getString(cursor.getColumnIndex("content")));
            list.add(poetry);
            cursor.moveToNext();
        }
        poetryAdapter.addData(list);

    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
