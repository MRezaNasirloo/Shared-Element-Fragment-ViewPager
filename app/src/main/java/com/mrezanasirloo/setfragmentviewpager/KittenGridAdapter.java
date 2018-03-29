package com.mrezanasirloo.setfragmentviewpager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Adapts Views containing kittens to RecyclerView cells
 *
 * @author bherbst
 */
public class KittenGridAdapter extends RecyclerView.Adapter<KittenViewHolder> {
    private final int mSize;
    private final KittenClickListener mListener;

    /**
     * Constructor
     *
     * @param size     The number of kittens to show
     * @param listener A listener for kitten click events
     */
    public KittenGridAdapter(int size, KittenClickListener listener) {
        mSize = size;
        mListener = listener;
    }

    @Override
    public KittenViewHolder onCreateViewHolder(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View cell = inflater.inflate(R.layout.grid_item, container, false);

        return new KittenViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(final KittenViewHolder viewHolder, final int position) {
        switch (position % 6) {
            case 0:
                viewHolder.image.setImageResource(R.drawable.placekitten_1);
                break;
            case 1:
                viewHolder.image.setImageResource(R.drawable.placekitten_2);
                break;
            case 2:
                viewHolder.image.setImageResource(R.drawable.placekitten_3);
                break;
            case 3:
                viewHolder.image.setImageResource(R.drawable.placekitten_4);
                break;
            case 4:
                viewHolder.image.setImageResource(R.drawable.placekitten_5);
                break;
            case 5:
                viewHolder.image.setImageResource(R.drawable.placekitten_6);
                break;
        }

        String transitionName = "kittenImage" + position;
        Log.e(TAG, transitionName);
        viewHolder.image.setTransitionName(transitionName);

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onKittenClicked(viewHolder, position);
            }
        });
    }

    private static final String TAG = KittenGridAdapter.class.getSimpleName();

    @Override
    public int getItemCount() {
        return mSize;
    }
}
