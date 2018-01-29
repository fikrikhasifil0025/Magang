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

import org.dcode.magang.Api.BaseApiService;
import org.dcode.magang.Api.UtilsApi;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText nik,password;
    private TextView tvLupaPassword;
    Context mContext;
    ProgressDialog loading;

    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nik = (EditText) findViewById(R.id.input_nik);
        password = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        tvLupaPassword = (TextView) findViewById(R.id.lupa_password);
//      loginbutton
//        mContext = this;
//        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
//        initComponents();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Waiting for connecting...", true, false);
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

//    private void initComponents() {
//        nik = (EditText) findViewById(R.id.input_nik);
//        password = (EditText) findViewById(R.id.input_password);
//        loginButton = (Button) findViewById(R.id.btn_login);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loading = ProgressDialog.show(mContext, null, "Waiting for connecting...", true, false);
//                login();
//            }
//        });
//    }

    public  void login(){
        String username = nik.getText().toString();
        String passwordKey = password.getText().toString();

        if (username.equals("1234") && passwordKey.equals("1234")){
            //jika login berhasil
            Toast.makeText(getApplicationContext(), "LOGIN SUCCESS",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }else {
            //jika login gagal
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Username or Password wrong!")
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

    public void requestLogin(){
        mApiService.loginRequest(nik.getText().toString(), password.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("result_nama", nama);
                                    startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

}
