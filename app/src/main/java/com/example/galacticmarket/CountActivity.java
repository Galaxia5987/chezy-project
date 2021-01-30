package com.example.galacticmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CountActivity extends AppCompatActivity {
    private FloatingActionButton add_button;
    private FloatingActionButton subtract_button;
    private EditText count_text;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        createView();
        add_button.setOnClickListener(view -> {
            String s = count_text.getText().toString();
            if (s.equals(""))
                count = 1;
            else
                count = Integer.parseInt(s) + 1;
            count_text.setText(Integer.toString(count));
        });
        subtract_button.setOnClickListener(view -> {
            String s = count_text.getText().toString();
            if (s.equals(""))
                count = 0;
            else
                count = Integer.parseInt(s) - 1;
            if (count < 0)
                count = 0;
            count_text.setText(Integer.toString(count));
        });

    }

    private void createView() {
        add_button = findViewById(R.id.add_button);
        subtract_button = findViewById(R.id.subtract_button);
        count_text = findViewById(R.id.count_text);
    }

}