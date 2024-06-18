package com.phantanthinh.cau5;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.phantanthinh.cau5.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cau1 extends AppCompatActivity {
    ActivityMainBinding binding;
    ExecutorService executorService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        executorService = Executors.newSingleThreadExecutor();
        binding.btndraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.containerLayout.removeAllViews();
                executeLongRunningTask();
            }
        });
    }

    private void executeLongRunningTask() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int numRows = Integer.parseInt(binding.edtinput.getText().toString());
                for (int i = 0; i < numRows; i++) {
                    final int row = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayout rowLayout = new LinearLayout(Cau1.this);
                            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(0, 10, 0, 10);
                            rowLayout.setLayoutParams(layoutParams);
                            if (row % 2 == 0) {
                                addTextView(rowLayout, 2);
                                addTextView(rowLayout, 1);
                            } else {
                                addTextView(rowLayout, 1);
                                addTextView(rowLayout, 2);
                            }

                            binding.containerLayout.addView(rowLayout);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
    }

    private void addTextView(LinearLayout rowLayout, int weight) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
        params.setMargins(5, 5, 5, 5);
        textView.setLayoutParams(params);
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        textView.setText(String.valueOf(randomNumber));
        if (randomNumber % 2 == 0) {
            textView.setBackgroundColor(Color.BLUE);
        } else {
            textView.setBackgroundColor(Color.GRAY);
        }
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(26);
        rowLayout.addView(textView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (executorService != null)
            executorService.shutdownNow();
    }
}
