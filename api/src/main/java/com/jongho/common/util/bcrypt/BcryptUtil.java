package com.jongho.common.util.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
