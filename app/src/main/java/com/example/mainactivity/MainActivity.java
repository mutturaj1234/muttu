package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText username,password,repassword;
private Button login,resister;
DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.user);
        password=findViewById(R.id.pass);
        repassword=findViewById(R.id.repass);
        login=findViewById(R.id.resistered);
        resister=findViewById(R.id.resister);
        DB=new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginactivity.class));
            }
        });

        resister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this,"please enter all the fields",Toast.LENGTH_LONG).show();
                }else{
                    if(pass.equals((repass))){
                        Boolean checkuser  = DB.Checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Resistered succefully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this,"Resistration failed",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"user already exit login",Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(MainActivity.this,"passwords not matching",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}