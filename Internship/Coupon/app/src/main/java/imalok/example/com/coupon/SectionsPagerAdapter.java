package imalok.example.com.coupon;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Assigning Tab fragment to respective positions
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                CouponFragment couponFragment = new CouponFragment();
                return couponFragment;

            case 1:
                DealFragment dealFragment = new DealFragment();
                return dealFragment;
            default:
                return null;
        }
    }


    // Returning number of Tab items.
    @Override
    public int getCount() {
        return 2;
    }


    //  Assigning the name of Tab Items.
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Coupons";
            case 1:
                return "Deals";
            default:
                return null;
        }
    }
}

