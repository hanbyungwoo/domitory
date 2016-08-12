package com.tory.domi.domitory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tory.domi.domitory.Fragment.FragmentOne;
import com.tory.domi.domitory.Fragment.FragmentThree;
import com.tory.domi.domitory.Fragment.FragmentTwo;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCall();
    }

    void setCall() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment newFragment = null;

            switch (position) {
                case FRAGMENT_ONE :
                    newFragment = new FragmentOne();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("latitude", saved_latitude);
//                    bundle.putString("longitude", saved_longitude);
//                    newFragment.setArguments(bundle);
                    break;
                case FRAGMENT_TWO:
                    newFragment = new FragmentTwo();
                    break;
                case FRAGMENT_THREE:
                    newFragment = new FragmentThree();
                    break;
                default:
                    break;
            }
            return newFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);

            }
            return null;
        }


    }

}
