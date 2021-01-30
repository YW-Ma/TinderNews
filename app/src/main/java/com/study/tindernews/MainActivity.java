package com.study.tindernews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view); // 找到bottom
        // 找到fragment host     host fragment --> activity_main/host_fragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        // 把fragment的controller取出来，它可以告诉我们当前显示的是谁、也可以控制跳转
        navController = navHostFragment.getNavController();
        // 1. link navView and controller
        NavigationUI.setupWithNavController(navView, navController);
        // 2. link action bar and controller：action bar会显示名字、负责返回
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    // 支持action bar的回退到home的操作
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}
