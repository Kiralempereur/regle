package com.example.bloodmenstrue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText editTextTextPersonName, editTextTextPersonName2;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        View ProgressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);
        button= findViewById(R.id.button);
        editTextTextPersonName= findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2= findViewById(R.id.editTextTextPersonName2);
        fAuth = FirebaseAuth.getInstance();



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = editTextTextPersonName.getText().toString().trim();
                String password =editTextTextPersonName2.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    editTextTextPersonName.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    editTextTextPersonName2.setError("password is required");
                    return;
                }
                if (password.length() < 8){
                    editTextTextPersonName2.setError("the password must be have 8 or more characters");
                    return;
                }

                ProgressBar.setVisibility(View.VISIBLE);
                //enregistrer le user dans firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
                            Toast.makeText(register.this, "user created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),bienvenue.class));
                        } else {
                            Toast.makeText(register.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });};
        });



    };
}
    