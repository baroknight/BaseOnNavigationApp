package com.example.mynavigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mynavigation.R;
import com.example.mynavigation.fragment.ftvdemo_fragment.PictureFragment;
import com.example.mynavigation.fragment.ftvdemo_fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class FTVDemoFragment extends SupportFragment {
    TabLayout tlDemo;
    ViewPager vpDemo;
//    在FTCVDemo中通过TabLayout跳转的Fragment的集合
    ArrayList<SupportFragment> list;

    // 对应的Fragment的标题
    List<String> tabTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fra_ftvdemo,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 初始化视图
         * */
        initView(view);

        /**
         * 设置数据及相关数据的初始化操作
         * */
        setData();

        /**
         * 为相关的组件适配参数
         * */
        setOther();

    }

    private void setOther() {
//        这里要设置子类的Fragment管理器，不然会出现一些bug
        vpDemo.setAdapter(new VPAdapter(getChildFragmentManager()));
        tlDemo.setupWithViewPager(vpDemo);
    }

    private void setData() {
//        添加要跳转的页面
        list = new ArrayList<>();
        list.add(new VideoFragment());
        list.add(new PictureFragment());

//        添加要跳转页面的标题
        tabTitle = new ArrayList<>();
        tabTitle.add("Video");
        tabTitle.add("picture");
    }

    private void initView(View view) {
        tlDemo = view.findViewById(R.id.tl_demo);
        vpDemo = view.findViewById(R.id.vp_demo);
    }




    /**
     * 设置ViewPager的适配器VPAdapter
     * */
    class VPAdapter extends FragmentPagerAdapter{


        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

//        返回要跳转的页面
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

//        再添加一个设置标题的方法

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }
}
