package com.progdawn.amiibud;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

public class AmiiboActivity extends AppCompatActivity{

    private Amiibo mAmiibo;
    private EditText mNameField;
    private EditText mSeriesField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amiibo_activity);

    }
}
