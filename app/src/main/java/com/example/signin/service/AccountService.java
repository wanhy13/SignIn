package com.example.signin.service;

import android.database.Cursor;

import com.example.signin.model.User;

import com.example.signin.database.AccountDB;

public class AccountService {
    AccountDB accountDB;

    public AccountService(AccountDB accountDB){
        this.accountDB=accountDB;
    }
    public boolean addNewUser(User user){
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        return accountDB.addUser(name,email,password);
    }
    public User getUser(String name){
        Cursor data = accountDB.getUser(name);
        data.moveToNext();
        if(data.getCount() == 0) return null;
        return new User(data.getString(0),data.getString(1),data.getString(2));
    }
    public void resetPassword(String name, String password){
        accountDB.updateUser(name,password);
    }
    public boolean verifyIdentity(String name, String email){
        return getUser(name).getEmail().equals(email);
    }
}
