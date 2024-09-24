/*Stores the user's information when logged in and creating accounts*/
package com.example.mapsappjava;

import androidx.annotation.NonNull;

public class Account {

    private final String firstName;
    private final String secondName;
    private final String email;
    private String password;

    //Setter for creating account
    public Account(String firstName, String secondName, String email, String setPassword) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = setPassword;
    }

    //Setter for storing logged in account
    public Account(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    public String accountToString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", setPassword='" + password + '\'';
    }

}
