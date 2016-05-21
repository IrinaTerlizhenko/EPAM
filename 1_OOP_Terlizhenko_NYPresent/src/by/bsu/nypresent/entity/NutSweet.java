package by.bsu.nypresent.entity;

import by.bsu.nypresent.exception.ValidationException;
import by.bsu.nypresent.validator.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class NutSweet extends Sweet {
    private double nuts;

    public NutSweet() {
    }

    public NutSweet(double weight, double sugar, Date expiration, double nuts) throws ValidationException {
        super(weight, sugar, expiration);
        if (Validator.validateNuts(nuts)) {
            this.nuts = nuts;
        } else {
            throw new ValidationException();
        }
    }

    public double getNuts() {
        return nuts;
    }

    public void setNuts(double nuts) throws ValidationException {
        if (Validator.validateNuts(nuts)) {
            this.nuts = nuts;
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public String toString() {
        return "NutSweet: " + super.toString() + "nuts = " + nuts + "; ";
    }
}
