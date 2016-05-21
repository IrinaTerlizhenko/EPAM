package by.bsu.nypresent.validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class Validator {
    private static final int MAX_VICOSITY = 100;
    private static final int MAX_PROOF = 100;

    private static boolean isDouble(String word) {
        Pattern p = Pattern.compile("[1-9][\\d]*(\\.[\\d]*)?");
        Matcher m = p.matcher(word);
        return m.matches();
    }

    private static boolean isInteger(String word) {
        Pattern p = Pattern.compile("[1-9][\\d]*");
        Matcher m = p.matcher(word);
        return m.matches();
    }

    public static boolean validateWeight(String weight) {
        if (!isDouble(weight)) {
            return false;
        }
        return validateWeight(Double.parseDouble(weight));
    }

    public static boolean validateWeight(double weight) {
        return weight > 0;
    }

    public static boolean validateSugar(String sugar) {
        if (!isDouble(sugar)) {
            return false;
        }
        return validateSugar(Double.parseDouble(sugar));
    }

    public static boolean validateSugar(double sugar) {
        return sugar > 0;
    }

    public static boolean validateExpiration(Date expiration) {
        return expiration.after(new Date());
    }

    public static boolean validateSweet(double weight, double sugar, Date expiration) {
        return validateWeight(weight) &&
                validateSugar(sugar) &&
                validateExpiration(expiration);
    }

    public static boolean validateNuts(String nuts) {
        if (!isDouble(nuts)) {
            return false;
        }
        return validateNuts(Double.parseDouble(nuts));
    }

    public static boolean validateNuts(double nuts) {
        return nuts > 0;
    }

    public static boolean validateVicosity(String vicosity) {
        if (!isInteger(vicosity)) {
            return false;
        }
        return validateVicosity(Integer.parseInt(vicosity));
    }

    public static boolean validateVicosity(int vicosity) {
        return vicosity > 0 && vicosity < MAX_VICOSITY;
    }

    public static boolean validateBrandyWeight(String brandyWeight) {
        if (!isDouble(brandyWeight)) {
            return false;
        }
        return validateBrandyWeight(Double.parseDouble(brandyWeight));
    }

    public static boolean validateBrandyWeight(double brandyWeight) {
        return brandyWeight > 0;
    }

    public static boolean validateProof(String proof) {
        if (!isInteger(proof)) {
            return false;
        }
        return validateVicosity(Integer.parseInt(proof));
    }

    public static boolean validateProof(double proof) {
        return proof > 0 && proof < MAX_PROOF;
    }
}
