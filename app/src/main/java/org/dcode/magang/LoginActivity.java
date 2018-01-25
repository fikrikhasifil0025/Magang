package org.dcode.magang;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText nik,password;
    private TextView tvLupaPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nik = (EditText) findViewById(R.id.input_nik);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        tvLupaPassword = (TextView) findViewById(R.id.lupa_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tvLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lupaPassword();
            }
        });
    }
    public  void login(){
        String username = nik.getText().toString();
        String passwordKey = password.getText().toString();

        if (username.equals("1234") && passwordKey.equals("1234")){
            //jika login berhasil
            Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }else {
            //jika login gagal
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Username atau Password Anda salah!")
                    .setNegativeButton("Retry", null).create().show();
        }
    }

    public void lupaPassword(){
        Context mContext = null;
        String[] email = new String[]{
                "khasifil10@gmail.com","faisalaulia10@gmail.com"
        };

        String mailSubject = "Lupa Password";
        String mailBody = "Meminta penggantian password";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL,email);
        intent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);
        intent.putExtra(Intent.EXTRA_TEXT,mailBody);
        intent.setData(Uri.parse("mailto:"));

        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }else{
            Toast.makeText(mContext, "Email Belum terdaftar", Toast.LENGTH_LONG);
        }
    }

}
