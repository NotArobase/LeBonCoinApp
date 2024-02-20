package com.example.leboncoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.TextInputEditText;



public class AdAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_add);

        TextInputLayout textInputLayout = findViewById(R.id.textInput_titre);
        TextInputEditText textInputEditText = (TextInputEditText) textInputLayout.getEditText();
        String titre = textInputEditText != null ? textInputEditText.getText().toString() : "";

        TextInputLayout textInputLayoutAdresse = findViewById(R.id.textInput_adresse);
        TextInputEditText textInputEditTextAdresse = (TextInputEditText) textInputLayoutAdresse.getEditText();
        String adresse = textInputEditTextAdresse != null ? textInputEditTextAdresse.getText().toString() : "";

        Button validateButton = findViewById(R.id.button);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = textInputEditText.getText().toString();
                String adresse = textInputEditTextAdresse.getText().toString();
                Intent intent = new Intent(AdAddActivity.this, AdListViewActivity.class);
                intent.putExtra("title", titre);
                intent.putExtra("address", adresse);
                startActivity(intent);
            }
        });
    }
}