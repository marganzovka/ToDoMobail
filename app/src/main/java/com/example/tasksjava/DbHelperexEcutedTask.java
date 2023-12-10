package com.example.tasksjava;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelperexEcutedTask extends SQLiteOpenHelper {
    public DbHelperexEcutedTask(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Const.CREATE_ECUTED_TABLE);
        // Check if the table was created successfully
        Cursor cursor = db.rawQuery(Const.SELECT_ECUTED_TABLE, null);
        if (cursor != null) {
            cursor.close();
            Log.d("DbHelperexEcutedTask", "Table created successfully");
        } else {
            Log.e("DbHelperexEcutedTask", "Error creating table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Const.DROP_ECUTED_TABLE);
        onCreate(db);
    }

    public void AddExecutedTask(String nameExecutedTask){
        ContentValues values = new ContentValues();
        values.put(Const.NAME_COLUMN, nameExecutedTask);

        SQLiteDatabase db = this.getWritableDatabase();


        long result = db.insert(Const.NAME_DATABASE_TASKECUTED, null, values);

        if (result != -1) {
            Log.d("DbHelperexEcutedTask", "Task added successfully");
        } else {
            Log.e("DbHelperexEcutedTask", "Error adding task");
        }

        db.close();
    }


    public ArrayList<String> getAllTasks() {
        ArrayList<String> TaskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Const.SELECT_ECUTED_TABLE, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String task = cursor.getString(cursor.getColumnIndex(Const.NAME_COLUMN));
                TaskList.add(task);
                Log.d("DbHelperexEcutedTask", "Task added get task");
            } while (cursor.moveToNext());
        } else {
            Log.e("DbHelperexEcutedTask", "Error get task");
        }

        if (cursor != null) {
            cursor.close();
        }
        return TaskList;
    }


    public void deleteTaskByName(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.NAME_DATABASE_TASKECUTED, "task=?", new String[]{taskName});
        db.close();
    }
}
