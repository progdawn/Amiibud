package com.progdawn.amiibud.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dawn Myers on 5/11/2017.
 */

public class AmiiboBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "amiibo.db";

    public AmiiboBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AmiiboDbSchema.AmiiboTable.NAME + "(" +
                AmiiboDbSchema.AmiiboTable.Cols.UUID + ", " +
                AmiiboDbSchema.AmiiboTable.Cols.NAME + ", " +
                AmiiboDbSchema.AmiiboTable.Cols.SERIES + ", " +
                AmiiboDbSchema.AmiiboTable.Cols.DATE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
