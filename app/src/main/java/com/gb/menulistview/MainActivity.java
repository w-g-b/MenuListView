package com.gb.menulistview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ListView listView = findViewById(R.id.listView);
        //使用系统自带的MenuAdapter，没有Icon
//        MenuBuilder menuBuilder = new MenuBuilder(this);
//        new MenuInflater(this).inflate(R.menu.me_btn_menu, menuBuilder);
//        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, LayoutInflater.from(this), false, R.layout.abc_list_menu_item_layout);

//        MenuAdapter menuAdapter = new MenuAdapter(this, R.menu.me_btn_menu);
//        listView.setAdapter(menuAdapter);
    }
}
