package com.example.applicationmoviesearch.activities;


import static com.example.applicationmoviesearch.activities.MainActivity.MOVIE_EXTRA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.User;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.LoginPageActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class LoginPageActivity extends AppCompatActivity {
    private EditText txtLogin;
    private EditText txtPassword;
    private Button btnRegister;
    private Button btnEnter;
    public LoginPageActivityViewModel loginPageActivityViewModel;
    private User loginUser;
    public static String USER_KEY ="user_key";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(MOVIE_EXTRA, 0);
        boolean dialogShown = sharedPreferences.getBoolean("dialogShown", false);
        if (dialogShown!=true) {
            Intent intent = new Intent(this,ActivityForViewPagerActivity.class);
            startActivity(intent);
            //Ecrive la valeur
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
        }
        setContentView(R.layout.activity_login_page);
        setTitle("");
        loginUser = (User) getIntent().getSerializableExtra(USER_KEY);
        loginPageActivityViewModel = new ViewModelProvider(this).get(LoginPageActivityViewModel.class);
        txtLogin = findViewById(R.id.edit_user_login);
        txtPassword = findViewById(R.id.edit_user_password);
        btnRegister = findViewById(R.id.btn_go_to_register);
        btnEnter = findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkingEditZone();
                isRegister();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginPageActivityViewModel.getLiveDataUser(this).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                RepositoryMovies.getInstance().newListUser = (ArrayList<User>) users;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RepositoryMovies.getInstance().getMyUserList();

    }

    public void checkingEditZone(){
        if(txtLogin.getText().toString() == ""){
            Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
        }
        if(txtPassword.getText().toString() == ""){
            Toast.makeText(this, "Password Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void isRegister() {
        boolean resultat = false;
        for (User user : RepositoryMovies.getInstance().newListUser) {
            if (txtLogin.getText().toString().equalsIgnoreCase(user.getLogin()) &&
                    txtPassword.getText().toString().equalsIgnoreCase(user.getPassword()))
            {
                resultat = true;
                loginUser =user;
            }
        }
        if (resultat == true) {
           Intent intent = new Intent(LoginPageActivity.this, MainActivity.class);
           intent.putExtra(USER_KEY, loginUser);
           Log.d("log", loginUser.getLogin());
           startActivity(intent);
           Toast.makeText(LoginPageActivity.this,"connecté",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginPageActivity.this, "Inconnu", Toast.LENGTH_SHORT).show();
        }
    }
}
