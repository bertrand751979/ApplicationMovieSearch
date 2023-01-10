package com.example.applicationmoviesearch.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="login")
    private String login;
    @ColumnInfo(name="password")
    private String password;
    @ColumnInfo(name="userLoginConnected")
    private String userLoginConnected;

    public User(Integer id, String login, String password, String userLoginConnected) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userLoginConnected = userLoginConnected;
    }

    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserLoginConnected() {
        return userLoginConnected;
    }

    public void setUserLoginConnected(String userLoginConnected) {
        this.userLoginConnected = userLoginConnected;
    }
}
