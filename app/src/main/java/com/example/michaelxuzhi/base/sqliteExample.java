package com.example.michaelxuzhi.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class sqliteExample extends Activity implements View.OnClickListener {
    Button sqlUpdate, sqlView;
    EditText sqlName,sqlHotness;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlexample);
        sqlUpdate= findViewById(R.id.bSQLUpdate);
        sqlView= findViewById(R.id.bSQLView);
        sqlName= findViewById(R.id.etSQLName);
        sqlHotness= findViewById(R.id.etSQLHotness);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
    }
    @Override
    public void onClick(View arg0){
        switch (arg0.getId()){
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(sqliteExample.this);
                    entry.open();

                    entry.createEntry(name, hotness);

                    entry.close();
                }catch (Exception e){
                    didItWork = false;
                    String error = e.toString();
                    Dialog d =new Dialog(this);
                    d.setTitle("OH!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }finally {
                    if(didItWork){
                        Dialog d =new Dialog(this);
                        d.setTitle("标题");
                        TextView tv = new TextView(this);
                        tv.setText("插入成功！");
                        d.setContentView(tv);
                        d.show();
                    }
                         }

                break;
            case R.id.bSQLView:
                Intent i=new Intent(sqliteExample.this,SQLView.class);
                startActivity(i);
                break;
        }
    }
}
