package com.example.ajay_mac.electricitybillcalculation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="customers.db";
    public static final String TABLE_NAME="billdetails_table";
    public static final String COL0="ID";
    public static final String COL1="CUSTID";
    public static final String COL2="NAME";
    public static final String COL3="EMAIL";
    public static final String COL4="GENDER";
    public static final String COL5="BILLDATE";
    public static final String COL6="UNITSCONSUMED";
    public static final String COL7="TOTALAMOUNT";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_USER_TABLE="CREATE TABLE billdetails_table ( " + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "CUSTID TEXT," +"NAME TEXT," +"EMAIL TEXT," +"GENDER TEXT," +"BILLDATE TEXT," +"UNITSCONSUMED REAL," +"TOTALAMOUNT REAL )";
        String CREATE_USER_TABLE="CREATE TABLE billdetails_table (" + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL1 + " TEXT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT, " + COL6 + " REAL, " + COL7 + " REAL " + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_USER_TABLE=" DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_USER_TABLE);
        this.onCreate(db);
    }
    public boolean addBill(ElectricityBill electricityBill){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL1,electricityBill.getCustomerId());
        values.put(COL2,electricityBill.getCustomerName());
        values.put(COL3,electricityBill.getCustomerEmail());
        values.put(COL4,electricityBill.getGender());
        values.put(COL5,electricityBill.getBillDate());
        values.put(COL6,electricityBill.getUnitConsumed());
        values.put(COL7,electricityBill.getTotalBillAmount());
        Log.d(TAG,"addBill: Adding " + electricityBill + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }

    }
    /*public Cursor getBillDetails(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query= "SELECT * FROM " + TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);
        return cursor;
    }*/

    public ArrayList<ElectricityBill> getBillDetails()
    {
        ArrayList<ElectricityBill> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query= "SELECT * FROM " + TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);
        while (cursor.moveToNext())
        {
            String cId=cursor.getString(1);
            String cName=cursor.getString(2);
            String cEmail=cursor.getString(3);
            String cGender=cursor.getString(4);
            String cBillDate=cursor.getString(5);
            Double cBillUnits=cursor.getDouble(6);
            Double cBillAmount=cursor.getDouble(7);
            ElectricityBill electricityBill=new ElectricityBill(cId,cName,cEmail,cGender,cBillDate,cBillUnits,cBillAmount);
            arrayList.add(electricityBill);
        }
        return arrayList;
    }
}
