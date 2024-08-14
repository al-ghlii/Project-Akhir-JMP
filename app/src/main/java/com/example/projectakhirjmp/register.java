package com.example.projectakhirjmp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class register extends AppCompatActivity {
    Button bregis;
    EditText eduser,edpass;
    DatabaseHelper dblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bregis = (Button) findViewById(R.id.btnregister1);
        eduser = (EditText) findViewById(R.id.username1);
        edpass = (EditText) findViewById(R.id.password1);
        dblogin = new DatabaseHelper(this);

        bregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = eduser.getText().toString();
                String password = edpass.getText().toString();
                Boolean checkUser = dblogin.checkUser(user);
                if (checkUser == false) {
                    Boolean insert = dblogin.insertUser(user,password);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}