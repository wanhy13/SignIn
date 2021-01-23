package com.example.signin.service;

import android.database.Cursor;

import com.example.signin.model.User;

import com.example.signin.database.AccountDB;

public class AccountService {
    AccountDB accountDB;

    public AccountService(AccountDB accountDB){
        this.accountDB=accountDB;
    }

    /**
     * Add new user to the database
     * @param user User
     * @return true if user successfully added
     */
    public boolean addNewUser(User user){
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        return accountDB.addUser(name,email,password);
    }

    /**
     * Get user records
     * @param name String
     * @return return the information of user User
     */
    public User getUser(String name){
        Cursor data = accountDB.getUser(name);
        data.moveToNext();
        if(data.getCount() == 0) return null;
        return new User(data.getString(0),data.getString(1),data.getString(2));
    }

    /**
     * Reset the pasword
     * @param name String
     * @param password String
     */
    public void resetPassword(String name, String password){
        accountDB.updateUser(name,password);
    }

    /**
     * Compare the user email and username to set the Authentication to reset password.
     * @param name String
     * @param email String
     * @return true fi email and username match
     */
    public boolean verifyIdentity(String name, String email){
        return getUser(name).getEmail().equals(email);
    }
}
