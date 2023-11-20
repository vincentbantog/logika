package com.example.logika.startPortion.mainMenuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.ReviewerFragment.reviewerActivities.BooleanAlgebraReviewer1;
import com.example.logika.ReviewerFragment.reviewerActivities.DigitalArithmeticReviewer1;
import com.example.logika.ReviewerFragment.reviewerActivities.IntroReviewer1;
import com.example.logika.ReviewerFragment.reviewerActivities.LogicGatesReviewer1;


public class StatsFragment extends Fragment {

    private Button btnIntro;
    private Button btnDigitalArithmetic;
    private Button btnLogicGates;
    private Button btnBooleanAlgebra;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        initializeViewElements(view);

        configureButtons(view);

        return view;
    }

    private void initializeViewElements(View view){
        btnIntro = view.findViewById(R.id.btnIntro);
        btnDigitalArithmetic = view.findViewById(R.id.btnDigitalArithmetic);
        btnLogicGates = view.findViewById(R.id.btnLogicGates);
        btnBooleanAlgebra = view.findViewById(R.id.btnBooleanAlgebra);
    }

    private void configureButtons(View view){
        btnIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroReviewer1.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnDigitalArithmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DigitalArithmeticReviewer1.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogicGates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogicGatesReviewer1.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnBooleanAlgebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BooleanAlgebraReviewer1.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


    }
}
