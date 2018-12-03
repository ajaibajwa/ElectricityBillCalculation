package com.example.ajay_mac.electricitybillcalculation;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ajay_mac.electricitybillcalculation.Adapters.MyAdapter;

import java.util.ArrayList;

public class BillDetailsActivity extends AppCompatActivity {
    private static  final String TAG="ListDetailsActivity";
    DatabaseHelper databaseHelper;
    private ListView lvBillDetails;
    //ArrayList<String> listItem;
    ArrayList<ElectricityBill> arrayList;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        lvBillDetails=findViewById(R.id.listBilldetails);
        databaseHelper=new DatabaseHelper(this);
        //listItem=new ArrayList<>();
        arrayList=new ArrayList<>();
        displayListView();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_back_button,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menuBack:
                //Toast.makeText(this, "My Bills!", Toast.LENGTH_SHORT).show();
                Intent mIntent=new Intent(this,ElectricityBillActivity.class);
                startActivity(mIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void displayListView()
    {
        arrayList=databaseHelper.getBillDetails();
        myAdapter=new MyAdapter(this,arrayList);
        lvBillDetails.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    private  void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
