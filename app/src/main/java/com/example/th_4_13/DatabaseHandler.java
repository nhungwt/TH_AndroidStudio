package com.example.th_4_13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.th_4_13.model.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "workManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DEADLINE = "deadline";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_STATUS = "status";
    private static final String KEY_COLABORATION = "colaboration";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_tasks_table = String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_CONTENT, KEY_STATUS, KEY_DEADLINE, KEY_COLABORATION);
        db.execSQL(create_tasks_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_tasks_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_tasks_table);

        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DEADLINE, task.getDeadline());
        values.put(KEY_CONTENT, task.getContent());
        values.put(KEY_STATUS, task.getStatus());
        values.put(KEY_COLABORATION, task.getColaboration());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Task getTask(int taskId) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[]{String.valueOf(taskId)}, null, null, null);
        if (cursor != null)
            // db.query sẽ trả về một Cursor, lúc này Cursor đầu đọc chưa trỏ tới dòng dữ liệu nào cả,
            //  do đó, ta phải gọi lệnh .moveToFirst() để Cursor có thể trỏ tới dòng đầu tiên.
            cursor.moveToFirst();
        Task task = new Task(cursor.getInt(0), cursor.getString(1), // ID + name
                cursor.getString(2), cursor.getString(3), // deadline + content
                cursor.getString(4), cursor.getInt(5) == 1 ? true : false); //status + colab
        return task;
    }

    public List<Task> getAllTasks() throws ParseException {
        List<Task> taskList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Task task = new Task(cursor.getInt(0), cursor.getString(1), // ID + name
                    cursor.getString(2), cursor.getString(3), // deadline + content
                    cursor.getString(4), cursor.getInt(5) == 1 ? true : false); //status + colab
            taskList.add(task);
            cursor.moveToNext();
        }
        db.close();
        return taskList;
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DEADLINE, task.getDeadline());
        values.put(KEY_CONTENT, task.getContent());
        values.put(KEY_STATUS, task.getStatus());
        values.put(KEY_COLABORATION, task.getColaboration());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public void deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(taskId)});
        db.close();
    }

}
