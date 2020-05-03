package com.example.mynavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mynavigation.fragment.AmapFragment;
import com.example.mynavigation.fragment.FTVDemoFragment;
import com.example.mynavigation.fragment.HomeFragment;
import com.example.mynavigation.fragment.VisualFragment;
import com.google.android.material.navigation.NavigationView;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    NavigationView ngv;
    TextView frgmentTitle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
        * 初始化视图
        * */
        initView();

        /**
         * 加载容器以及初始化的第一个Fragment
         * */
        loadRootFragment(R.id.container, new HomeFragment());
        setFrgmentTitle("首页");

        /**
         * 添加左上角的侧滑导航栏t图标，也可以不添加，通过侧滑的方式打开
         * */
        openStart();

        /**
         * 设置Fragment跳转监听
         * */

        navClick();


    }

    private void navClick() {
        ngv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home_fragment:
                        setFrgmentTitle("首页");
                        startWithPop(new HomeFragment());
                        break;
                    case R.id.menu_ftv_fragment:
                        setFrgmentTitle("视频和图片");
                        startWithPop(new FTVDemoFragment());
                        break;
                    case R.id.menu_visual_fragment:
                        setFrgmentTitle("可视化图");
                        startWithPop(new VisualFragment());
                        break;
                    case R.id.menu_amap_fragment:
                        setFrgmentTitle("高德地图");
                        startWithPop(new AmapFragment());
                        break;
                }

//                每次点击一个项目后关闭导航栏
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }


    private void setFrgmentTitle(String frgTitle) {
        frgmentTitle.setText(frgTitle);
    }

    private void openStart() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.nav_drawer_open,
                R.string.nav_drawer_close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    private void initView() {
        ngv = findViewById(R.id.ngv_navigation);
        frgmentTitle = findViewById(R.id.tv_title);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
    }
}
