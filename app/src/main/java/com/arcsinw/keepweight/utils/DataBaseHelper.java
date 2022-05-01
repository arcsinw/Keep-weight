package com.arcsinw.keepweight.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.arcsinw.keepweight.model.Weight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author x
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_WEIGHT = "create table weight (" +
            "id integer primary key autoincrement," +
            "weight real," +
            "create_time real)";

    private Context context;

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_WEIGHT);
        Toast.makeText(this.context, "Successfully create database", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Weight> getWeights() {
        Cursor cursor = getReadableDatabase().rawQuery("select * from weight", new String[0]);


        List<Weight> weights = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            double weight = cursor.getDouble(cursor.getColumnIndex("weight"));
            long date = cursor.getLong(cursor.getColumnIndex("create_time"));

            Weight weight1 = new Weight();
            weight1.setWeight(weight);
            weight1.setCreateTime(new Date());
            weights.add(weight1);
        }

        return weights;
    }
}
