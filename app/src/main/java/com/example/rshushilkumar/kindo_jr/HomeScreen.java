package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void startGame(View view) {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        int level=sharedpreferences.getInt("level",0);

        Intent intent;
        intent = new Intent(this,Puzzle1.class);
        if(level==2)
            intent = new Intent(this,Puzzle2.class);
        else if(level==3)
            intent = new Intent(this,Puzzle3.class);
        else if (level==4)
            intent = new Intent(this,Puzzle4.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
