package com.phantanthinh.cau4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.sax.StartElementListener;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.phantanthinh.cau4.databinding.ActivityMainBinding;

import java.util.Random;

public class Cau1 extends AppCompatActivity {
    ActivityMainBinding binding;
    int index,randNumb;
    Random random = new Random();
    Handler handler = new Handler();
    Runnable foreGround = new Runnable() {
        @Override
        public void run() {
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = 150;
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            layoutParams.setMargins(10, 10, 10, 10);
            TextView textView = new TextView(Cau1.this);
            textView.setText(String.valueOf(randNumb));
            textView.setTextSize(20);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            if(randNumb%2==0){
                textView.setBackgroundColor(Color.BLUE);
            }else{
                textView.setBackgroundColor(Color.GRAY);
            }
            textView.setLayoutParams(layoutParams);
            binding.containerLayout.addView(textView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();
    }

    private void addEvent() {
        binding.btndraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBackground();
            }
        });
        binding.btnbai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cau1.this,Cau2.class);
                startActivity(intent);
            }
        });
    }

    private void drawBackground() {
        binding.containerLayout.removeAllViews();
        int numbOfViews = Integer.parseInt(binding.edtinput.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numbOfViews; i++) {
                    index = i;
                    randNumb = random.nextInt(10);
                    handler.post(foreGround);
                    SystemClock.sleep(100);
                }
            }
        });
        thread.start();
    }

}