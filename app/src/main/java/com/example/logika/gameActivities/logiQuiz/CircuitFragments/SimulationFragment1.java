package com.example.logika.gameActivities.logiQuiz.CircuitFragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.logika.R;

public class SimulationFragment1 extends Fragment {

    private ImageView toggleImageButton1;
    private ImageView toggleImageButton2;
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;
    private ImageView outputDisplay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation1, container, false);

        configureToggleInputButtons(view);

        return view;
    }

    public void configureToggleInputButtons(View view) {
        toggleImageButton1 = view.findViewById(R.id.toggleImageButton1);
        toggleImageButton2 = view.findViewById(R.id.toggleImageButton2);
        toggleButton1 = view.findViewById(R.id.toggleButton1);
        toggleButton2 = view.findViewById(R.id.toggleButton2);
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
    }

    private void updateOutputDisplay(){
        boolean isToggleButton1Checked = toggleButton1.isChecked();
        boolean isToggleButton2Checked = toggleButton2.isChecked();

        if (isToggleButton1Checked && isToggleButton2Checked) {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_off);
        } else {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_on);
        }
    }
}