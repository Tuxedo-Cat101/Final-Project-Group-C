
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexverifier {

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }
    private static final String PHONE_NUMBER_REGEX = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    private static final String PAYRATE_REGEX = "^\\$\\d{1,3}(,\\d{3})*(\\.\\d{2})?$";

    public static boolean isValidPayRate(String payRate) {
        if (payRate == null || payRate.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(PAYRATE_REGEX);
        Matcher matcher = pattern.matcher(payRate);
        return matcher.matches();
    }
}