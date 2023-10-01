package com.example.logika.mainMenuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.logika.R;
import com.example.logika.gameActivities.MultipleChoice;
import com.example.logika.gameActivities.TrueOrFalse;


public class HomeFragment extends Fragment {


    private Button btnMultipleChoice;
    private Button btnTrueOrFalse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        configureBtnMultipleChoice(view);
        configureBtnTrueOrFalse(view);


        return view;
    }

    public void configureBtnMultipleChoice(View view){
        btnMultipleChoice = view.findViewById(R.id.btnMultipleChoice);
        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMultipleChoice = new Intent(getActivity(), MultipleChoice.class);
                startActivity(intentMultipleChoice);
            }
        });
    }

    public void configureBtnTrueOrFalse(View view){
        btnTrueOrFalse = view.findViewById(R.id.btnTrueOrFalse);
        btnTrueOrFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrueOrFalse = new Intent(getActivity(), TrueOrFalse.class);
                startActivity(intentTrueOrFalse);
            }
        });
    }


}