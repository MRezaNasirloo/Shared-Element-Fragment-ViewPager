package com.mrezanasirloo.setfragmentviewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Displays a grid of pictures
 *
 * @author bherbst
 */
@TargetApi(Build.VERSION_CODES.M)
public class GridFragment extends Fragment implements KittenClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    private static final String TAG = GridFragment.class.getSimpleName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated() called");
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new KittenGridAdapter(6, this));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onKittenClicked(KittenViewHolder holder, int position) {

        int kittenNumber = (position % 6) + 1;

        DetailsFragment fragmentDetails = DetailsFragment.newInstance(kittenNumber);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionSet transition = new TransitionSet()
                    .setOrdering(TransitionSet.ORDERING_TOGETHER)
                    .addTransition(new ChangeTransform())
                    .addTransition(new ChangeBounds())
                    .addTransition(new ChangeClipBounds())
                    .addTransition(new ChangeImageTransform());

            transition.setDuration(300);

            fragmentDetails.setSharedElementEnterTransition(transition);

            TransitionSet transitionSet = new TransitionSet().setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
                    .addTransition(new Slide(Gravity.BOTTOM))
                    .addTransition(new Fade(Fade.IN));
            transitionSet.setDuration(300);


            fragmentDetails.setEnterTransition(transitionSet);

            fragmentDetails.setExitTransition(transitionSet);
            this.setExitTransition(new Explode());
            this.setReenterTransition(new Explode().setDuration(300));
            this.setEnterTransition(new Explode().setDuration(300));
        }

        String name = "kittenImage" + position;
        Log.e(TAG, name);
        getFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.image, name)
                .replace(R.id.container_fragment, fragmentDetails, "tag")
                .addToBackStack(null)
                .commit();
    }
}
