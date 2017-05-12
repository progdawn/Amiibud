package com.progdawn.amiibud.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.progdawn.amiibud.Amiibo;
import com.progdawn.amiibud.database.AmiiboDbSchema.AmiiboTable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Dawn Myers on 5/11/2017.
 */

public class AmiiboCursorWrapper extends CursorWrapper {

    public AmiiboCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Amiibo getAmiibo(){
        String uuidString = getString(getColumnIndex(AmiiboTable.Cols.UUID));
        String name = getString(getColumnIndex(AmiiboTable.Cols.NAME));
        String series = getString(getColumnIndex(AmiiboTable.Cols.SERIES));
        long date = getLong(getColumnIndex(AmiiboTable.Cols.DATE));

        Amiibo amiibo = new Amiibo(UUID.fromString(uuidString));
        amiibo.setName(name);
        amiibo.setSeries(series);
        amiibo.setDate(new Date(date));

        return amiibo;
    }
}
