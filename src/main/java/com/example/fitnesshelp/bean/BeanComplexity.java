package com.example.fitnesshelp.bean;

import com.example.fitnesshelp.exception.*;

public class BeanComplexity {
    public static void checkComplexity(String password) throws UpperCaseException, LowerCaseException, DigitException, LenghtException, SpecialException {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");

        if(!hasUppercase){
            throw new UpperCaseException("Password must contain an uppercase letter\n");
        }
        if(!hasLowercase){
            throw new LowerCaseException("Password must contain an lowercase letter\n");
        }
        if(!hasDigit){
            throw new DigitException("Password must contain a number\n");
        }
        if(!hasSpecial){
            throw new SpecialException("Password must contain special characters\n");
        }
        if(password.length() < 8){
            throw new LenghtException("Password must be at least 8 characters long\n");
        }

    }
}
