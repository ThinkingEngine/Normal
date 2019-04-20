package toolkit.cala.normal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * package name:toolkit.cala.normal.adapters
 * create:cala
 * date:2019/4/20
 * description:
 */
public class FmPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList;

    public FmPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);

        mFragmentList = fragments;
    }

    @Override
    public int getCount() {
        if(mFragmentList!=null){
            return mFragmentList.size();
        }else{
            return 0;
        }
    }

    @Override
    public Fragment getItem(int pos) {
        if(pos < mFragmentList.size()){
            return mFragmentList.get(pos);
        }
        return null;
    }
}
