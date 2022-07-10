package sg.edu.np.mad.myapplication;

import static sg.edu.np.mad.myapplication.random.getRandom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "users.db";
    public static String USER = "User";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_FOLLOWED = "Followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + USER + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " INTEGER," + COLUMN_FOLLOWED + " INTEGER" + ")";

        db.execSQL(CREATE_USER_TABLE);

        for (int i = 0; i < 20; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "Name-" + getRandom());
            values.put(COLUMN_DESCRIPTION, "Description " + getRandom());
            values.put(COLUMN_ID, i + 1);
            Random rd = new Random();
            values.put(COLUMN_FOLLOWED, rd.nextBoolean());

            db.insert(USER, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }

    public ArrayList<User> getUsers() {
        String query = "SELECT * FROM " + USER + " ORDER BY " + COLUMN_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<User> queryData = new ArrayList<>();

        while (cursor.moveToNext()) {
            Boolean followed;
            if (cursor.getInt(3) > 0){
                followed = true;
            }
            else {
                followed = false;
            }
            queryData.add(new User(
                    cursor.getString(0), cursor.getString(1), cursor.getInt(2), followed
            ));
        }
        cursor.close();
        db.close();
        return queryData;
    }
    public User findUser(String name) {
        String query = "SELECT * FROM " + USER + " WHERE " + COLUMN_NAME
                + "=\"" + name + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();

        if (cursor.moveToFirst()) {
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            Boolean followed;
            if (cursor.getInt(3) > 0){
                followed = true;
            }
            else {
                followed = false;
            }
            queryData.setFollowed(followed);
            cursor.close();
        } else {
            queryData = null;
        }
        db.close();
        return queryData;
    }
    public void updateFollowed(User user) {
        String query = "UPDATE " + USER + " SET " + COLUMN_FOLLOWED + " = " + user.followed + " WHERE " + COLUMN_ID;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
