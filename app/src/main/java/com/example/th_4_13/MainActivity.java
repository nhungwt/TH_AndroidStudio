package com.example.th_4_13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.th_4_13.adapter.ViewPagerFragmentAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    ViewPager viewPagerContainer;
    FloatingActionButton btn_floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPagerContainer = findViewById(R.id.viewPagerContainer);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        btn_floating = findViewById(R.id.btn_floating);

//        viewPagerContainer.setOnPageChangeListener();
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), 3);
        viewPagerContainer.setAdapter(viewPagerFragmentAdapter);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_list:
                        bottomNavigation.getMenu().getItem(0).setCheckable(true);
                        viewPagerContainer.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        bottomNavigation.getMenu().getItem(1).setCheckable(true);
                        viewPagerContainer.setCurrentItem(1);
                        return true;
                    case R.id.navigation_profile:
                        bottomNavigation.getMenu().getItem(2).setCheckable(true);
                        viewPagerContainer.setCurrentItem(2);
                        return true;
                    default:
                        viewPagerContainer.setCurrentItem(0);
                        return true;
                }
            }
        });

        btn_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}