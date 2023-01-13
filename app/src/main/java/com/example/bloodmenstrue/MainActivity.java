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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnx;
    private Button b1;
    FirebaseAuth fAuth;
    EditText edittext1, editText2;
    Button b0,b2;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View ProgressBar = findViewById(R.id.progressbar);

        b1= findViewById(R.id.b1);
        edittext1= findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        fAuth = FirebaseAuth.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      FirebaseAuth.getInstance().signOut();
                                      startActivity(new Intent(getApplicationContext(), bienvenue.class));
                                  }
                              });



        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = edittext1.getText().toString().trim();
                String password = editText2.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    edittext1.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    editText2.setError("password is required");
                    return;
                }
        if (password.length() < 8){
            editText2.setError("the password must be have 8 or more characters");
            return;
        }

ProgressBar.setVisibility(View.VISIBLE);
        //enregistrer le user dans firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
                        Toast.makeText(MainActivity.this, "welcome.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),bienvenue.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            });};
        });




        this.b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), register.class);
                startActivity(otherActivity);
                fAuth = FirebaseAuth.getInstance();
            }


        });



    }


}