package by.bsu.nypresent.action;

import by.bsu.nypresent.entity.BrandySweet;
import by.bsu.nypresent.entity.Caramel;
import by.bsu.nypresent.entity.NutSweet;
import by.bsu.nypresent.exception.ValidationException;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 25.02.16
 * Time: 3:34
 * To change this template use File | Settings | File Templates.
 */
public class SweetCreator {

    public static NutSweet createNutSweet()
            throws ValidationException {
        return new NutSweet();
    }

    public static NutSweet createNutSweet(double weight, double sugar, Date expiration, double nuts)
            throws ValidationException {
        return new NutSweet(weight, sugar, expiration, nuts);
    }

    public static Caramel createCaramel()
            throws ValidationException {
        return new Caramel();
    }

    public static Caramel createCaramel(double weight, double sugar, Date expiration, int vicosity)
            throws ValidationException {
        return new Caramel(weight, sugar, expiration, vicosity);
    }

    public static BrandySweet createBrandySweet()
            throws ValidationException {
        return new BrandySweet();
    }

    public static BrandySweet createBrandySweet
            (double weight, double sugar, Date expiration, double brandyWeight, int proof)
            throws ValidationException {
        return new BrandySweet(weight, sugar, expiration, brandyWeight, proof);
    }
}
