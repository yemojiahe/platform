//该类是用来检验密码强度

package com.example.userlogin.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    public static boolean validatePassword(String password) {
        // 验证密码长度
        if (password.length() < 8) {
            return false;
        }

        // 验证字符组合
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else {
                hasSpecialChar = true;
            }
        }

        // 验证密码是否满足要求
        return hasUppercase && hasLowercase && hasNumber && hasSpecialChar;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] saltedPasswordBytes = new byte[passwordBytes.length + saltBytes.length];
            System.arraycopy(passwordBytes, 0, saltedPasswordBytes, 0, passwordBytes.length);
            System.arraycopy(saltBytes, 0, saltedPasswordBytes, passwordBytes.length, saltBytes.length);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedPasswordBytes);

            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        boolean isValid = false;
        try {
            // 提取存储的哈希值和盐
            String[] parts = hashedPassword.split("\\$");
            String hashString = parts[0];
            String saltString = parts[1];
            byte[] salt = Base64.getDecoder().decode(saltString);
            byte[] hash = Base64.getDecoder().decode(hashString);

            // 将盐与密码组合后进行哈希
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] newHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // 验证哈希值是否一致
            isValid = MessageDigest.isEqual(hash, newHash);
        } catch (NoSuchAlgorithmException e) {
            //直接使用 printStackTrace() 不够灵活和可维护。建议使用日志框架（如 SLF4J、Log4j 或 Logback）来记录异常信息
            e.printStackTrace();
        }
        return isValid;
    }


}

