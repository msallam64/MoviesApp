package com.example.ecom_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecom_app.MainActivity;
import com.example.ecom_app.R;
import com.example.ecom_app.models.home.usermodel.UserLog;
import com.example.ecom_app.models.home.usermodel.UserModel;
import com.example.ecom_app.networking.MyRequests;
import com.example.ecom_app.networking.MyRetrofit;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    Button login;
    EditText user, password;
    String user_emil, user_password;
    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView user_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        googleSignInButton = findViewById(R.id.sign_in_google_button);
        login = findViewById(R.id.btn_signin);
        user = findViewById(R.id.usename);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.face_login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        user_signup=findViewById(R.id.tv_signup);
        user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_password = password.getText().toString();
                user_emil = user.getText().toString();
                attemptLogin();

            }
        });
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


    }

    private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "UserLog is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is Required", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    private void attemptLogin() {
        boolean mCancel = validateLogin(user_emil, user_password);
        if (mCancel) {
            loginProcess(user_emil, user_password);


        } else {
            Toast.makeText(this, "UserLog or Password is incorrect ! ", Toast.LENGTH_LONG).show();


        }
    }

    private void loginProcess(final String email, String password) {
        MyRequests log = MyRetrofit.getRetrofitInstance().create(MyRequests.class);
        Call<UserModel> mService = log.signin(email, password, "121212121", "andriod");
        mService.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userModel = response.body();
                    UserLog userLog = userModel.getUser();
                    userModel.setUser(userLog);
                    Intent intent=new Intent(LogInActivity.this, MainActivity.class);
                    intent.putExtra("user",userLog);
                    startActivity(intent);
                    Toast.makeText(LogInActivity.this, "Hi ! : " + userLog.getName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LogInActivity.this, "Sign in Not Successful   !"
                            , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "Network Error ! ", Toast.LENGTH_LONG).show();


            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            googleSignInClient.signOut();
        } else {
            Log.d("", "Not logged in");
        }
    }


}
