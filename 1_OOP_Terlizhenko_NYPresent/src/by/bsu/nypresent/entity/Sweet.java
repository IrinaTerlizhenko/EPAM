package by.bsu.nypresent.entity;

import by.bsu.nypresent.exception.ValidationException;
import by.bsu.nypresent.validator.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class Sweet {
    private double weight;
    private double sugar;
    private Date expiration;

    public Sweet() {
    }

    public Sweet(double weight, double sugar, Date expiration) throws ValidationException {
        if (Validator.validateSweet(weight, sugar, expiration)) {
            this.sugar = sugar;
            this.weight = weight;
            this.expiration = expiration;
        } else {
            throw new ValidationException();
        }
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) throws ValidationException {
        if (Validator.validateSugar(sugar)) {
            this.sugar = sugar;
        } else {
            throw new ValidationException();
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws ValidationException {
        if (Validator.validateWeight(weight)) {
            this.weight = weight;
        } else {
            throw new ValidationException();
        }
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) throws ValidationException {
        if (Validator.validateExpiration(expiration)) {
            this.expiration = expiration;
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public String toString() {
        return "weight = " + weight + "; sugar = " + sugar + "; expiration = " + expiration + "; ";
    }
}
