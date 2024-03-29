package com.example.fitnesshelp.utils;

import com.example.fitnesshelp.entities.Account;
import com.example.fitnesshelp.entities.State;
import com.example.fitnesshelp.entities.TypeOfUser;

public class UtilityAccess {
    // this is only used to set this static parameter for another class, so this can see the status of user
    private static String username = null;
    private static String email = null;
    private static String password = null;
    private static State state = State.NOT_LOGGED_IN;
    private static TypeOfUser typeOfUser = TypeOfUser.NORMAL;
    private UtilityAccess(){
        // is empty because this class provides only methods to set and get user status
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UtilityAccess.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UtilityAccess.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UtilityAccess.password = password;
    }

    public static TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public static void setTypeOfUser(TypeOfUser typeOfUser) {
        UtilityAccess.typeOfUser = typeOfUser;
    }

    public static State getState() {
        return state;
    }

    public static void setState(State state){
        UtilityAccess.state = state;
    }

    // new account added
    private static Account account;

    public static Account getAccount() {return account;}

    public static void setAccount(Account account) {
        UtilityAccess.account = account;
    }


}
