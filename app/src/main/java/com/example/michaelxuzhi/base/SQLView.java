package com.example.michaelxuzhi.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SQLView extends Activity {


    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);


        tv = findViewById(R.id.tvSQLInfo);

        HotOrNot info =new HotOrNot(this);
        info.open();
        //从数据库中提取数据
        String data = info.getData();
        info.close();
        tv.setText(data);
    }
}
