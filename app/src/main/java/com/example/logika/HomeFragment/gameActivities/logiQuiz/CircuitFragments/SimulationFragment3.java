package com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.logika.R;


public class SimulationFragment3 extends Fragment {

    private ImageView imageViewA;
    private ImageView imageViewB;
    private ToggleButton toggleButtonA;
    private ToggleButton toggleButtonB;
    private ImageView imageViewOutput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation3, container, false);

        configureToggleInputButtons(view);

        return view;
    }

    public void configureToggleInputButtons(View view){
        imageViewA = view.findViewById(R.id.imageViewA);
        imageViewB = view.findViewById(R.id.imageViewB);
        toggleButtonA = view.findViewById(R.id.toggleButtonA);
        toggleButtonB = view.findViewById(R.id.toggleButtonB);
        imageViewOutput = view.findViewById(R.id.imageViewOutput);

        toggleButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButtonA.isChecked()){
                    imageViewA.setImageResource(R.drawable.logiquiz_input_button_clicked);
                } else {
                    imageViewA.setImageResource(R.drawable.logiquiz_input_button_unclicked);
                }
                updateOutputDisplay();
            }
        });

        toggleButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButtonB.isChecked()){
                    imageViewB.setImageResource(R.drawable.logiquiz_input_button_clicked);
                } else {
                    imageViewB.setImageResource(R.drawable.logiquiz_input_button_unclicked);
                }
                updateOutputDisplay();
            }
        });

    }

    private void updateOutputDisplay(){
        boolean isToggleButtonAChecked = toggleButtonA.isChecked();
        boolean isToggleButtonBChecked = toggleButtonB.isChecked();

        if (!isToggleButtonAChecked && !isToggleButtonBChecked){
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_off);
        } else {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        }
    }

}