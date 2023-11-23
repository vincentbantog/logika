package com.example.logika.startPortion.mainMenuFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.logika.R;



public class SettingsFragment extends Fragment {

    private ToggleButton toggleBtnMute;
    private ImageView imageViewMute;
    private Button btnExit;

    // Define an interface
    public interface OnExitListener {
        void onExitRequested();
    }

    private OnExitListener exitListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnExitListener) {
            exitListener = (OnExitListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnExitListener");
        }
    }

    // In some action (button click or other event), call this method
    private void requestExit() {
        if (exitListener != null) {
            exitListener.onExitRequested();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        toggleBtnMute = view.findViewById(R.id.toggleBtnMute);
        imageViewMute = view.findViewById(R.id.imageViewMute);
        btnExit = view.findViewById(R.id.btnExit);

        configureMuteToggleButton();
        configureExitButton();

        return view;
    }

    private void configureMuteToggleButton(){
        toggleBtnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleBtnMute.isChecked()){
                    imageViewMute.setImageResource(R.drawable.home_017);
                } else {
                    imageViewMute.setImageResource(R.drawable.home_018);
                }
            }
        });
    }

    private void configureExitButton(){

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestExit();
            }
        });
    }
}