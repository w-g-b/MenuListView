package com.gb.menulistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ListView listView = findViewById(R.id.listView);
        //使用系统自带的MenuAdapter，没有Icon
//        MenuBuilder menuBuilder = new MenuBuilder(this);
//        new MenuInflater(this).inflate(R.menu.me_menu, menuBuilder);
//        MenuAdapter menuAdapter = new MenuAdapter(menuBuilder, LayoutInflater.from(this), false, R.layout.abc_list_menu_item_layout);

//        MenuAdapter menuAdapter = new MenuAdapter(this, R.menu.me_menu);
//        listView.setAdapter(menuAdapter);
        findViewById(R.id.custom_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
            }
        });
    }

    public void helloWorld(View view) {
        System.out.println(view);
        System.out.println("HelloWorld");
    }

    public void hello(View view) {
        System.out.println("Hello");
    }

    public void test(View view) {
        System.out.println("test");
        System.out.println("=====" + view + "=======");
    }
}
