import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validation{
    private String emailAddress;
    public EmailValidator(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    @Override
    public boolean isValid() {

        return true;
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("...@...");
        Matcher matcher = pattern.matcher("kb@sdf");
        System.out.println(matcher.matches());
    }
}
