package com.boss.cuncis.postappretrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boss.cuncis.postappretrofit.R;
import com.boss.cuncis.postappretrofit.api.ApiClient;
import com.boss.cuncis.postappretrofit.api.TheJsonPlaceHoderlApi;
import com.boss.cuncis.postappretrofit.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText etEmail, etPassword;
    Button btnSubmit;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_submit);
        tvResult = findViewById(R.id.tv_result);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postData();
            }
        });

    }

    private void postData() {
        User user = new User();
        user.setEmail(etEmail.getText().toString());
        user.setPassword(etPassword.getText().toString());
        Log.d(TAG, "postData: before id: " + user.getId());

        TheJsonPlaceHoderlApi theJsonPlaceHoderlApi = ApiClient.getClient();
        Call<User> call = theJsonPlaceHoderlApi.getResult(user);
        etEmail.setText("");
        etPassword.setText("");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User returnedUser = response.body();
                tvResult.setText("The id is " + returnedUser.getId());
                Log.d(TAG, "onResponse: After id: " + returnedUser.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
