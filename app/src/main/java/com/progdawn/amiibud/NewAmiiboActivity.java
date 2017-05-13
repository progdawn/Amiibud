package com.progdawn.amiibud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

public class NewAmiiboActivity extends AppCompatActivity{

    private Amiibo mAmiibo;
    private EditText mNameField;
    private EditText mSeriesField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_amiibo_activity);

        mAmiibo = new Amiibo();
        Collection.get(NewAmiiboActivity.this).addAmiibo(mAmiibo);

        mNameField = (EditText)findViewById(R.id.new_amiibo_name);
        mSeriesField = (EditText)findViewById(R.id.new_amiibo_series);

        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAmiibo.setName(s.toString());
                Collection.get(NewAmiiboActivity.this).updateAmiibo(mAmiibo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSeriesField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAmiibo.setSeries(s.toString());
                Collection.get(NewAmiiboActivity.this).updateAmiibo(mAmiibo);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
