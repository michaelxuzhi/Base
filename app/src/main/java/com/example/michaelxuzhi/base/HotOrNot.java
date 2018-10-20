package com.example.michaelxuzhi.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;




public class HotOrNot {
    private static final String KEY_ROWID = "_id";
    private static final String KEY_NAME = "persons_name";
    private static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_version = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;



    private static class DbHelper extends SQLiteOpenHelper {
        DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "
                    + DATABASE_TABLE + " ("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL, "
                    + KEY_HOTNESS + " TEXT NOT NULL);");
        }


        //更新
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXITS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    HotOrNot(Context c) {
        ourContext = c;
    }

    //打开数据库,throws是抛出异常，在sqliteExample.java中的onCLick()中的try来捕获异常
    HotOrNot open() throws SQLiteException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();     //写的权限
        return this;
    }


    //关闭数据库
    void close(){
        ourHelper.close();
    }


    //插入数据
    long createEntry(String name, String hotness){
        ContentValues cv =new ContentValues(); //定义个容器
        cv.put(KEY_NAME,name);
        cv.put (KEY_HOTNESS,hotness);
        return  ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public String getData() {
        String[] colunms = new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
        String result="";

        Cursor c = ourDatabase.query(DATABASE_TABLE, colunms,null,null,null,null,null);

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        //读数据表，光标从first然后+1往下读
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) + "  " + c.getString(iName) + " " + c.getString(iHotness) + "\n";
        }
        c.close();
        return result;
    }
}


















