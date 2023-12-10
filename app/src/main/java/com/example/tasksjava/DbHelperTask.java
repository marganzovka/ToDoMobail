package com.example.tasksjava;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelperTask extends SQLiteOpenHelper {

    public DbHelperTask(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Const.CREATE_TASKS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Const.DROP_TASK_TABLE);
        onCreate(db);
    }

    public void AddTask(String nameTask){
        ContentValues values = new ContentValues();
        values.put(Const.NAME_COLUMN, nameTask);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Const.NAME_DATABASE_TASK, null, values);

        db.close();
    }


    public ArrayList<String> getAllTasks() {
        ArrayList<String> TaskList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Const.SELECT_TASK_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String task = cursor.getString(cursor.getColumnIndex(Const.NAME_COLUMN));
                TaskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return TaskList;
    }

    public void deleteTaskByName(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Const.NAME_DATABASE_TASK, "task=?", new String[]{taskName});
        db.close();
    }
}
