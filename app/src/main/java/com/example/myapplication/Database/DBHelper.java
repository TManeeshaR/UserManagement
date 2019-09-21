package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DBMobileMania.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_USER_ENTRIES = "CREATE TABLE " + UsrMaster.Usr.TABLE_NAME + "(" +
                UsrMaster.Usr._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsrMaster.Usr.COLUMN_NAME_FIRSTNAME + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_LASTNAME + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_USERNAME + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_PASSWORD + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_ADDRESS + " TEXT," +
                UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER + " INT)";

        sqLiteDatabase.execSQL(SQL_CREATE_USER_ENTRIES);

        ContentValues values = new ContentValues();
        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_PASSWORD, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, "xxx");
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, 772556158);

        sqLiteDatabase.insert(UsrMaster.Usr.TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUsrDetails(String fname, String lname, String uname, String pass, String cpass, String address, String cNumber) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, fname);
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, lname);
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, uname);
        values.put(UsrMaster.Usr.COLUMN_NAME_PASSWORD, pass);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONFIRMPASSWORD, cpass);
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, address);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, cNumber);

        long newRowId = sqLiteDatabase.insert(UsrMaster.Usr.TABLE_NAME, null, values);

        if (newRowId >= 1)
            return true;
        else
            return false;
    }

    public int login(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM "+ UsrMaster.Usr.TABLE_NAME +" WHERE "+ UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] s = {username};

        Cursor result = sqLiteDatabase.rawQuery(query, s);

        if(result.getCount() == 0){
            //no username
            return -1;
        }

        String dbPassword;

        while(result.moveToNext()){
            dbPassword = result.getString(4);

            if(!dbPassword.equals(password)){
                //invalid pwd
                return 0;
            }
            else{
                //login success
                return 1;
            }
        }

        return  -2;

    }

    public Cursor getUserData(String username){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String query = "SELECT * FROM "+ UsrMaster.Usr.TABLE_NAME+" WHERE "+ UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] s = {username};

        Cursor result = sqLiteDatabase.rawQuery(query, s);

        return result;
    }

    public boolean updateInfo(String fname,String lname,String uname,String address,String contactNo) {
        SQLiteDatabase db = getReadableDatabase();

        //New value for one column
        ContentValues values = new ContentValues();
        values.put(UsrMaster.Usr.COLUMN_NAME_FIRSTNAME, fname);
        values.put(UsrMaster.Usr.COLUMN_NAME_LASTNAME, lname);
        values.put(UsrMaster.Usr.COLUMN_NAME_USERNAME, uname);
        values.put(UsrMaster.Usr.COLUMN_NAME_ADDRESS, address);
        values.put(UsrMaster.Usr.COLUMN_NAME_CONTACTNUMBER, contactNo);


        //Which row to update, based on the title
        String selection = UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {uname};

        int count = db.update(
                UsrMaster.Usr.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if(count >= 1)
            return true;
        else
            return false;
    }




    public boolean deleteInfo(String userName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //Define 'where' part of query
        String selection = UsrMaster.Usr.COLUMN_NAME_USERNAME + " = ?";
        //Specify arguments n placeholder order
        String[] selectionArgs = {userName};
        //Issue SQL statement
        int result = sqLiteDatabase.delete(UsrMaster.Usr.TABLE_NAME, selection, selectionArgs);

        if (result > 0)
        {
            return true;
        }

        else {
            return false;
        }

    }
}