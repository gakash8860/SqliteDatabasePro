package com.example.sqlitedatabasepro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,name,email,age;
    Button add,view,update,delete,viewall;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        myDB=new DataBaseHelper(this);
        age=findViewById(R.id.age);
        add=findViewById(R.id.add);
        view=findViewById(R.id.view);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        viewall=findViewById(R.id.viewall);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = myDB.insertData(name.getText().toString(),email.getText().toString(), age.getText().toString());

                if (result) {
                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDB.getData(id.getText().toString());

                if (c.moveToNext()) {
                    id.setText(c.getString(0));
                    name.setText(c.getString(1));
                    email.setText(c.getString(2));
                    age.setText(c.getString(3));

                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean value = myDB.updateData(id.getText().toString(), name.getText().toString(), email.getText().toString(), age.getText().toString());

                if (value) {
                    Toast.makeText(MainActivity.this, value + " Value get Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, value + " Value not get Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = myDB.deleteData(id.getText().toString());

                if (num > 0) {
                    Toast.makeText(MainActivity.this, "Data is Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data is not Deleted ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDB.viewAllData();
                StringBuffer data = new StringBuffer();
                if (c.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();

                } else {
                    while (c.moveToNext()) {
                        data.append(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n" + c.getString(3) + "\n\n");

                    }
                    Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
