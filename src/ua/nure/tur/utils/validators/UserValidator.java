package ua.nure.tur.utils.validators;

import ua.nure.tur.entities.User;

public final class UserValidator {

    private static final String USERNAME_REGEXP = "[A-Za-z]([A-Za-z0-9]){5,19}";

    private static final String EMAIL_REGEXP = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";


    private static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])[0-9a-zA-Z]{6,}$";

    public static boolean validateUser(User user) {
        if (user.getUserName() == null || !user.getUserName().matches(USERNAME_REGEXP)) {
            return false;
        }
        if (user.getEmail() == null || !user.getEmail().matches(EMAIL_REGEXP)) {
            return false;
        }
        if (!validatePassword(user.getPassword())) {
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEXP);
    }
}
