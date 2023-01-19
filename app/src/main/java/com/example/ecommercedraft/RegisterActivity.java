package com.example.ecommercedraft;

import static com.example.ecommercedraft.BottomNavigationActivity.DATABASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEdit, passwordEdit, usernameEdit;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();

        emailEdit = findViewById(R.id.email);
        passwordEdit = findViewById(R.id.password);
        usernameEdit = findViewById(R.id.username);
        btnRegister = findViewById(R.id.register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,username;
                email = emailEdit.getText().toString();
                username = usernameEdit.getText().toString();
                password= passwordEdit.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this,"Please type in your email.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterActivity.this,"Please type in your username.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"Please type in your password.",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            User user = new User(username, "Online", email);

                            FirebaseDatabase.getInstance(DATABASE_URL).getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(),"data saved",Toast.LENGTH_LONG).show();

                                            }else {
                                                Toast.makeText(getApplicationContext(),"data not made",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                            //Toast.makeText(getApplicationContext(),"Create Successful",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, BottomNavigationActivity.class);
                            startActivity(intent);
                        }else{
                            //Toast.makeText(getApplicationContext(),"Create Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}