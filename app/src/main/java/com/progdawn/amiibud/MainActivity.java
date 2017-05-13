package com.progdawn.amiibud;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<TestAmiibo> amiibos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvAmiibos = (RecyclerView)findViewById(R.id.amiibo_recycler_view);
        amiibos = TestAmiibo.createAmiibosList(20);
        AmiiboAdapter adapter = new AmiiboAdapter(this, amiibos);
        rvAmiibos.setAdapter(adapter);
        rvAmiibos.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_amiibo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AmiiboActivity.class));
            }
        });
    }
}
