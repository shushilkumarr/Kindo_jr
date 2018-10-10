package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Puzzle4 extends AppCompatActivity{

    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            if (mAccel > 15) {
                /*Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
                toast.show();*/
                startAnim();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
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

            Intent intent = new Intent(getApplicationContext(), End.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle4);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putInt("level",4);
        editor.commit();
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}
