package com.example.avadla.scratch2;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private ScratchTextView scratchTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);

        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);

        scratchTextView = (ScratchTextView) findViewById(R.id.scratch);
        scratchTextView.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView tv) {
            }

            @Override
            public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                if(percent*100 > 99){
//                    scratchTextView.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"coupon revealed",Toast.LENGTH_SHORT).show();
                    flipCard();
//                    TextView textView = (TextView) findViewById(R.id.card1);
//                    textView.setText("123456789");
                    mIsBackVisible = true;
                }
            }
        });
    }

    public void flipCard() {

            mSetRightOut.setTarget(scratchTextView);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
        mCardBackLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if(mIsBackVisible){
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
        else {
            super.onBackPressed();
        }
    }
}