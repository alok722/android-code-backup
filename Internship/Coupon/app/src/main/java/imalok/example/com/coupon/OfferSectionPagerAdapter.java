package imalok.example.com.coupon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class OfferSectionPagerAdapter extends FragmentPagerAdapter {

    public OfferSectionPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    //Assigning Tab fragment to respective positions
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MostUsedTab mostUsedTab = new MostUsedTab();
                return mostUsedTab;

            case 1:
                RechargeTab rechargeTab = new RechargeTab();
                return rechargeTab;

            case 2:
                FashionTab fashionTab = new FashionTab();
                return fashionTab;

            case 3:
                FoodTab foodTab = new FoodTab();
                return foodTab;

            case 4:
                ElectronicsTab electronicsTab = new ElectronicsTab();
                return electronicsTab;

            case 5:
                TravelTab travelTab = new TravelTab();
                return travelTab;

            default:
                return null;
        }
    }


    // Returning number of Tab items.
    @Override
    public int getCount() {
        return 6;
    }


    //  Assigning the name of Tab Items.
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Most Used";
            case 1:
                return "Recharge";
            case 2:
                return "Fashion";
            case 3:
                return "Food";
            case 4:
                return "Electronics";
            case 5:
                return "Travel";
            default:
                return null;
        }
    }
}
