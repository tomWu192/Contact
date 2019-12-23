package com.tom.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tom.contact.Adapter.ContactAdapter;
import com.tom.contact.Data.Contact;
import com.tom.contact.databinding.ActivityContactListBinding;
import com.tom.contact.databinding.ActivityMainBinding;

import java.util.LinkedList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ActivityContactListBinding binding;
    SQLiteDatabase db;
    String query_String="Select * from contact";
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactList = new LinkedList<Contact>();
        binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=openOrCreateDatabase("Test.db",MODE_PRIVATE,null);
        Cursor cursor=db.rawQuery(query_String,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++){
            Contact ctx=new Contact();
            ctx.setFirstName(cursor.getString(0));
            ctx.setLastName(cursor.getString(1));
            ctx.setPhone(cursor.getString(2));
            contactList.add(ctx);
            cursor.moveToNext();

        }
        ContactAdapter adapter=new ContactAdapter(this,contactList);
        binding.contactList.setAdapter(adapter);
        binding.btnBack.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
