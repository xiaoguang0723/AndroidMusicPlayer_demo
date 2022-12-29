package com.example.mych_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
//  SQLiteOpenHelper是SQLiteDatabse的一个帮助类，用来管理数据的创建和版本更新。
class User_Database extends SQLiteOpenHelper {
    Context context;
    public User_Database(Context context) {

        super(context, "user_db", null, 1);
        this.context=context;
        System.out.println("in User_Database ,context="+ context);
    }
    //需要实现两个回调方法onCreate和onUpgrade
    //初次生成数据库时才会被调用
    //SQLiteDatabase可以完成对数据进行添加(Create)、查询(Retrieve)、更新(Update)和删除(Delete)操作。
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("onCreate");
        String sql = "create table user(id integer primary key autoincrement,username varchar(20),paswd varchar(20),sex varchar(20),hobby varchar(30))";
        //exceSQL(String sql)：执行SQL语句
        sqLiteDatabase.execSQL(sql);
        Log.i("SQLite","execSQL(sql)");
    }

    public boolean tabbleIsExist(SQLiteDatabase sqLiteDatabase){
        boolean result = true;
        try {
            String sql = "create table user(id integer primary key autoincrement,username varchar(20),paswd varchar(20),sex varchar(20),hobby varchar(30))";
            //exceSQL(String sql)：执行SQL语句
            sqLiteDatabase.execSQL(sql);


        } catch (Exception e) {
            // TODO: handle exception
            result = false;
            Log.i("SQLite","user表已存在");
        }
        return result;
    }

    public void dropTable(SQLiteDatabase db){
        db.execSQL("drop table user");
    }
    //数据库版本号更新时调用:一般在软件升级时才需改变版本号，而数据库的版本是由程序员控制的
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //添加数据
    public void adddata(SQLiteDatabase sqLiteDatabase, String username, String paswd, String sex, String hobby) {
        /*ContentValues类和Bundle类很类似，都是使用HashMap的泛型形式来存储的，并且都是HashMap<String, Object>()。
        但是ContentValues和Bundle有一个很明显的区别：Bundle可以存储Object对象，但是Contentvalues却只能存储基本类型的数据，
        相当于HashMap<String,基本数据类型>这种形式。究其根本是制于它的put方法，Contentvalues类中有很多用于存放数据的Put方法，
        但是这些put方法只会接收基本数据类型的参数，因此存储的时候也就只能存储基本数据类型。*/
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("paswd", paswd);
        values.put("sex", sex);
        values.put("hobby", hobby);
        //代表想插入数据的表名；nullColumnHack：代表强行插入null值的数据列的列名；values：代表一行记录的数据。
        sqLiteDatabase.insert("user", null, values);
        sqLiteDatabase.close();
    }

    //删除数据
    public void delete(SQLiteDatabase sqLiteDatabase, int id) {
        //delete(String table,String whereClause,String[] whereArgs)：删除满足特定条件的数据。
        /*第一个参数：表名；第二个参数：需要删除的属性名，？代表占位符；第三个参数：属性名的属性值*/
        sqLiteDatabase.delete("user", "id=?", new String[]{id + ""});
        sqLiteDatabase.close();
    }

    //更新数据
    public void update(SQLiteDatabase sqLiteDatabase, int id, String username,
                       String paswd, String sex, String hobby) {
        //创建一个ContentValues对象
        ContentValues values = new ContentValues();
        //以键值对的形式插入
        values.put("username", username);
        values.put("paswd", paswd);
        values.put("sex", sex);
        values.put("hobby", hobby);
        //执行修改的方法
        //update(String table,ContentValues values,String whereClause,String[] whereArgs)：修改满足条件的数据。
        sqLiteDatabase.update("user", values, "id=?", new String[]{id + ""});
        sqLiteDatabase.close();
    }

    //查询数据
    public List<UserInfo> querydata(SQLiteDatabase sqLiteDatabase) {
        //Cursor query(boolean distinct,String table,String[] columns,
        //      String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit)：
        //该方法用于查询数据。
        //Cursor是一个游标接口，在数据库中作为返回值，相当于结果集ResultSet
        Cursor cursor = sqLiteDatabase.query("user", null, null, null, null, null, "id ASC");
        List<UserInfo> list = new ArrayList<UserInfo>();
        Log.i("SQLite","ttttttttt");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Log.i("SQLite","id="+id);
            String username = cursor.getString(1);
            Log.i("SQLite","username="+username);
            String paswd = cursor.getString(2);
            Log.i("SQLite","paswd="+paswd);
            String sex = cursor.getString(3);
            Log.i("SQLite","sex="+sex);
            String hobby = cursor.getString(4);
            Log.i("SQLite","hobby="+hobby);
            list.add(new UserInfo(id, username, paswd, sex, hobby));
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    /**
     * 一些查询用法
     */
    public String queryHobbyData(SQLiteDatabase sqLiteDatabase,String id)
    {
        String hobby="";
        Cursor cursor = sqLiteDatabase.query("user", null, "username=?",new String[]{id}, null, null, null, null);
        while (cursor.moveToNext()) {
            hobby = cursor.getString(4);
            Log.i("cccc11", hobby);

        }
        return hobby;
    }
}