package com.gb.menulistview;

import android.os.Bundle;
import android.view.View;

import com.gb.viewgroup.MenuAdapter;
import com.gb.viewgroup.MenuListView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        MenuListView menuListView = findViewById(R.id.custom_menu_list_view);
        MenuAdapter menuAdapter = new MenuAdapter(this, R.menu.me_menu,
                R.layout.custom_menu_item, new CustomMenuViewHolder.MenuViewHolderFactory());
        menuListView.setAdapter(menuAdapter);
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
