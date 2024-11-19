package com.alfa.utils;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringGenerator {

    public static String generateRandomStringByRegex(String regex, int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        while (stringBuilder.length() < length) {
            char randomChar = (char) (random.nextInt(95) + 32);
            Matcher matcher = pattern.matcher(Character.toString(randomChar));
            if (matcher.matches()) {
                stringBuilder.append(randomChar);
            }
        }
        return stringBuilder.toString();
    }
}
