package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText ed_filename=(EditText)findViewById(R.id.editText_TenFile);
        final EditText ed_content=(EditText)findViewById(R.id.editText_Content);
        Button nhapmoi=(Button)findViewById(R.id.button_Nhap);
        nhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_filename.setText("");
                ed_content.setText("");
            }
        });
        final ArrayList<String> filenameList=new ArrayList<>();
        filenameList.add("Hello");

        Spinner sp_filename=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,filenameList);
        sp_filename.setAdapter(adapter);
        sp_filename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                ed_filename.setText(filenameList.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button bt_luu = (Button)findViewById(R.id.button_Luu);
        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = ed_filename.getText().toString();
                filenameList.add(filename);
                try {
                    FileOutputStream fo = openFileOutput(filename, Context.MODE_PRIVATE);
                    fo.write(ed_content.getText().toString().getBytes());
                    fo.close();
                } catch(Exception e){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button bt_mo=(Button)findViewById(R.id.button_Mo);
        bt_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = ed_filename.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try {
                    FileInputStream fin = openFileInput(filename);
                    BufferedReader br  = new BufferedReader(new InputStreamReader(fin));
                    while ((line=br.readLine())!= null)
                        buffer.append(line).append("\n");
                    ed_content.setText(buffer.toString());
                } catch (Exception e){

                }
            }
        });
    }
}
