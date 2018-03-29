package com.mrezanasirloo.setfragmentviewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag("FragmentViewPager") == null)
            fm.beginTransaction()
                    .replace(R.id.container, new FragmentViewPager(), "FragmentViewPager")
                    .commit();
    }
}
