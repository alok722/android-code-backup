package imalok.example.com.coupon;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

public class CouponFragment extends Fragment {

    ViewPager viewPager;
    CouponSliderAdapter adapter;

    private ViewPager mViewPager;
    private OfferSectionPagerAdapter mOfferSectionPagerAdapter;
    private TabLayout mTabLayout;


    public CouponFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        viewPager = (ViewPager)view.findViewById(R.id.coupon_slider);
        adapter = new CouponSliderAdapter(getActivity());
        viewPager.setAdapter(adapter);

        //setting auto-slide time
        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
        };
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 4000, 4000);

        //RecyclerView String and Image for Popular Stores

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.popular_stores_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new HorizontalAdapter(new int[]{R.drawable.paytm,
                                                                    R.drawable.paytm,
                                                                        R.drawable.paytm,R.drawable.paytm,R.drawable.paytm,
                R.drawable.paytm,R.drawable.paytm,R.drawable.paytm,R.drawable.paytm,R.drawable.paytm,R.drawable.paytm}));


        mViewPager = (ViewPager)view.findViewById(R.id.offer_tab_pager);
        mOfferSectionPagerAdapter = new OfferSectionPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mOfferSectionPagerAdapter);
        mTabLayout = (TabLayout)view.findViewById(R.id.offers_tab);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

}
