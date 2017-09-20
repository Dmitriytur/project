package ua.nure.tur.utils.validators;

import ua.nure.tur.entities.User;

import java.util.regex.Pattern;

public final class UserValidator {

    private static final String USERNAME_REGEXP = "[A-Za-z]([A-Za-z0-9]){5,19}";

    private static final String EMAIL_REGEXP = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";


    private static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])[0-9a-zA-Z]{6,}$";

    public static boolean validateUser(User user){
        if (user.getUserName() == null || !user.getUserName().matches(USERNAME_REGEXP)){
            System.out.println("name");
            return false;
        }
        if (user.getEmail() == null || !user.getEmail().matches(EMAIL_REGEXP)){
            System.out.println("email");
            return false;
        }
        if (user.getPassword() == null || !user.getPassword().matches(PASSWORD_REGEXP)){
            System.out.println("password");
            return false;
        }
        return true;
    }
}
