package com.munggle.util;

public class PasswordValidator {

    private static final String BLANK = " ";
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 15;

    public static boolean isValidPassword(String password) {
        return MIN_LENGTH <= password.length() && password.length() <= MAX_LENGTH
                && !password.contains(BLANK);
    }
}
