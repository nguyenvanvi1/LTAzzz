package com.phantanthinh.cau3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.phantanthinh.cau3.databinding.ActivityMainBinding;

public class Cau1 extends AppCompatActivity {
    ActivityMainBinding binding;
    int index;
    Handler handler = new Handler();
    Runnable foreThread = new Runnable() {
        @Override
        public void run() {
            String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};
            Button button = new Button(Cau1.this);
            button.setBackgroundColor(Color.GRAY);
            button.setText(values[index]);
            button.setTextSize(20);
            button.setTextColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentText = binding.edtinput.getText().toString();
                    binding.edtinput.setText(currentText + button.getText().toString());
                }
            });

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = 150;
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            layoutParams.setMargins(10, 10, 10, 10);
            button.setLayoutParams(layoutParams);
            binding.containerLayout.addView(button);
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
    }

    private void drawBackground() {
        binding.edtinput.setText("");
        binding.containerLayout.removeAllViews();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    index = i;
                    handler.post(foreThread);
                    SystemClock.sleep(100);
                }
            }
        });
        thread.start();
    }
}