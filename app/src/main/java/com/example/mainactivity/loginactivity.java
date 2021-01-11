package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {
    private EditText edituser,editpass;
    private Button login1,resister1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        edituser=findViewById(R.id.user1);
        editpass=findViewById(R.id.pass1);
        login1=findViewById(R.id.login);
        resister1=findViewById(R.id.resister1);
        DB = new DBHelper(this);
        resister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = edituser.getText().toString();
                String pass1 = edituser.getText().toString();
                if(user1.equals("")||pass1.equals("")){
                    Toast.makeText(loginactivity.this,"please enter all the fields",Toast.LENGTH_LONG).show();
                }else{
                   Boolean checkusernamepassword = DB.checkusernamepassword(user1,pass1);
                   if(checkusernamepassword==true){
                       Toast.makeText(loginactivity.this,"Loged in succefully",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(loginactivity.this,HomeActivity.class));
                   }else{
                       Toast.makeText(loginactivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }
}