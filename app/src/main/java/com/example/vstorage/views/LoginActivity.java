package com.example.vstorage.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.vstorage.R;
import com.example.vstorage.network.response.LoginResponse;
import com.example.vstorage.listener.Action;
import com.example.vstorage.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginViewModel viewModel;
    private EditText mail;
    private EditText passw;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        initViews();

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void initViews() {
        loginBtn = findViewById(R.id.btnSignIn);
        mail = findViewById(R.id.mailText);
        passw = findViewById(R.id.passText);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == loginBtn)
        {
            viewModel.logIn(mail.getText().toString(), passw.getText().toString(), new Action<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse model) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);

                    finish();
                }

                @Override
                public void onError(String error) {

                }
            });
        }
    }
}
