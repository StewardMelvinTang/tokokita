package com.example.tokokita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    Button btn_login;
    EditText tb_username;
    EditText tb_password;
    Boolean canopendialog = false, finished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        btn_login = (Button)findViewById(R.id.BTN_Login);
        tb_username = (EditText)findViewById(R.id.TB_Username);
        tb_password = (EditText)findViewById(R.id.TB_Password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tb_username.getText().toString();
                String password = tb_password.getText().toString();

                if (username.equalsIgnoreCase("admin")
                && password.equals("12345"))
                {
                    canopendialog = false;

                    // intent ke mainactivity
                    Intent move = new Intent(Login_Activity.this, MainActivity.class);
                    move.putExtra("username", username);
                    startActivity(move);
                    finished = true;
                    //finish supaya gak bisa back ke activity ini
                    finish();


                } if (username.equals("")
                || password.equals("")) {
                    canopendialog = true;
                    openDialog("Login Failed", "Please enter your username and password");
                } else {
                    canopendialog = true;
                    openDialog("Login Failed", "Wrong Username/Password");
                }
            }
        });



        //function open dialog
    }
     public void openDialog(String title, String description){
        if (canopendialog == true && finished == false){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login_Activity.this);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(description);
        //set negative button akan selalu mengclose dialog
        dialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //clear all tb
                tb_username.setText("");
                tb_password.setText("");
            }
        });
            dialogBuilder.show();
        // jangan lupa di show di akhir
        }

        //end of function
    }
}