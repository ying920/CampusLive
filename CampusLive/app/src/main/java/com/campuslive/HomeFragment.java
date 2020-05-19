package com.campuslive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private ViewFlipper flipper_home_fragment;
    private int[] images = {R.drawable.advertisement_1, R.drawable.advertisement_2, R.drawable.advertisement_3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        flipper_home_fragment = (ViewFlipper)view.findViewById(R.id.flipper_home_fragment);

        for (int image: images) {
            flipperImages(image);
        }
        
        return view;
    }

    private void flipperImages(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        flipper_home_fragment.addView(imageView);
        flipper_home_fragment.setFlipInterval(3000);
        flipper_home_fragment.setAutoStart(true);

        flipper_home_fragment.setInAnimation(getContext(), android.R.anim.slide_in_left);
        flipper_home_fragment.setOutAnimation(getContext(), android.R.anim.slide_out_right);

    }
}
