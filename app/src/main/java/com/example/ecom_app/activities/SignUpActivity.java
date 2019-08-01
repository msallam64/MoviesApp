package com.example.ecom_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom_app.R;
import com.example.ecom_app.models.home.usermodel.UserLog;
import com.example.ecom_app.models.home.usermodel.UserModel;
import com.example.ecom_app.networking.MyRequests;
import com.example.ecom_app.networking.MyRetrofit;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    LoginButton loginButton;
    Button signup;
    EditText user, mail, phone, password;
    String user_name, user_mail, user_phone, user_password;
    TextView signin;
    UserLog userLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signin = findViewById(R.id.tv_signin);
        googleSignInButton = findViewById(R.id.sign_in_google_button);
        loginButton = findViewById(R.id.face_login_button);
        googleSignInButton = findViewById(R.id.sign_in_google_button);
        signup = findViewById(R.id.btn_signup);
        user = findViewById(R.id.usename);
        mail = findViewById(R.id.mail);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 102);
            }
        });
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = user.getText().toString();
                user_mail = mail.getText().toString();
                user_password = password.getText().toString();
                user_phone = phone.getText().toString();
                attemptLogin();

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

    }

    private void attemptLogin() {
        boolean mCancel = validateLogin(user_name, user_password, user_mail, user_phone);
        if (mCancel) {
            loginProcess(user_name, user_password, user_mail, user_phone);


        } else {
            Toast.makeText(this, "UserLog or Password is incorrect ! ", Toast.LENGTH_LONG).show();


        }
    }

    private void loginProcess(String user_name, String user_password, String user_mail1, String user_phone) {
        MyRequests log = MyRetrofit.getRetrofitInstance().create(MyRequests.class);
        Call<UserModel> mService = log.signup(user_name, user_mail1, user_phone, user_password);
        mService.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userModel = response.body();
                    userLog = userModel.getUser();
                    int activationCode = userLog.getActivation();
                    String act_code = String.valueOf(activationCode);
                    Toast.makeText(SignUpActivity.this, "Activation Code Sent  " + userLog.getName() + " Just Activation ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, ActivityActivation.class);
                    intent.putExtra("code", act_code);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Sign Up Not Successful   !"
                            , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Network Error ! ", Toast.LENGTH_LONG).show();


            }
        });


    }


    private boolean validateLogin(String username, String password, String mail, String phone) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "UserLog is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        if (mail == null || mail.trim().length() == 0) {
            Toast.makeText(this, "mail is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        if (phone == null || phone.trim().length() == 0) {
            Toast.makeText(this, "UserLog is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }


}
