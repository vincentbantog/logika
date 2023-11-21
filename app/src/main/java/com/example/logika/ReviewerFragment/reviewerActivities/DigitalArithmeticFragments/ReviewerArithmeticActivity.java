package com.example.logika.ReviewerFragment.reviewerActivities.DigitalArithmeticFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.ReviewerFragment.reviewerActivities.IntroFragments.ReviewerIntroFragment1;
import com.example.logika.ReviewerFragment.reviewerActivities.IntroFragments.ReviewerIntroFragment2;

public class ReviewerArithmeticActivity extends AppCompatActivity {

    private TextView txtPageNumber;
    private Button btnBack;
    private Button btnNext;
    private Button btnPrevious;

    int pageNumber = 1;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_arithmetic_reviewer1);

        initializeViewElements();
        configureBackButton();
        switchPages();
    }

    private void initializeViewElements(){
        txtPageNumber = findViewById(R.id.txtPageNumber);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
    }

    private void configureBackButton(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewerArithmeticActivity.this, MainMenu.class);
                intent.putExtra("RETURN_TO_FRAGMENT", "SecondFragment"); // Add identifier for the second fragment
                startActivity(intent);
                finish();
            }
        });
    }

    private void switchPages(){

        contentFragment = createFragmentPage(pageNumber);

        fragmentManager.beginTransaction()
                .replace(R.id.contentFragmentContainer, contentFragment.getClass(), null)
                .setReorderingAllowed(true)
                . addToBackStack("name")
                .commit();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber == 1){
                    pageNumber++;
                }
                switchPages();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNumber == 2) {
                    pageNumber--;
                }
                switchPages();
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
                fragment = new ReviewerArithmeticFragment1();
                break;
            case 2:
                fragment = new ReviewerArithmeticFragment2();
                break;
            default:
                break;
        }

        return fragment;
    }

}