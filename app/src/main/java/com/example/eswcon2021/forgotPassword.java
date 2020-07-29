package com.example.eswcon2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    private EditText passwordEmail;
    private FirebaseAuth auth;
    Toolbar toolbar;
    String rootChange;
    int aha;

    private static final String TAG = "forgotPassword111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int intentF = getIntent().getExtras().getInt("place1");
            if (intentF == 1) {
                aha = 1;
            }
        }

        // Setting the back button
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ESWCon");

        passwordEmail = (EditText) findViewById(R.id.etResetEmail);
        Button resetPassword = (Button) findViewById(R.id.btnReset);
        auth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Sending email to user
                String userEmail = passwordEmail.getText().toString().trim(); // Text entered is an EditText object converting to string

                if (userEmail.equals("")) {
                    Toast.makeText(forgotPassword.this, "Please enter the registered email.", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(forgotPassword.this, "Email sent!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotPassword.this, MainActivity.class));
                            } else {
                                Toast.makeText(forgotPassword.this, "Email not found in database.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.

        if(aha == 1)
        {
            startActivity(new Intent(forgotPassword.this, MainActivity.class));
            finish();
        }
        else
        {
            rootChange = Settings.settingsSP.getString(Settings.rootChangeOne, null);
            if(rootChange.equalsIgnoreCase("true"))
            {
                Bundle b = new Bundle();
                b.putInt("frgToLoad", 1);
                Intent i = new Intent(forgotPassword.this, Homepage.class);
                i.putExtras(b);
                startActivity(i);
            }
        }

        return true;
    }

}
