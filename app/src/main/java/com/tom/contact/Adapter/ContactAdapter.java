package com.tom.contact.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tom.contact.Data.Contact;
import com.tom.contact.R;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Contact> contactList;
    public ContactAdapter(Context ctx,List<Contact> contacts){
        this.context=ctx;
        this.contactList=contacts;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return this.contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.layout_contact_listitem,null);
        TextView txvFirstname=convertView.findViewById(R.id.txvFirstName);
        TextView txvLastName=convertView.findViewById(R.id.txvLastName);
        TextView txvPhone=convertView.findViewById(R.id.txvPhone);
        txvFirstname.setText(this.contactList.get(position).getFirstName());
        txvLastName.setText(this.contactList.get(position).getLastName());
        txvPhone.setText(this.contactList.get(position).getPhone());
        return convertView;
    }
}
