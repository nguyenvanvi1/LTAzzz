package com.phantanthinh.cau2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.phantanthinh.cau2.databinding.ActivityMainBinding;

import java.util.Random;

public class Cau1 extends AppCompatActivity {
    ActivityMainBinding binding;
    int randNumb,numb;
    Random random = new Random();
    Handler handler = new Handler();
    // Main/UI Thread
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100,1);
            params.setMargins(10, 10, 10, 10);
            EditText editText = new EditText(Cau1.this);
            Button button = new Button(Cau1.this);
            if(numb % 2 == 0){
                editText.setLayoutParams(params);
                editText.setText(String.valueOf(randNumb));
                editText.setTextColor(Color.BLACK);
                editText.setTextSize(26);
                binding.containerLayout.addView(editText);
            }else{
                button.setLayoutParams(params);
                button.setText(String.valueOf(randNumb));
                button.setTextColor(Color.BLACK);
                button.setGravity(Gravity.CENTER);
                button.setTextSize(26);
                binding.containerLayout.addView(button);
            }
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
        binding.btndraw. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execLongRunningTask();
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

    private void execLongRunningTask() {
        // Worker / Background Thread
        binding.containerLayout.removeAllViews();
        int numbOfViews = Integer.parseInt(binding.edtinput.getText().toString());
        Thread backgroungThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =1 ; i<= numbOfViews ; i++){
                    numb = i;
                    randNumb = random.nextInt(100);
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroungThread.start();
    }
}