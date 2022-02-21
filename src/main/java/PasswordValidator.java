public class PasswordValidator implements Validation{
    private String password;
    public PasswordValidator(String password) {
        this.password = password;
    }
    @Override
    public boolean isValid() {
        if (ownsAtLeastThreeSmallCharacters() && 
                ownsAtLeastTwoBigCharacters() &&
                ownsAtLeastOneDigit() ) {
            return true;
        }  return false;
    }

    private boolean ownsAtLeastOneDigit() {
        if (extracted()) {
            return false;
        }

        for (int i=0; i<password.length(); i++) {
            try {
                if (Integer.parseInt(String.valueOf(password.charAt(i))) >= 0) {
                    return true;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return false;
    }

    private boolean ownsAtLeastTwoBigCharacters() {
        if (extracted()) {
            return false;
        }

        int counter = 0;
        for (int i=0; i<password.length(); i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                counter++;
            }
        }
        if (counter >= 2) {
            return true;
        }
        return false;
    }

    private boolean ownsAtLeastThreeSmallCharacters() {
        if (extracted()) return false;

        int counter = 0;
        for (int i=0; i<password.length(); i++) {
            if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                counter++;
            }
        }
        if (counter >= 3) {
            return true;
        }
        return false;
    }

    private boolean extracted() {
        if (password == null || password.length() == 0) {
            return true;
        }
        return false;
    }


}
