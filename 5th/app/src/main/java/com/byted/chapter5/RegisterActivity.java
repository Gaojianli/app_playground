package com.byted.chapter5;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText eName = findViewById(R.id.name);
        EditText ePassword = findViewById(R.id.password);
        EditText eRepassword = findViewById(R.id.repassword);
        Button register = findViewById(R.id.register);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        register.setOnClickListener(v -> {
            String name = eName.getText().toString();
            String password = ePassword.getText().toString();
            String repassword = eRepassword.getText().toString();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
                Toast.makeText(RegisterActivity.this, "参数不合法", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.equals(password, repassword)) {
                Toast.makeText(RegisterActivity.this, "密码不相等", Toast.LENGTH_SHORT).show();
                return;
            }
            apiService.register(name, password, password).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.body() != null) {
                        Integer errorCode = response.body().errorCode;
                        if (errorCode != 0) {
                            Toast.makeText(RegisterActivity.this, "注册失败" + response.body().errorMsg, Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Toast.makeText(RegisterActivity.this, "注册成功:" + response.body().user.nickname, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("retrofit", t.getCause().getMessage());
                }
            });

        });
    }
}
