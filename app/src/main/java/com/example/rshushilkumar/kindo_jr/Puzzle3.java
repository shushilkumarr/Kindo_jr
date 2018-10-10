package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Puzzle3 extends AppCompatActivity {

    private Puzzle3.OrientationListener orientationListener;

    @Override
    protected void onStart() {
        orientationListener.enable();
        super.onStart();
    }

    @Override
    protected void onStop() {
        orientationListener.disable();
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle3);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        orientationListener = new Puzzle3.OrientationListener(this);

    }
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putInt("level",3);
        editor.commit();
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);
    }
    private void startAnim(){
        Toast.makeText(getApplicationContext(), "Solved!!", Toast.LENGTH_SHORT).show();
        ImageView chicken=findViewById(R.id.chicken);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.chicken_amin);
        animation.setFillAfter(true);
        chicken.startAnimation(animation);
        Handler mHandler = new Handler();
        mHandler.postDelayed(chageView,3000);


    }
    private Runnable chageView=new Runnable() {
        @Override
        public void run() {
            TextView reason=findViewById(R.id.reason);
            reason.setVisibility(View.VISIBLE);
            Handler mHandler=new Handler();
            mHandler.postDelayed(changeActivity, 3000);
        }
    };
    private Runnable changeActivity=new Runnable() {
        public void run() {

            Intent intent = new Intent(getApplicationContext(), Puzzle4.class);
            startActivity(intent);
        }
    };
    private class OrientationListener extends OrientationEventListener {
        final int ROTATION_O = 0;
        final int ROTATION_90 = 1;
        final int ROTATION_180 = 2;
        final int ROTATION_270 = 3;

        private int rotation = 0;

        public OrientationListener(Context context) {
            super(context);
        }

        public int getRotation() {
            return rotation;
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if ((orientation < 35 || orientation > 325) && rotation != ROTATION_O) { // PORTRAIT
                rotation = ROTATION_O;

            } else if (orientation > 145 && orientation < 215 && rotation != ROTATION_180) { // REVERSE PORTRAIT
                rotation = ROTATION_180;
                startAnim();

            } else if (orientation > 55 && orientation < 125 && rotation != ROTATION_270) { // REVERSE LANDSCAPE
                rotation = ROTATION_270;

            } else if (orientation > 235 && orientation < 305 && rotation != ROTATION_90) { //LANDSCAPE
                rotation = ROTATION_90;

            }
        }
    }
}
