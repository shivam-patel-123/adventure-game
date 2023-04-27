public class Utils {

    public static boolean validateName(String name) {
        return !name.matches("^[0-9]+(_|-|[a-z]|[A-Z]|[0-9])*");
    }
}
