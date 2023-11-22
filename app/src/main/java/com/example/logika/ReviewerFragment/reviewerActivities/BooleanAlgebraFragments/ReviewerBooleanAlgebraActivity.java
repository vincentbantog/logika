package com.example.logika.ReviewerFragment.reviewerActivities.BooleanAlgebraFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.ReviewerFragment.reviewerActivities.LogicGates.ReviewerLogicGatesFragment1;
import com.example.logika.ReviewerFragment.reviewerActivities.LogicGates.ReviewerLogicGatesFragment2;

public class ReviewerBooleanAlgebraActivity extends AppCompatActivity {

    private TextView txtPageNumber;
    private Button btnBack;
    private Button btnNext;
    private Button btnPrevious;
    private ImageView imageViewNext;
    private ImageView imageViewPrevious;

    int pageNumber = 1;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boolean_algebra_reviewer1);

        initializeViewElements();
        configureBackButton();
        switchPages();
    }

    private void initializeViewElements(){
        txtPageNumber = findViewById(R.id.txtPageNumber);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewPrevious = findViewById(R.id.imageViewPrevious);
    }


    private void configureBackButton(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewerBooleanAlgebraActivity.this, MainMenu.class);
                intent.putExtra("RETURN_TO_FRAGMENT", "SecondFragment"); // Add identifier for the second fragment
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void switchPages(){

        contentFragment = createFragmentPage(pageNumber);

        fragmentManager.beginTransaction()
                .replace(R.id.contentFragmentContainer, contentFragment.getClass(), null)
                .setReorderingAllowed(true)
                . addToBackStack("name")
                .commit();


        btnNext.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewNext.setImageResource(R.drawable.home_033);
                    if (pageNumber == 1){
                        pageNumber++;
                    }
                    switchPages();
                    return true;

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewNext.setImageResource(R.drawable.home_025);
                    return true;

                default:
                    return false;
            }
        });

        btnPrevious.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewPrevious.setImageResource(R.drawable.home_037);
                    if (pageNumber == 2) {
                        pageNumber--;
                    }
                    switchPages();
                    return true;

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewPrevious.setImageResource(R.drawable.home_029);
                    return true;


                default:
                    return false;
            }
        });

        updatePageNumberText();
    }

    private void updatePageNumberText(){
        txtPageNumber.setText(pageNumber + "");
    }

    public Fragment createFragmentPage (int pageNumber){
        Fragment fragment = null;

        switch (pageNumber) {
            case 1:
                fragment = new ReviewerBooleanAlgebraFragment1();
                break;
            case 2:
                fragment = new ReviewerBooleanAlgebraFragment2();
                break;
            default:
                break;
        }

        return fragment;
    }

}