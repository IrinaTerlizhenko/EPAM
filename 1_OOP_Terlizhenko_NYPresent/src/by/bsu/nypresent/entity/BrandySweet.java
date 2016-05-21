package by.bsu.nypresent.entity;

import by.bsu.nypresent.exception.ValidationException;
import by.bsu.nypresent.validator.Validator;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class BrandySweet extends Sweet {
    private double brandyWeight;
    private int proof;

    public BrandySweet() {
    }

    public BrandySweet(double weight, double sugar, Date expiration, double brandyWeight, int proof)
            throws ValidationException {
        super(weight, sugar, expiration);
        if (Validator.validateBrandyWeight(brandyWeight) &&
                Validator.validateProof(proof)) {
            this.brandyWeight = brandyWeight;
            this.proof = proof;
        } else {
            throw new ValidationException();
        }
    }

    public double getBrandyWeight() {
        return brandyWeight;
    }

    public void setBrandyWeight(double brandyWeight) throws ValidationException {
        if (Validator.validateBrandyWeight(brandyWeight)) {
            this.brandyWeight = brandyWeight;
        } else {
            throw new ValidationException();
        }
    }

    public int getProof() {
        return proof;
    }

    public void setProof(int proof) throws ValidationException {
        if (Validator.validateProof(proof)) {
            this.proof = proof;
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public String toString() {
        return "BrandySweet: " + super.toString() + "brandyWeight = " + brandyWeight + "; proof = " + proof + "%; ";
    }
}
