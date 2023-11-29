package com.example.animationstask;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity2 extends AppCompatActivity {
    LottieAnimationView lottieAnimation;
    ProgressBar progressBar;
    TextView screen, loading;
    int count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lottieAnimation=findViewById(R.id.lottieanimation02);
        lottieAnimation.setAnimation(R.raw.ball);

        screen=findViewById(R.id.txtScreenName);
        screen.setText("Second Animation Loading.....");

        loading=findViewById(R.id.tvLoading);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        }
        else
        {
            progressBar.getProgressDrawable().setColorFilter(
                    new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN));
        }

        CountDownTimer countDownTimer = new CountDownTimer(5000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                count += 10;
                progressBar.incrementProgressBy(10);
                loading.setText("Loading "+count+" %");
               // Toast.makeText(MainActivity2.this, "this is second screen", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                //create Intent for screen switching equation one
                finishAffinity();
            }
        }.start();
        progressAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAffinity();
            }
        },5000);
    }

    private void progressAnimation() {
        ProgressBarAnimation animation = new ProgressBarAnimation(this,progressBar,0f,100f,100f);
        animation.setDuration(8000);
        progressBar.setAnimation(animation);
    }
}
