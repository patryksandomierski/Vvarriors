package com.oliwa.web;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static SecureRandom random = new SecureRandom();

    private static Map<String, String> rememberedUsers = new HashMap<>();

    public static boolean isUserAuthenticated(String userName, String password) {
        return userName.equals("pat") && password.equals("san");
    }

    public static String rememberUser(String userName) {
        String randomId = new BigInteger(130, random).toString(32);
        rememberedUsers.put(randomId, userName);
        return randomId;
    }

    public static String getRememberedUser(String id) {
        return rememberedUsers.get(id);
    }

    public static void forgetUser(String id) {
        rememberedUsers.remove(id);
    }
}
