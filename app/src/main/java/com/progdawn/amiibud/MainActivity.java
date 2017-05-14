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
    AmiiboAdapter mAdapter;
    RecyclerView mRecyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmiibos = Collection.get(MainActivity.this).getAmiibos();
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.amiibo_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new AmiiboAdapter(this, mAmiibos);
        mRecyclerView.setAdapter(mAdapter);
        updateUI();


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_amiibo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAmiiboActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        Collection collection = Collection.get(MainActivity.this);
        mAmiibos = collection.getAmiibos();

        if(mAdapter == null){
            mAdapter = new AmiiboAdapter(MainActivity.this, mAmiibos);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setAmiibos(mAmiibos);
            mAdapter.notifyDataSetChanged();
        }
    }
}
