package me.sam.poetry.data.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import me.sam.poetry.data.database.asset.SQLiteAssetHelper;

/**
 * desc :
 * date : 2018/8/12
 *
 * @author : dongSen
 */
public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "ci.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    public Cursor getPoetry() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String sqlTables = "ci";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, null, null, null,
                null, null, null);

        c.moveToFirst();
        return c;

    }
}
