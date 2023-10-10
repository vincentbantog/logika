package com.example.logika.gameActivities.logiQuiz.CircuitFragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.logika.R;

public class SimulationFragment1 extends Fragment {

    private ImageButton toggleInputButton1;
    private ImageButton toggleInputButton2;
    private ImageView outputDisplay;

    private boolean isButton1Pressed = false;
    private boolean isButton2Pressed = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation1, container, false);

        configureToggleInputButtons(view);
        updateOutputDisplay();

        return view;
    }

    public void configureToggleInputButtons(View view){
        toggleInputButton1 = view.findViewById(R.id.toggleInputButton1);
        toggleInputButton2 = view.findViewById(R.id.toggleInputButton2);
        outputDisplay = view.findViewById(R.id.outputDisplay);

        toggleInputButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isButton1Pressed) {
                    toggleInputButton1.setImageResource(R.drawable.simulation_button_on_state_a);
                } else {
                    toggleInputButton1.setImageResource(R.drawable.simulation_button_off_state_a);
                }

                isButton1Pressed = !isButton1Pressed;
                updateOutputDisplay();
            }
        });
        toggleInputButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton2Pressed) {
                    toggleInputButton2.setImageResource(R.drawable.simulation_button_on_state_b);
                } else {
                    toggleInputButton2.setImageResource(R.drawable.simulation_button_off_state_b);
                }
                isButton2Pressed = !isButton2Pressed;
                updateOutputDisplay();
            }
        });

        updateOutputDisplay();

    }

    private void updateOutputDisplay() {
        if (!isButton1Pressed && !isButton2Pressed) {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_off);
        } else {
            outputDisplay.setImageResource(R.drawable.simulation_output_display_on);
        }
    }


}