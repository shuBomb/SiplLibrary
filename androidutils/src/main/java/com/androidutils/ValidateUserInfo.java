package com.androidutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AndreBTS on 25/09/2015.
 */
public class ValidateUserInfo {
    public static boolean isEmailValid(String email) {
        //TODO change for your own logic
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPasswordMinimumEightCharactersLong(String password) {
        //TODO change for your own logic
        return password.length() >=8;
    }

    // validating username
    public static boolean isUsernameMinimumSixCharactersLong(String username) {
        if (username != null && username.length() > 6) {
            return true;
        }
        return false;
    }

    public static boolean isZipcodeValid(String zipCode){
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }
}
