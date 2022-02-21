public class PhoneNumberValidation implements Validation{
    private String phoneNumber;
    public PhoneNumberValidation(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        cleanNumber();

    }

    private void cleanNumber() {
        phoneNumber = phoneNumber.replaceAll(" ", "");
        phoneNumber = phoneNumber.replaceAll("_", "");
        phoneNumber = phoneNumber.replaceAll("-", "");
    }
    @Override
    public boolean isValid() {
        if (ownsOnlyNumbers() && ownsNineNumbers()
        && mostNumberFrequencyLessThanFour() && basicValidation()) {
            return true;
        } return false;
    }

    private boolean mostNumberFrequencyLessThanFour() {
        if (ownsOnlyNumbers() && basicValidation() && ownsNineNumbers()) {
            int maxCounter = 0;
            char mostFrequentNumber;
            for (int i=0; i<phoneNumber.length(); i++) {
                int counter = 1;
                for (int j=i+1; j<phoneNumber.length(); j++) {
                    if (phoneNumber.charAt(j) == phoneNumber.charAt(i)) {
                        counter++;
                    }
                }
                if (counter > maxCounter) {
                    maxCounter = counter;
                    mostFrequentNumber = phoneNumber.charAt(i);
                }
            }

            if (maxCounter < 6) {
                return true;
            } return false;
        } return false;
    }

    private boolean ownsNineNumbers() {
        if (phoneNumber.length() != 9) {
            return false;
        } return true;

    }

    private boolean ownsOnlyNumbers() {
        if (basicValidation()) {
            for (int i=0; i<phoneNumber.length(); i++) {
                if (phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        } return false;
    }

    private boolean basicValidation() {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        } return true;
    }

    public static void main(String[] args) {
        PhoneNumberValidation phoneNumberValidation = new PhoneNumberValidation("781881487");
        System.out.println(phoneNumberValidation.isValid());
    }
}
