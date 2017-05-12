package com.progdawn.amiibud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.progdawn.amiibud.database.AmiiboBaseHelper;
import com.progdawn.amiibud.database.AmiiboCursorWrapper;
import com.progdawn.amiibud.database.AmiiboDbSchema;
import com.progdawn.amiibud.database.AmiiboDbSchema.AmiiboTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dawn Myers on 5/11/2017.
 */

public class Collection {

    private static Collection sCollection;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Collection get(Context context){
        if(sCollection == null){
            sCollection = new Collection(context);
        }
        return sCollection;
    }

    private Collection(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new AmiiboBaseHelper(mContext).getWritableDatabase();
    }

    public List<Amiibo> getAmiibos(){
        List<Amiibo> amiibos = new ArrayList<>();

        AmiiboCursorWrapper cursor = queryAmiibos(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                amiibos.add(cursor.getAmiibo());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }

        return amiibos;
    }

    public Amiibo getAmiibo(UUID id){
        AmiiboCursorWrapper cursor = queryAmiibos(AmiiboTable.Cols.UUID + "=?", new String[]{id.toString()});
        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAmiibo();
        }finally{
            cursor.close();
        }
    }

    public void addAmiibo(Amiibo a){
        ContentValues values = getContentValues(a);

        mDatabase.insert(AmiiboTable.NAME, null, values);
    }

    public void updateAmiibo(Amiibo amiibo){
        String uuidString = amiibo.getId().toString();
        ContentValues values = getContentValues(amiibo);

        mDatabase.update(AmiiboTable.NAME, values, AmiiboTable.Cols.UUID + "=?", new String[]{uuidString});
    }

    public File getPhotoFile(Amiibo amiibo){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, amiibo.getPhotoFilename());
    }

    private static ContentValues getContentValues(Amiibo amiibo){
        ContentValues values = new ContentValues();

        values.put(AmiiboTable.Cols.UUID, amiibo.getId().toString());
        values.put(AmiiboTable.Cols.NAME, amiibo.getName());
        values.put(AmiiboTable.Cols.SERIES, amiibo.getSeries());
        values.put(AmiiboTable.Cols.DATE, amiibo.getDate().getTime());

        return values;
    }

    private AmiiboCursorWrapper queryAmiibos(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(AmiiboTable.NAME, null, whereClause, whereArgs, null, null, null);

        return new AmiiboCursorWrapper(cursor);
    }
}
