package by.bsu.nypresent.action;

import by.bsu.nypresent.collection.Present;
import by.bsu.nypresent.entity.BrandySweet;
import by.bsu.nypresent.entity.Caramel;
import by.bsu.nypresent.entity.NutSweet;
import by.bsu.nypresent.entity.Sweet;
import by.bsu.nypresent.exception.ValidationException;
import by.bsu.nypresent.type.SweetType;
import by.bsu.nypresent.validator.Validator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 25.02.16
 * Time: 2:42
 * To change this template use File | Settings | File Templates.
 */
public class SweetReader {

    static Logger log = Logger.getLogger(SweetReader.class);

    public static void readPresent(Present present, String filename) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            fileReader.readLine();
            String line = fileReader.readLine();
            while (line != null) {
                Sweet sweet = readSweet(line);
                if (sweet != null) {
                    present.add(sweet);
                }
                line = fileReader.readLine();
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private static Sweet readSweet(String line) {
        Sweet sweet = null;
        try {
            String[] params = line.split("[\\s]+");
            if (!Validator.validateWeight(params[1])) {
                throw new ValidationException("Not valid weight");
            }
            if (!Validator.validateSugar(params[2])) {
                throw new ValidationException("Not valid sugar");
            }
            double weight = Double.parseDouble(params[1]),
                    sugar = Double.parseDouble(params[2]);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date expiration = dateFormat.parse(params[3]);
            if (!Validator.validateExpiration(expiration)) {
                throw new ValidationException("Not valid expiration");
            }
            switch (SweetType.valueOf(params[0].toUpperCase())) {
                case NUTSWEET:
                    if (!Validator.validateNuts(params[4])) {
                        throw new ValidationException("Not valid nuts");
                    }
                    double nuts = Double.parseDouble(params[4]);
                    sweet = SweetCreator.createNutSweet();
                    NutSweet nutSweet = (NutSweet) sweet;
                    nutSweet.setNuts(nuts);
                    break;
                case CARAMEL:
                    if (!Validator.validateVicosity(params[4])) {
                        throw new ValidationException("Not valid vicosity");
                    }
                    int vicosity = Integer.parseInt(params[4]);
                    sweet = SweetCreator.createCaramel();
                    Caramel caramel = (Caramel) sweet;
                    caramel.setVicosity(vicosity);
                    break;
                case BRANDYSWEET:
                    if (!Validator.validateBrandyWeight(params[4])) {
                        throw new ValidationException("Not valid brandyWeight");
                    }
                    if (!Validator.validateProof(params[5])) {
                        throw new ValidationException("Not valid proof");
                    }
                    double brandyWeight = Double.parseDouble(params[4]);
                    int proof = Integer.parseInt(params[5]);
                    sweet = SweetCreator.createBrandySweet();
                    BrandySweet brandySweet = (BrandySweet) sweet;
                    brandySweet.setBrandyWeight(brandyWeight);
                    brandySweet.setProof(proof);
                    break;
                default:
                    throw new ValidationException("Unsupported sweet type");
            }
            if (sweet != null) {
                sweet.setWeight(weight);
                sweet.setSugar(sugar);
                sweet.setExpiration(expiration);
            }
        } catch (ValidationException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return sweet;
    }
}

