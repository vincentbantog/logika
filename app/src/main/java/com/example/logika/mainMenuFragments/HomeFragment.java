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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private Button btnMultipleChoice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        configureBtnMultipleChoice(view);


        return view;
    }

    public void configureBtnMultipleChoice(View view){
        btnMultipleChoice = view.findViewById(R.id.btnMultipleChoice);
        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), MultipleChoice.class);
                startActivity(mainIntent);
            }
        });
    }


}