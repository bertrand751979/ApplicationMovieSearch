package com.example.applicationmoviesearch.activities;


import static com.example.applicationmoviesearch.activities.LoginPageActivity.USER_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.applicationmoviesearch.InterfaceUser;
import com.example.applicationmoviesearch.R;
import com.example.applicationmoviesearch.models.User;
import com.example.applicationmoviesearch.repository.RepositoryMovies;
import com.example.applicationmoviesearch.viewModels.RegisterActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends AppCompatActivity {
    private EditText registerLogin;
    private EditText registerPassword;
    private Button registerDone;
    public RegisterActivityViewModel registerActivityViewModel;
    public User user;
    private InterfaceUser interfaceUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerActivityViewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);
        registerLogin = findViewById(R.id.register_edit_login);
        registerPassword = findViewById(R.id.register_edit_password);
        registerDone = findViewById(R.id.register_btn_done);
        registerDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewProfil();
            }
        });
        registerActivityViewModel.getLiveDataUser(this).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                RepositoryMovies.getInstance().myUserList = (ArrayList<User>) users;
                Toast.makeText(RegisterActivity.this, "size"+RepositoryMovies.getInstance().myUserList.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void createNewProfil(){
        User user = new User();
        user.setLogin(registerLogin.getText().toString());
        user.setPassword(registerPassword.getText().toString());
        registerActivityViewModel.addAnUser(user,RegisterActivity.this);
        Intent intent = new Intent(RegisterActivity.this, LoginPageActivity.class);
        intent.putExtra(USER_KEY,user);
        startActivity(intent);
        Toast.makeText(this, "login"+user.getLogin(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "password"+user.getPassword(), Toast.LENGTH_SHORT).show();
    }
}
