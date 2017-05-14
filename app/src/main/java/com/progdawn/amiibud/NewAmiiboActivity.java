package com.progdawn.amiibud;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.File;

/**
 * Created by Dawn Myers on 5/13/2017.
 */

public class NewAmiiboActivity extends AppCompatActivity{

    private static final int REQUEST_PHOTO = 0;

    private Amiibo mAmiibo;
    private EditText mNameField;
    private EditText mSeriesField;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_amiibo_activity);

        mAmiibo = new Amiibo();
        Collection.get(NewAmiiboActivity.this).addAmiibo(mAmiibo);

        mNameField = (EditText)findViewById(R.id.new_amiibo_name);
        mSeriesField = (EditText)findViewById(R.id.new_amiibo_series);
        mPhotoButton = (ImageButton)findViewById(R.id.amiibo_camera);
        mPhotoView = (ImageView)findViewById(R.id.amiibo_picture);

        mPhotoFile = Collection.get(NewAmiiboActivity.this).getPhotoFile(mAmiibo);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        PackageManager packageManager = NewAmiiboActivity.this.getPackageManager();
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

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

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureWithCamera();
            }
        });
    }

    private void takePictureWithCamera(){
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = FileProvider.getUriForFile(NewAmiiboActivity.this, "com.progdawn.android.amiibud.fileprovider", mPhotoFile);

        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(captureIntent, REQUEST_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PHOTO && resultCode == RESULT_OK){
            setImageView();
        }
    }

    private void setImageView(){
        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoView.setImageDrawable(null);
        }else{
            Bitmap pictureBitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), NewAmiiboActivity.this);
            mPhotoView.setImageBitmap(pictureBitmap);
        }
    }
}
