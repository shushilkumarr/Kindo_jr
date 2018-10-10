package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Puzzle1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle1);
    }

    public void optionClick(View view) {
       int id= view.getId();
       if(id==R.id.feb){
           solved();
       }
       else {
           failed();
       }
    }

    private void solved() {
        Toast.makeText(getApplicationContext(),"Solved!!",Toast.LENGTH_SHORT).show();
        LinearLayout layout=findViewById(R.id.options);
        layout.setVisibility(View.INVISIBLE);
        TextView textView =findViewById(R.id.reason);
        textView.setVisibility(View.VISIBLE);
        Handler mHandler = new Handler();
        mHandler.postDelayed(changeactivity,2000);
    }
    private void failed() {
        Toast.makeText(getApplicationContext(),"Try Again..",Toast.LENGTH_SHORT).show();
    }
    private Runnable changeactivity = new Runnable() {
        public void run() {
            Intent intent=new Intent(getApplicationContext(),Puzzle2.class);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putInt("level",1);
        editor.commit();
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);
    }
}
