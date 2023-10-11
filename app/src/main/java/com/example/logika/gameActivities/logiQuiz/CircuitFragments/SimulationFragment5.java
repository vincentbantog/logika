package com.example.logika.gameActivities.logiQuiz.CircuitFragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.logika.R;


public class SimulationFragment5 extends Fragment {

    private ImageView toggleImageButton1;
    private ImageView toggleImageButton2;
    private ImageView toggleImageButton3;
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ToggleButton toggleButton3;
    private ImageView outputDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation5, container, false);

        configureToggleInputButtons(view);

        return view;
    }

    public void configureToggleInputButtons(View view){
        toggleImageButton1 = view.findViewById(R.id.toggleImageButton1);
        toggleImageButton2 = view.findViewById(R.id.toggleImageButton2);
        toggleImageButton3 = view.findViewById(R.id.toggleImageButton3);
        toggleButton1 = view.findViewById(R.id.toggleButton1);
        toggleButton2 = view.findViewById(R.id.toggleButton2);
        toggleButton3 = view.findViewById(R.id.toggleButton3);
        outputDisplay = view.findViewById(R.id.outputDisplay);

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton1.isChecked()){
                    toggleImageButton1.setImageResource(R.drawable.simulation_button_on_state_a);
                } else {
                    toggleImageButton1.setImageResource(R.drawable.simulation_button_off_state_a);
                }
                updateOutputDisplay();
            }
        });

        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton2.isChecked()){
                    toggleImageButton2.setImageResource(R.drawable.simulation_button_on_state_b);
                } else {
                    toggleImageButton2.setImageResource(R.drawable.simulation_button_off_state_b);
                }
                updateOutputDisplay();
            }
        });

        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton3.isChecked()){
                    toggleImageButton3.setImageResource(R.drawable.simulation_button_on_state_c);
                } else {
                    toggleImageButton3.setImageResource(R.drawable.simulation_button_off_state_c);
                }
                updateOutputDisplay();
            }
        });
    }

    private void updateOutputDisplay(){
        boolean isToggleButton1Checked = toggleButton1.isChecked();
        boolean isToggleButton2Checked = toggleButton2.isChecked();
        boolean isToggleButton3Checked = toggleButton3.isChecked();

        if (!isToggleButton1Checked && !isToggleButton2Checked && isToggleButton3Checked) {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_off);
        } else if (!isToggleButton1Checked && isToggleButton2Checked && isToggleButton3Checked) {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_off);
        } else if (isToggleButton1Checked && !isToggleButton2Checked && isToggleButton3Checked) {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_off);
        } else {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_on);
        }


    }

}