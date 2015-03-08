package com.parmelee.qgym;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by mparm920 on 3/7/15
 * This will create the main frame for most fragements
 */
public class MainActivity extends Activity {
    FragmentManager fm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fm = getFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        //ChooseMeetFragment chooseMeet = new ChooseMeetFragment();
        //ft.add(R.layout.activity_main, chooseMeet);
        //ft.commit();
    }
}