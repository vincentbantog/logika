package com.example.logika.startPortion.mainMenuFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.logika.HomeFragment.gameActivities.TorF.twoPlayers.TicTacToeMain;
import com.example.logika.R;
import com.example.logika.HomeFragment.gameActivities.TorF.TFHomePage;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.LogicGate;
import com.example.logika.HomeFragment.gameActivities.basiQuiz.MultipleChoice;
import com.example.logika.HomeFragment.gameActivities.TorF.singlePlayer.TrueOrFalse;

import java.util.Random;


public class HomeFragment extends Fragment {


    private Button btnMultipleChoice;
    private Button btnTrueOrFalse;
    private Button btnLogicGate;
    private Button btnQuickStart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        configureBtnLogicGate(view);
        configureBtnMultipleChoice(view);
        configureBtnTicTacToe(view);
        configureBtnTrueOrFalse(view);


        return view;
    }


    public void configureBtnLogicGate(View view){
        btnLogicGate = view.findViewById(R.id.btnLogicSim);
        btnLogicGate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogicGate.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void configureBtnMultipleChoice(View view){
        btnMultipleChoice = view.findViewById(R.id.btnMultipleChoice);
        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MultipleChoice.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void configureBtnTicTacToe(View view){

        btnQuickStart = view.findViewById(R.id.btnTicTacToe);
        btnQuickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TicTacToeMain.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void configureBtnTrueOrFalse(View view){
        btnTrueOrFalse = view.findViewById(R.id.btnTrueOrFalse);
        btnTrueOrFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrueOrFalse.class);
                startActivity(intent);
                getActivity().finish();

            }

        });
    }


}