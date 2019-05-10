package com.tts.www.tsg_disaster_response_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tts.www.tsg_disaster_response_app.R;

public class DemoPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private Context context;
    private int[] imageArray;
    private View lastView;
    private final CallbackDemoPagerAdapter callbackDemoPagerAdapter;

    public DemoPagerAdapter(Context context, int[] imageArray, View lastView, CallbackDemoPagerAdapter callbackDemoPagerAdapter) {
        this.context = context;
        this.imageArray = imageArray;
        this.lastView = lastView;
        this.callbackDemoPagerAdapter = callbackDemoPagerAdapter;
    }

    @Override
    public void onClick(View v) {
        callbackDemoPagerAdapter.skipDemo();
    }

    public interface CallbackDemoPagerAdapter {
        void skipDemo();
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.demo_image_container,null,false);
        ImageView ivDemo = view.findViewById(R.id.ivDemo);
        ivDemo.setImageResource(imageArray[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
