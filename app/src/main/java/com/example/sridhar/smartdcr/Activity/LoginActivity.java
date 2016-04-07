package com.example.sridhar.smartdcr.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sridhar.smartdcr.R;

import com.example.sridhar.smartdcr.Util.CustomProgressDialog;


public class LoginActivity extends BaseActivity {

    EditText userNameEdittext,passwordEdittext;
    Button signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        userNameEdittext=(EditText) findViewById(R.id.username_edittext);
        passwordEdittext=(EditText) findViewById(R.id.password_edittext);

        progressBar = new CustomProgressDialog(this);

        signInButton=(Button)findViewById(R.id.signin_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=userNameEdittext.getText().toString();
                String passWord=passwordEdittext.getText().toString();

                if (progressBar != null) {
                        progressBar.show();
                    }

        //        startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
        //        finish();

                if(userName.equalsIgnoreCase("admin") && passWord.equalsIgnoreCase("admin")) {

                    if (progressBar != null) {
                        progressBar.show();
                    }

                    startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                    finish();
                }
                else{

                    if (progressBar != null) {
                        progressBar.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Enter User Name and Password Correctly", Toast.LENGTH_LONG).show();
                }

//                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
//                startActivity(intent);


            }
        });

    }

    @Override
    public void onBackPressed()
    {
      finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                break;
            // Something else
            case R.id.action_settings:
//                intent = new Intent(this, ThirdActivity.class);
//                startActivity(intent);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }






    public void login(){

        String userName=userNameEdittext.getText().toString();
        String passWord=passwordEdittext.getText().toString();



    }



}
