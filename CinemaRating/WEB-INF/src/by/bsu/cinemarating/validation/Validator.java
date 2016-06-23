package by.bsu.cinemarating.validation;

import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 11.04.16
 * Time: 5:08
 */
public class Validator { // todo
    private static final String REGEXP_LOGIN = "(\\w|\\d){1,30}";
    private static final String REGEXP_PASSWORD = "(\\w|\\d){3,20}";
    private static final String REGEXP_EMAIL_FORMAT = "(\\w|\\d)+@\\w+\\.\\w+";
    private static final String REGEXP_EMAIL_LENGTH = ".{5,40}";
    private static final String REGEXP_NAME = "([A-Z][A-Za-z]*([ -][A-Z][A-Za-z]*)*)|([А-Я][А-Яа-я]*([ -][А-Я][А-Яа-я]*)*)";
    private static final String REGEXP_NAME_LENGTH = ".{1,30}";
    private static final String REGEXP_SURNAME = "([A-Za-z]+([ -'][A-Za-z]+)*)|([А-Яа-я]+([ -'][А-Яа-я]+)*)";
    private static final String REGEXP_SURNAME_LENGTH = ".{1,30}";
    private static final String REGEXP_SURNAME_CAPITAL = "([A-ZА-Я].*)|(.+[ -'][A-ZА-Я].*)";
    private static final String REGEXP_MOVIE_NAME = ".{1,50}";
    private static final String REGEXP_MOVIE_DESCRIPTION = ".{0,1000}";
    private static final String REGEXP_YEAR = "188[8-9]|189\\d|19\\d{2}|[2-9]\\d{3}";
    private static final String REGEXP_COUNTRY = "([A-Z][A-Za-z]*(, [A-Z][A-Za-z]*)*)|([А-Я][А-Яа-я]*(, [А-Я][А-Яа-я]*)*)";
    private static final String REGEXP_REVIEW = ".{0,1000}";
    private static final int MIN_YEAR = 1888;
    private static final int MAX_YEAR = Year.now().getValue() + 1;

    /**
     * Validates login. A login consists of from 1 to 30 english letters of digits.
     *
     * @param login String containing login
     * @return true if login is valid, false otherwise
     */
    public static boolean validateLogin(String login) {
        return validate(login, REGEXP_LOGIN);
    }

    /**
     * Validates password. A password consists of from 3 to 20 english letters of digits.
     *
     * @param password String containing password
     * @return true if password is valid, false otherwise
     */
    public static boolean validatePassword(String password) {
        return validate(password, REGEXP_PASSWORD);
    }

    /**
     * Validates e-mail. An e-mail has format aaa@bbb.ccc Parts bbb, ccc consist of at least 1 english
     * letter. Part aaa consists of at least 1 english letter or digit. E-mail contains not more than 40 symbols.
     *
     * @param email String containing e-mail
     * @return true if e-mail is valid, false otherwise
     */
    public static boolean validateEmail(String email) {
        return validate(email, REGEXP_EMAIL_FORMAT, REGEXP_EMAIL_LENGTH);
    }

    /**
     * Validates name. A name contains either english or russian letters, spaces and hyphens. Every word after a
     * space or a hyphen begins with a capital letter. A name contains from 1 to 30 symbols.
     *
     * @param name String containing user name
     * @return true if name is valid, false otherwise
     */
    public static boolean validateName(String name) {
        return validate(name, REGEXP_NAME, REGEXP_NAME_LENGTH);
    }

    /**
     * Validates surname. A surname contains either english or russian letters, spaces, hyphens and apostrophes.
     * There must be at least one word starting with a capital letter. A surname contains from 1 to 30 symbols.
     *
     * @param surname String containing user surname
     * @return true if surname is valid, false otherwise
     */
    public static boolean validateSurname(String surname) {
        return validate(surname, REGEXP_SURNAME, REGEXP_SURNAME_CAPITAL, REGEXP_SURNAME_LENGTH);
    }

    /**
     * Validates movie name. A movie name contains from 1 to 50 symbols.
     *
     * @param movieName String containing movie name
     * @return true if name is valid, false otherwise
     */
    public static boolean validateMovieName(String movieName) {
        return validate(movieName, REGEXP_MOVIE_NAME);
    }

    /**
     * Validates movie description. A movie description contains from 0 to 1000 symbols.
     *
     * @param movieDescription String containing movie description
     * @return true if description is valid, false otherwise
     */
    public static boolean validateMovieDescription(String movieDescription) {
        return validate(movieDescription, REGEXP_MOVIE_DESCRIPTION);
    }

    /**
     * Validates movie year. A year is a positive number not less than 1888.
     *
     * @param year String containing movie premiere year
     * @return true if year is valid, false otherwise
     */
    public static boolean validateYear(String year) {
        return validate(year, REGEXP_YEAR);
    }

    /**
     * Validates movie year. A year is a positive number between 1888 and future year.
     *
     * @param year movie premiere year
     * @return true if year is valid, false otherwise
     */
    public static boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    /**
     * Validates movie production country. A country contains either english or russian letters. A country contains
     * one or more words starting with a capital letter, divided from each other by one comma and space.
     *
     * @param country String containing movie production countries (maybe several)
     * @return true if country is valid, false otherwise
     */
    public static boolean validateCountry(String country) {
        return validate(country, REGEXP_COUNTRY);
    }

    /**
     * todo
     *
     * @param review String containing movie review
     * @return true if review is valid, false otherwise
     */
    public static boolean validateReview(String review) {
        return validate(review, REGEXP_REVIEW);
    }

    /**
     * todo
     *
     * @param target             target string to validate
     * @param regularExpressions regular expressions that a target string needs to match
     * @return true if target string matches all the expressions, false otherwise
     */
    private static boolean validate(String target, String... regularExpressions) {
        boolean valid = true;
        Pattern pattern;
        Matcher matcher;
        for (String regExp : regularExpressions) {
            pattern = Pattern.compile(regExp);
            matcher = pattern.matcher(target);
            valid = valid && matcher.matches();
        }
        return valid;
    }
}
