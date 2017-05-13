package com.progdawn.amiibud;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Amiibo> mAmiibos;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmiibos = Collection.get(MainActivity.this).getAmiibos();
        RecyclerView rvAmiibos = (RecyclerView)findViewById(R.id.amiibo_recycler_view);
        AmiiboAdapter adapter = new AmiiboAdapter(this, mAmiibos);

        rvAmiibos.setAdapter(adapter);
        rvAmiibos.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_amiibo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAmiiboActivity.class);
                startActivity(intent);
            }
        });
    }

}
