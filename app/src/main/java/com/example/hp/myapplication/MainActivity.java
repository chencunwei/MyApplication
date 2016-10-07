package com.example.hp.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyFragmentPageAdapter mAdapter;

    private int width;
    private float x;
    private final int MARGIN = 200;

    /**
     * 判断是否点击的是左右两边漏出来的上一个和下一个fragment的边缘
     */
    private boolean isSideClick(float x) {
        return x < MARGIN || x > width - MARGIN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        // 设置为负值两边的间隔距离才会变小
        mViewPager.setPageMargin(-10);

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new MyFragmentPageAdapter(fm);
        RelativeLayout viewById = (RelativeLayout) findViewById(R.id.container_pager);
        if (viewById != null) {
            viewById.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent ev) {
                    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                        if (isSideClick(ev.getX())) {
                            x = ev.getX();
                        }
                    } else if (ev.getAction() == MotionEvent.ACTION_UP) {
                        if (isSideClick(ev.getX())) {
                            int currentItem = mViewPager.getCurrentItem();
                            if (ev.getX() < MARGIN) {
                                mViewPager.setCurrentItem(currentItem - 1, true);
                            } else if (ev.getX() > width - MARGIN) {
                                mViewPager.setCurrentItem(currentItem + 1, true);
                            }
                        }
                    }
                    return true;
                }
            });

        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
    }

}
