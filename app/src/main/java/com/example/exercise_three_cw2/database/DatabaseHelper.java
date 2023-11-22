package com.example.exercise_three_cw2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.exercise_three_cw2.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS contact_table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "uuid TEXT," +
                "name TEXT," +
                "dob TEXT," +
                "email TEXT," +
                "avatar INTEGER" +
                ")";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact_table");
        onCreate(db);
    }

    public void insertData(ContactModel contactModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uuid", contactModel.getUuid());
        contentValues.put("name", contactModel.getName());
        contentValues.put("dob", contactModel.getDob());
        contentValues.put("email", contactModel.getEmail());
        contentValues.put("avatar", contactModel.getAvatar());
        db.insert("contact_table", null, contentValues);
        db.close();
    }

    public List<ContactModel> readData() {
        List<ContactModel> data = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM contact_table", null);
        while (cursor.moveToNext()) {
            String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String dob = cursor.getString(cursor.getColumnIndex("dob"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            int avatar = cursor.getInt(cursor.getColumnIndex("avatar"));
            data.add(new ContactModel(uuid, name, dob, email, avatar));
        }
        cursor.close();
        return data;
    }
}