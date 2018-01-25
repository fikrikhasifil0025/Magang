package org.dcode.magang;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvChangePassword, tvLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvChangePassword = (TextView) findViewById(R.id.changePass);
        tvLogOut = (TextView) findViewById(R.id.logout);

        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                AppState.getSingleInstance().setLoggingOut(true);
                Toast.makeText(getApplicationContext(), "BERHASIL LOGOUT",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this,
                        LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeDialog();
            }
        });
    }

    public static class AppState {
        private static AppState singleInstance;

        private boolean isLoggingOut;

        private AppState() {
        }

        public static AppState getSingleInstance() {
            if (singleInstance == null) {
                singleInstance = new AppState();
            }
            return singleInstance;
        }

        public boolean isLoggingOut() {
            return isLoggingOut;
        }

        public void setLoggingOut(boolean isLoggingOut) {
            this.isLoggingOut = isLoggingOut;
        }
    }

    public void showChangeDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_change_password, null);
        dialogBuilder.setView(dialogView);

        final EditText edtPass = (EditText) dialogView.findViewById(R.id.input_password_lama);
        final EditText edtPassBaru = (EditText) dialogView.findViewById(R.id.input_password_baru);

        dialogBuilder.setTitle("Alert!");
        dialogBuilder.setMessage("Don't forget your password");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Toast.makeText(getApplicationContext(), "Your Password have been Changes", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
