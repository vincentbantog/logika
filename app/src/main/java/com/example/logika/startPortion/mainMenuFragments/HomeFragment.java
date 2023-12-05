package com.example.logika.startPortion.mainMenuFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

    private ImageView imageViewLogiquiz;
    private ImageView imageViewBasiquiz;
    private ImageView imageViewTTT;
    private ImageView imageViewTorF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageViewBasiquiz = view.findViewById(R.id.imageViewBasiquiz);
        imageViewLogiquiz = view.findViewById(R.id.imageViewLogiquiz);
        imageViewTTT = view.findViewById(R.id.imageViewTTT);
        imageViewTorF = view.findViewById(R.id.imageViewTorF);

        configureBtnLogicGate(view);
        configureBtnMultipleChoice(view);
        configureBtnTicTacToe(view);
        configureBtnTrueOrFalse(view);


        return view;
    }


    @SuppressLint("ClickableViewAccessibility")
    public void configureBtnLogicGate(View view){
        btnLogicGate = view.findViewById(R.id.btnLogicSim);
//        btnLogicGate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), LogicGate.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });

        btnLogicGate.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewLogiquiz.setImageResource(R.drawable.home_009c);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewLogiquiz.setImageResource(R.drawable.home_009);
                    Intent intent = new Intent(getActivity(), LogicGate.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
    public void configureBtnMultipleChoice(View view){
        btnMultipleChoice = view.findViewById(R.id.btnMultipleChoice);
//        btnMultipleChoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MultipleChoice.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });

        btnMultipleChoice.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewBasiquiz.setImageResource(R.drawable.home_010c);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewBasiquiz.setImageResource(R.drawable.home_010);
                    Intent intent = new Intent(getActivity(), MultipleChoice.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void configureBtnTicTacToe(View view){

        btnQuickStart = view.findViewById(R.id.btnTicTacToe);
//        btnQuickStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), TicTacToeMain.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });

        btnQuickStart.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewTTT.setImageResource(R.drawable.home_011c);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewTTT.setImageResource(R.drawable.home_011);
                    Intent intent = new Intent(getActivity(), TicTacToeMain.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void configureBtnTrueOrFalse(View view){
        btnTrueOrFalse = view.findViewById(R.id.btnTrueOrFalse);
//        btnTrueOrFalse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), TrueOrFalse.class);
//                startActivity(intent);
//                getActivity().finish();
//
//            }
//        });

        btnTrueOrFalse.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewTorF.setImageResource(R.drawable.home_012c);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewTorF.setImageResource(R.drawable.home_012);
                    Intent intent = new Intent(getActivity(), TrueOrFalse.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });

    }


}