package com.example.logika.ReviewerFragment.reviewerActivities.IntroFragments;

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

public class ReviewerIntroActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_intro_reviewer1);

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
                Intent intent = new Intent(ReviewerIntroActivity.this, MainMenu.class);
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
                    imageViewNext.setImageResource(R.drawable.home_030);
                    if (pageNumber == 1 || pageNumber == 2 || pageNumber == 3){
                        pageNumber++;
                    }
                    switchPages();


                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewNext.setImageResource(R.drawable.home_022);
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
                    imageViewPrevious.setImageResource(R.drawable.home_034);
                    if (pageNumber == 2 || pageNumber == 3 || pageNumber == 4) {
                        pageNumber--;
                    }
                    switchPages();
                    return true; // Indicate that the event was handled

                case MotionEvent.ACTION_UP:
                    // Change the image back when the button is released
                    imageViewPrevious.setImageResource(R.drawable.home_026);
                    return true; // Indicate that the event was handled

                default:
                    return false; // Return false for unhandled events
            }
        });

    }

    private void createImagePerPage(int pageNumber){
        switch (pageNumber){
            case 1:
                imageViewPage.setImageResource(R.drawable.l1_p1);
                break;
            case 2:
                imageViewPage.setImageResource(R.drawable.l1_p2);
                break;
            case 3:
                imageViewPage.setImageResource(R.drawable.l1_p3);
                break;
            case 4:
                imageViewPage.setImageResource(R.drawable.l1_p4);
                break;

        }
    }

    public Fragment createFragmentPage (int pageNumber){
        Fragment fragment = null;

        switch (pageNumber) {
            case 1:
                fragment = new ReviewerIntroFragment1();
                break;
            case 2:
                fragment = new ReviewerIntroFragment2();
                break;
            case 3:
                fragment = new ReviewerIntroFragment3();
                break;
            case 4:
                fragment = new ReviewerIntroFragment4();
                break;
            default:
                break;
        }

        return fragment;
    }
}