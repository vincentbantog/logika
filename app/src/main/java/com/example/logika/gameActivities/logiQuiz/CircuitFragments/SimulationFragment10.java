package com.example.logika.gameActivities.logiQuiz.CircuitFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.logika.R;


public class SimulationFragment10 extends Fragment {

    private ImageView imageViewA;
    private ImageView imageViewB;
    private ImageView imageViewC;
    private ImageView imageViewD;
    private ToggleButton toggleButtonA;
    private ToggleButton toggleButtonB;
    private ToggleButton toggleButtonC;
    private ToggleButton toggleButtonD;
    private ImageView imageViewOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simulation10, container, false);

        configureToggleInputButtons(view);

        return view;
    }

    public void configureToggleInputButtons(View view){
        imageViewA = view.findViewById(R.id.imageViewA);
        imageViewB = view.findViewById(R.id.imageViewB);
        imageViewC = view.findViewById(R.id.imageViewC);
        imageViewD = view.findViewById(R.id.imageViewD);
        toggleButtonA = view.findViewById(R.id.toggleButtonA);
        toggleButtonB = view.findViewById(R.id.toggleButtonB);
        toggleButtonC = view.findViewById(R.id.toggleButtonC);
        toggleButtonD = view.findViewById(R.id.toggleButtonD);
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

        toggleButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButtonC.isChecked()){
                    imageViewC.setImageResource(R.drawable.logiquiz_input_button_clicked);
                } else {
                    imageViewC.setImageResource(R.drawable.logiquiz_input_button_unclicked);
                }
                updateOutputDisplay();
            }
        });

        toggleButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButtonD.isChecked()){
                    imageViewD.setImageResource(R.drawable.logiquiz_input_button_clicked);
                } else {
                    imageViewD.setImageResource(R.drawable.logiquiz_input_button_unclicked);
                }
                updateOutputDisplay();
            }
        });

    }

    private void updateOutputDisplay() {
        boolean isToggleButtonAChecked = toggleButtonA.isChecked();
        boolean isToggleButtonBChecked = toggleButtonB.isChecked();
        boolean isToggleButtonCChecked = toggleButtonC.isChecked();
        boolean isToggleButtonDChecked = toggleButtonD.isChecked();

        if (!isToggleButtonAChecked && !isToggleButtonBChecked && isToggleButtonCChecked && isToggleButtonDChecked){
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (!isToggleButtonAChecked && isToggleButtonBChecked && isToggleButtonCChecked && isToggleButtonDChecked){
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (isToggleButtonAChecked && !isToggleButtonBChecked && isToggleButtonCChecked && isToggleButtonDChecked) {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (isToggleButtonAChecked && isToggleButtonBChecked && !isToggleButtonCChecked && !isToggleButtonDChecked) {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (isToggleButtonAChecked && isToggleButtonBChecked && !isToggleButtonCChecked && isToggleButtonDChecked){
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (isToggleButtonAChecked && isToggleButtonBChecked && isToggleButtonCChecked && !isToggleButtonDChecked) {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else if (isToggleButtonAChecked && isToggleButtonBChecked && isToggleButtonCChecked && isToggleButtonDChecked) {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_on);
        } else {
            imageViewOutput.setImageResource(R.drawable.logiquiz_simulation_output_display_off);
        }
    }
}