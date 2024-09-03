package com.example.mapsappjava;

import androidx.annotation.NonNull;

public class Account {

    private String firstName;
    private String secondName;
    private String email;
    private String password;

    public Account(String firstName, String secondName, String email, String setPassword) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = setPassword;
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

    public void SetPassword(String setPassword) {
        this.password = setPassword;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
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
