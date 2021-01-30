package com.example.galacticmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import com.google.android.material.snackbar.Snackbar;

public class OrderActivity extends AppCompatActivity {

    private Button delivery;
    private Button takeaway;
    private Button order;
    private EditText orderText;
    private boolean isDelivery = true;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        createView();
        delivery.setOnClickListener(v -> {
            delivery.setBackgroundColor(getResources().getColor(R.color.background_selected));
            takeaway.setBackgroundColor(getResources().getColor(R.color.background_default));
            isDelivery = true;
        });
        takeaway.setOnClickListener(v -> {
            takeaway.setBackgroundColor(getResources().getColor(R.color.background_selected));
            delivery.setBackgroundColor(getResources().getColor(R.color.background_default));
            isDelivery = false;
        });
        order.setOnClickListener(v -> {
            if (orderText.getText().toString().equals("")) {
                Snackbar snackbar = Snackbar.make(v, "Must Fill Order", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                Intent intent = new Intent(OrderActivity.this, SuccessActivity.class);
                startActivity(intent);
                finish();
                sendEmail();
            }
        });
    }


    private void sendEmail() {
        OrderSender orderSender = new OrderSender(this, isDelivery, orderText.getText().toString());
        orderSender.execute();
    }

    private void createView() {
        delivery = findViewById(R.id.delivery);
        takeaway = findViewById(R.id.takeaway);
        order = findViewById(R.id.order);
        orderText = findViewById(R.id.order_text);
        Intent intent = getIntent();
        Log.e("intent: ", "email was passed");
        email = intent.getStringExtra("email");
    }
}