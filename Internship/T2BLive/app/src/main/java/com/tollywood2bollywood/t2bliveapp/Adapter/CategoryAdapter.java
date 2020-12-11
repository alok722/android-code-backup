package com.tollywood2bollywood.t2bliveapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tollywood2bollywood.t2bliveapp.AllTabsFragment;
import com.tollywood2bollywood.t2bliveapp.CategoryTabFragment;
import com.tollywood2bollywood.t2bliveapp.YoutubeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAI JALARAM on 05-02-2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter
{
    int mNumOfTabs,catIdsize=0;
    Fragment mFragment = null;
    List<String> catId = new ArrayList<String>();
    final List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public CategoryAdapter(FragmentManager fm, int mNumOfTabs,List<String> catId,int catIdsize) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
        this.catId = catId;
        this.catIdsize = catIdsize;
    }
    @Override
    public Fragment getItem(int position)
    {
        for (int i = 0; i < mNumOfTabs; i++) {
            if(position == mNumOfTabs-1){
                mFragment = CategoryTabFragment.newInstance(position, catId.get(position));
            }else if(position == mNumOfTabs-2){
                mFragment = YoutubeFragment.newInstance(position, catId.get(position));
            }else{
                mFragment = AllTabsFragment.newInstance(position,catId.get(position));
            }
            break;
        }
        return mFragment;
    }
    @Override
    public int getCount() {
        return catId.size();
    }
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return catId.get(position);
    }

}
