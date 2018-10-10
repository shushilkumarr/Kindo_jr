package com.example.rshushilkumar.kindo_jr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedpreferences = getSharedPreferences("prefFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);

    }
}
