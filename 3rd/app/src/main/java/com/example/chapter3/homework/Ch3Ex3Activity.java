package com.example.chapter3.homework;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */
public class Ch3Ex3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);
        ViewPager pager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Bundle data = new Bundle();
                if (i == 0)
                    data.putStringArray("data", new String[]{
                            "张三",
                            "李四",
                            "王二麻子",
                            "赵五"
                    });
                else
                    data.putStringArray("data", new String[]{
                            "我的朋友很少",
                            "只有两个"
                    });
                PlaceholderFragment toReturn = new PlaceholderFragment();
                toReturn.setArguments(data);
                return toReturn;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) return "我的好友";
                else return "好友列表";
            }
        });
        tabLayout.setupWithViewPager(pager);
    }
}
