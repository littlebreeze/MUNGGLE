package com.munggle.util;

public class NicknameValidator {

    private static final String BLANK = " ";

    public static boolean isValidNickname(String nickname) {
        return !nickname.contains(BLANK);
    }
}
