package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by mparm920 on 7/13/14.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
