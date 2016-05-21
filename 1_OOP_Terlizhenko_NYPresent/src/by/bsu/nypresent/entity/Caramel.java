package by.bsu.nypresent.entity;

import by.bsu.nypresent.exception.ValidationException;
import by.bsu.nypresent.validator.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class Caramel extends Sweet {
    private int vicosity;

    public Caramel() {
    }

    public Caramel(double weight, double sugar, Date expiration, int vicosity) throws ValidationException {
        super(weight, sugar, expiration);
        if (Validator.validateVicosity(vicosity)) {
            this.vicosity = vicosity;
        } else {
            throw new ValidationException();
        }
    }

    public double getVicosity() {
        return vicosity;
    }

    public void setVicosity(int vicosity) throws ValidationException {
        if (Validator.validateVicosity(vicosity)) {
            this.vicosity = vicosity;
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public String toString() {
        return "Caramel: " + super.toString() + "vicosity = " + vicosity + "%; ";
    }
}
