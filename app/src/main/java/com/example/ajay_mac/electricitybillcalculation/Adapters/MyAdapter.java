package com.example.ajay_mac.electricitybillcalculation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ajay_mac.electricitybillcalculation.ElectricityBill;
import com.example.ajay_mac.electricitybillcalculation.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<ElectricityBill> arrayList;
    public MyAdapter(Context context,ArrayList<ElectricityBill> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        DetailsViewHolder dHolder;
        if (convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.list_item,null);
            /*TextView txtCustId=(TextView)view.findViewById(R.id.textbillCId);
            TextView txtCustName=(TextView)view.findViewById(R.id.textBillName);
            TextView txtCustEmail=(TextView)view.findViewById(R.id.textbillEmail);
            TextView txtCustGender=(TextView)view.findViewById(R.id.textBillGender);
            TextView txtCustBillDate=(TextView)view.findViewById(R.id.textBillDate);
            TextView txtCustBillUnits=(TextView)view.findViewById(R.id.textBillUnits);
            TextView txtCustBillAmount=(TextView)view.findViewById(R.id.textBillAmount);*/
            dHolder=new DetailsViewHolder(convertView);
            convertView.setTag(dHolder);
        }
        else
        {
            dHolder=(DetailsViewHolder)convertView.getTag();

        }
        ElectricityBill electricityBill=arrayList.get(i);
        NumberFormat format=NumberFormat.getCurrencyInstance();

        dHolder.txtCustId.setText(electricityBill.getCustomerId());
        dHolder.txtCustName.setText(electricityBill.getCustomerName());
        dHolder.txtCustEmail.setText(electricityBill.getCustomerEmail());
        dHolder.txtCustGender.setText(electricityBill.getGender());
        dHolder.txtCustBillDate.setText(electricityBill.getBillDate());
        dHolder.txtCustBillUnits.setText(Double.toString(electricityBill.getUnitConsumed()));
        dHolder.txtCustBillAmount.setText(format.format(electricityBill.getTotalBillAmount()));
        return convertView;
    }
    private class DetailsViewHolder
    {
        TextView txtCustId;
        TextView txtCustName;
        TextView txtCustEmail;
        TextView txtCustGender;
        TextView txtCustBillDate;
        TextView txtCustBillUnits;
        TextView txtCustBillAmount;

        public DetailsViewHolder(View view)
        {
            txtCustId=(TextView)view.findViewById(R.id.textbillCId);
            txtCustName=(TextView)view.findViewById(R.id.textBillName);
            txtCustEmail=(TextView)view.findViewById(R.id.textbillEmail);
            txtCustGender=(TextView)view.findViewById(R.id.textBillGender);
            txtCustBillDate=(TextView)view.findViewById(R.id.textBillDate);
            txtCustBillUnits=(TextView)view.findViewById(R.id.textBillUnits);
            txtCustBillAmount=(TextView)view.findViewById(R.id.textBillAmount);
        }
    }
}
