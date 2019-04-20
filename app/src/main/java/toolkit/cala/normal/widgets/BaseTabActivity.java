package toolkit.cala.normal.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;

import toolkit.cala.normal.BaseActivity;
import toolkit.cala.normal.R;
import toolkit.cala.normal.adapters.FmPagerAdapter;

/**
 * package name:toolkit.cala.normal.widgets
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BaseTabActivity extends BaseActivity {

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private ArrayList<Fragment> fragments;

    private String[] titles;


    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract ArrayList<Fragment> getFragments();

    protected abstract String[] getTitles();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        fragments = getFragments();
        titles = getTitles();

        initView();
        initViews();


    }


    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(viewPager);
        FmPagerAdapter adapter = new FmPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.getTabAt(i).setText(titles[i]);
        }

    }
}
