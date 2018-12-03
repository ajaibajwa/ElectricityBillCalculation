package com.example.ajay_mac.electricitybillcalculation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;

public class ElectricityBillActivity extends AppCompatActivity {
    private EditText edtCustId;
    private EditText edtCustName;
    private EditText edtCustEmail;
    private TextView txtGender;
    private RadioGroup rdgGender;
    private TextView txtBillDate;
    private EditText edtUnitsConsumed;
    private TextView txtTotalAmount;
    private TextView txtDisplayAmount;
    private Button btnSave;
    private Calendar mcurrentDate;
    int day,month,year;
    final Context context=this;
    private final AppCompatActivity activity=ElectricityBillActivity.this;
    private DatabaseHelper databaseHelper;
    private ElectricityBill electricityBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_bill);
        edtCustId=findViewById(R.id.editCustomerID);
        edtCustName=findViewById(R.id.editCustomerName);
        edtCustEmail=findViewById(R.id.editCustomerEmail);
        txtGender=findViewById(R.id.textGender);
        rdgGender=findViewById(R.id.rgGender);
        txtBillDate=findViewById(R.id.textBillDate);
        edtUnitsConsumed=findViewById(R.id.editUnitsConsumed);
        txtTotalAmount=findViewById(R.id.textTotalAmount);
        txtDisplayAmount=findViewById(R.id.textDisplayAmount);
        btnSave=findViewById(R.id.buttonSave);
        //databaseHelper=new DatabaseHelper(this);
        mcurrentDate=Calendar.getInstance();
        day=mcurrentDate.get(Calendar.DAY_OF_MONTH);
        day=day-1;
        month=mcurrentDate.get(Calendar.MONTH);
        year=mcurrentDate.get(Calendar.YEAR);
        //txtBillDate.setText(day+"/"+month+"/"+year);
        txtBillDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ElectricityBillActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            txtBillDate.setText(i + "/" + i1 + "/" + i2);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        edtCustId.setText("C");
        Selection.setSelection(edtCustId.getText(), edtCustId.getText().length());
        edtCustId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().startsWith("C")) {
                    edtCustId.setText("C");
                    Selection.setSelection(edtCustId.getText(), edtCustId.getText().length());
                    edtCustId.setHint("Enter CustID");
                }

            }

        });
        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=rdgGender.getCheckedRadioButtonId();
                RadioButton rbGender=findViewById(id);
                txtGender.setText(rbGender.getText().toString());
            }
        });
        edtUnitsConsumed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(edtUnitsConsumed.hasFocus()==false){
                 billCalculation();
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emptyFieldValidation()){
                    if (emailValidation()){
                            databaseHelper=new DatabaseHelper(activity);
                            electricityBill = new ElectricityBill();
                /*String custId=edtCustId.getText().toString();
                String custName=edtCustName.getText().toString();
                String custEmail=edtCustEmail.getText().toString();
                String custGender=txtGender.getText().toString();
                String billDate=txtBillDate.getText().toString();
                Double unitsConsumed=Double.parseDouble(edtUnitsConsumed.getText().toString());
                Double totalAmount=Double.parseDouble(txtTotalAmount.getText().toString());*/
                            electricityBill.setCustomerId(edtCustId.getText().toString().trim());
                            electricityBill.setCustomerName(edtCustName.getText().toString().trim());
                            electricityBill.setCustomerEmail(edtCustEmail.getText().toString().trim());
                            electricityBill.setGender(txtGender.getText().toString().trim());
                            electricityBill.setBillDate(txtBillDate.getText().toString().trim());
                            electricityBill.setUnitConsumed(Double.parseDouble(edtUnitsConsumed.getText().toString().trim()));
                            electricityBill.setTotalBillAmount(Double.parseDouble(txtTotalAmount.getText().toString().trim()));

                            boolean insertData = databaseHelper.addBill(electricityBill);
                            if (insertData){
                                Toast.makeText(ElectricityBillActivity.this,"DATA ADDED",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ElectricityBillActivity.this,"DATA NOT ADDED",Toast.LENGTH_SHORT).show();
                            }
                            Intent mIntent=new Intent(ElectricityBillActivity.this,BillDetailsActivity.class);
                            //Toast.makeText(ElectricityBillActivity.this,"hello",Toast.LENGTH_SHORT).show();
                            //mIntent.putExtra("billdetails",electricityBill);
                            startActivity(mIntent);
                            //finish();


                    }
                }


            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menuMyBills:
                Intent mIntent=new Intent(this,BillDetailsActivity.class);
                startActivity(mIntent);
                Toast.makeText(this, "My Bills!", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.menuLogOut:
                Intent Intent=new Intent(this,LoginActivity.class);
                startActivity(Intent);
                Toast.makeText(this,"Successfully Logged Out!",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void billCalculation(){
        NumberFormat format=NumberFormat.getCurrencyInstance();
        Double unitsConsumed=Double.parseDouble(edtUnitsConsumed.getText().toString());
        Double totalAmount = null;
        if (unitsConsumed>450){
            totalAmount=unitsConsumed*2.25;
        }
        else if (unitsConsumed<=100){
            totalAmount=unitsConsumed*0.75;
        }
        else if ((unitsConsumed<=250)&&(unitsConsumed>100)){
            totalAmount=(100*0.75)+((unitsConsumed-100)*1.25);
        }
        else if ((unitsConsumed<=450)&&(unitsConsumed>250)){
            totalAmount=(100*0.75)+(150*1.25)+((unitsConsumed-250)*1.75);
        }
        //Toast.makeText(ElectricityBillActivity.this,"hello",Toast.LENGTH_SHORT).show();
        txtDisplayAmount.setText(format.format(totalAmount));
        txtTotalAmount.setText(Double.toString(totalAmount));
    }
    public boolean emailValidation(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        edtCustEmail=findViewById(R.id.editCustomerEmail);
        String email=edtCustEmail.getText().toString();

        if (!email.matches(emailPattern)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Invalid Email!");
            builder.setMessage("Please enter valid Email Address")
                    .setCancelable(false)
                    //.setIcon(R.drawable.ic_invalid_alert)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });
            AlertDialog alertDialog = builder.create();

            // show it
            alertDialog.show();
            return false;
        }
        return true;
    }
    public boolean emptyFieldValidation(){
        String custId=edtCustId.getText().toString();
        String custName=edtCustName.getText().toString();
        String custEmail=edtCustEmail.getText().toString();
        String custGender=txtGender.getText().toString();
        String billDate=txtBillDate.getText().toString();
        String unitsConsumed=(edtUnitsConsumed.getText().toString());
        String totalAmount=(txtTotalAmount.getText().toString());
        if ((custId.length())!=10||custName.isEmpty()||custEmail.isEmpty()||custGender.equals("Gender")||billDate.isEmpty()||unitsConsumed.isEmpty()||totalAmount.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Empty Field Alert!");
            builder.setMessage("Please enter values in all the fields.")
                    .setCancelable(false)
                    //.setIcon(R.drawable.ic_invalid_alert)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });
            AlertDialog alertDialog = builder.create();

            // show it
            alertDialog.show();
            return false;
        }
        return true;
    }


}
