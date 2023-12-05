package com.example.logika.ReviewerFragment.reviewerActivities.DigitalArithmeticFragments;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.ReviewerFragment.reviewerActivities.IntroFragments.ReviewerIntroFragment1;
import com.example.logika.ReviewerFragment.reviewerActivities.IntroFragments.ReviewerIntroFragment2;

public class ReviewerArithmeticActivity extends AppCompatActivity {

    private ImageView imageViewPage;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_digital_arithmetic_reviewer1);

        initializeViewElements();
        configureBackButton();
        switchPages();
    }

    private void initializeViewElements(){
        imageViewPage = findViewById(R.id.imageViewPage);
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
                Intent intent = new Intent(ReviewerArithmeticActivity.this, MainMenu.class);
                intent.putExtra("RETURN_TO_FRAGMENT", "SecondFragment"); // Add identifier for the second fragment
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void switchPages(){

        contentFragment = createFragmentPage(pageNumber);
        createImagePerPage(pageNumber);

        fragmentManager.beginTransaction()
                .replace(R.id.contentFragmentContainer, contentFragment.getClass(), null)
                .setReorderingAllowed(true)
                . addToBackStack("name")
                .commit();

        btnNext.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewNext.setImageResource(R.drawable.home_031);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewNext.setImageResource(R.drawable.home_023);
                    if (pageNumber > 0 && pageNumber < 9){
                        pageNumber++;
                    }
                    switchPages();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });

        btnPrevious.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Change the image when the button is pressed
                    imageViewPrevious.setImageResource(R.drawable.home_035);
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewPrevious.setImageResource(R.drawable.home_027);
                    if (pageNumber > 1 && pageNumber < 10) {
                        pageNumber--;
                    }
                    switchPages();
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });


    }

    private void createImagePerPage(int pageNumber){
        switch (pageNumber){
            case 1:
                imageViewPage.setImageResource(R.drawable.l2_p1);
                break;
            case 2:
                imageViewPage.setImageResource(R.drawable.l2_p2);
                break;
            case 3:
                imageViewPage.setImageResource(R.drawable.l2_p3);
                break;
            case 4:
                imageViewPage.setImageResource(R.drawable.l2_p4);
                break;
            case 5:
                imageViewPage.setImageResource(R.drawable.l2_p5);
                break;
            case 6:
                imageViewPage.setImageResource(R.drawable.l2_p6);
                break;
            case 7:
                imageViewPage.setImageResource(R.drawable.l2_p7);
                break;
            case 8:
                imageViewPage.setImageResource(R.drawable.l2_p8);
                break;
            case 9:
                imageViewPage.setImageResource(R.drawable.l2_p9);
                break;

        }
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
            case 3:
                fragment = new ReviewerArithmeticFragment3();
                break;
            case 4:
                fragment = new ReviewerArithmeticFragment4();
                break;
            case 5:
                fragment = new ReviewerArithmeticFragment5();
                break;
            case 6:
                fragment = new ReviewerArithmeticFragment6();
                break;
            case 7:
                fragment = new ReviewerArithmeticFragment7();
                break;
            case 8:
                fragment = new ReviewerArithmeticFragment8();
                break;
            case 9:
                fragment = new ReviewerArithmeticFragment9();
                break;
            default:
                fragment = new ReviewerArithmeticFragment1();
                break;
        }

        return fragment;
    }

}