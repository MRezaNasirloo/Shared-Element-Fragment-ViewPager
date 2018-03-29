package com.mrezanasirloo.setfragmentviewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContainer extends Fragment {


    public FragmentContainer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentByTag("GridFragment") == null)
            fm.beginTransaction()
                    .replace(R.id.container_fragment, new GridFragment(), "GridFragment")
                    .commit();
    }
}
