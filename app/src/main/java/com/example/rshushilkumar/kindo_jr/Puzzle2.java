package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Puzzle2 extends AppCompatActivity {

    private OrientationListener orientationListener;

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
        setContentView(R.layout.activity_puzzle2);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        orientationListener = new OrientationListener(this);

    }

    private void rotateImg(int rotation) {
        ImageView view = findViewById(R.id.ques_img);
        view.animate().rotation(90 * rotation).setDuration(0);
    }

    public void optionClick(View view) {
        if (view.getId() == R.id.btnAns && (orientationListener.getRotation() == 1 || orientationListener.getRotation() == 3))
        {
            solved();
        }
        else
        failed();

    }
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putInt("level",2);
        editor.commit();
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);
    }

    private void solved() {
        rotateImg(0);
        orientationListener.disable();
        Toast.makeText(getApplicationContext(), "Solved!!", Toast.LENGTH_SHORT).show();
        LinearLayout layout = findViewById(R.id.options);
        layout.setVisibility(View.GONE);
        LinearLayout reason=findViewById(R.id.ans);
        reason.setVisibility(View.VISIBLE);
        Handler mHandler = new Handler();
        mHandler.postDelayed(changeActivity, 2000);
    }

    private void failed() {
        Toast.makeText(getApplicationContext(), "Try Again..", Toast.LENGTH_SHORT).show();
    }

    private Runnable changeActivity = new Runnable() {
        public void run() {
            Intent intent = new Intent(getApplicationContext(), Puzzle3.class);
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
                //menuButton.startAnimation(toPortAnim);
                rotateImg(rotation);
            } else if (orientation > 145 && orientation < 215 && rotation != ROTATION_180) { // REVERSE PORTRAIT
                rotation = ROTATION_180;
                rotateImg(rotation);
            } else if (orientation > 55 && orientation < 125 && rotation != ROTATION_270) { // REVERSE LANDSCAPE
                rotation = ROTATION_270;
                rotateImg(rotation);
            } else if (orientation > 235 && orientation < 305 && rotation != ROTATION_90) { //LANDSCAPE
                rotation = ROTATION_90;
                rotateImg(rotation);
            }
        }

    }
}

