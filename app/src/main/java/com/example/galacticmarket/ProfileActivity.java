package com.example.galacticmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    private EditText business_name;
    private EditText address;
    private EditText business_link;
    private EditText business_description;

    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        createView();
        
        submit_button.setOnClickListener(view -> {
            if (business_name.getText().toString().equals("") || address.getText().toString().equals("") || business_description.getText().toString().equals("")) {
                Snackbar.make(view, "Please enter all relevant information", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(view, "Name: " + business_name.getText().toString() +
                                ", Address: " + address.getText().toString() +
                                ", Link: " + business_link.getText().toString() +
                                ", Description: " + business_description.getText().toString(),
                        Snackbar.LENGTH_LONG).show();
                send();
                reset();
            }
        });


    }

    private void send() {
        BackgroundPost post = new BackgroundPost(this);
        post.execute("pu", business_name.getText().toString(), address.getText().toString(),
                business_link.getText().toString(), business_description.getText().toString());
    }

    private void reset() {
        business_name.setText("");
        address.setText("");
        business_link.setText("");
        business_description.setText("");
    }


    private void createView() {
        business_name = findViewById(R.id.business_name);
        address = findViewById(R.id.address);
        business_link = findViewById(R.id.business_link);
        business_description = findViewById(R.id.business_description);
        submit_button = findViewById(R.id.submit_button);
    }
}