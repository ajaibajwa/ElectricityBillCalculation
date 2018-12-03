package com.example.ajay_mac.electricitybillcalculation;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.sax.StartElementListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Switch swtRemember;
    final Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences myPrefs=getSharedPreferences("rememberDetails",MODE_PRIVATE);
        final SharedPreferences.Editor editor=myPrefs.edit();
        edtEmail=findViewById(R.id.editEmail);
        edtPassword=findViewById(R.id.editPassword);
        btnLogin=findViewById(R.id.btnSignIn);
        swtRemember=findViewById(R.id.switchRememberMe);
        edtEmail.setText(myPrefs.getString("email",null));
        edtPassword.setText(myPrefs.getString("password",null));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String givenEmail = "let@gmail.com";
                String givenPassword = "12345";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern)) {
                    if ((email.equals(givenEmail)) && (password.equals(givenPassword))) {
                        if (swtRemember.isChecked()){
                            editor.putString("email",email);
                            editor.putString("password",password);
                            editor.apply();
                        }
                        else{
                            editor.remove("email");
                            editor.remove("password");
                            editor.apply();
                        }
                        Intent mIntent = new Intent(LoginActivity.this, ElectricityBillActivity.class);
                        startActivity(mIntent);
                        Toast.makeText(LoginActivity.this, "Welcome " + email, Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Invalid Details!");
                        builder.setMessage("Please enter valid Email and Password.")
                                .setCancelable(false)
                                //.setIcon(R.drawable.ic_invalid_alert)
                                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();

                                    }
                                });
                        AlertDialog alertDialog = builder.create();

                        // show it
                        alertDialog.show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email Address!" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
