package com.tom.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tom.contact.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SQLiteDatabase db;
    String create_table="Create table if not exists contact(FirstName text,LastName text,phone text)";
    String drop_table="Drop table contact";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=openOrCreateDatabase("Test.db",MODE_PRIVATE,null);
        db.execSQL(create_table);
    }
    public void save(View v){
        String FirstName=binding.edtFirstName.getText().toString();
        String LastName=binding.edtLastName.getText().toString();
        String phone=binding.edtPhone.getText().toString();
        ContentValues values=new ContentValues();
        values.put("FirstName",FirstName);
        values.put("LastName",LastName);
        values.put("phone",phone);
        try {
            db.insert("contact",null,values);
            new AlertDialog.Builder(this)
                 .setTitle("新增")
                 .setMessage("新增聯絡人成功")
                 .setPositiveButton("確定",null)
                 .show();
        }
        catch (Exception ex){
            new AlertDialog.Builder(this)
                    .setTitle("失敗")
                    .setMessage(ex.getMessage())
                    .setPositiveButton("確定",null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_Add:
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_List:
                Intent intent1=new Intent(MainActivity.this,ContactListActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        db.execSQL(drop_table);
        super.onDestroy();

    }
}
