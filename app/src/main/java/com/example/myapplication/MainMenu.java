package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickAdmin(View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

    }

    public void onClickUser(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onClickDO(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onClickDP(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
