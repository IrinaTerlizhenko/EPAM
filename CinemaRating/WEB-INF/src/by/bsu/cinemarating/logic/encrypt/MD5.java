package by.bsu.cinemarating.logic.encrypt;

import by.bsu.cinemarating.exception.LogicException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 18.04.16
 * Time: 13:37
 * Class for quick and easy MD5 encryption.
 */
public class MD5 {
    private static final String MD5 = "MD5";

    /**
     * @param word String to encrypt
     * @return String that contains MD5 encryption of the given word
     * @throws LogicException
     */
    public static String encrypt(String word) throws LogicException {
        MessageDigest messageDigest;
        byte[] digest;
        try {
            messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.reset();
            messageDigest.update(word.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new LogicException(e);
        }
        BigInteger bigIntPass = new BigInteger(1, digest);
        return bigIntPass.toString(16);
    }
}
