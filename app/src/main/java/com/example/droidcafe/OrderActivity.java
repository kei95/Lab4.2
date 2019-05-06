package com.example.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String message = "Order: " +
                intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.OrderTV);
        try {
            if(!intent.getStringExtra(MainActivity.EXTRA_MESSAGE).isEmpty())
            textView.setText(message);
        } catch (Exception e){
            textView.setText("Order: You didn't order anything yet");
        }
    }
}
