package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException {

        PhoneNumber [] temp = new PhoneNumber[phoneNumberCount];
        for (PhoneNumber each : temp) {
            each = createRandomPhoneNumber();
        }

        return temp;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() throws  InvalidPhoneNumberFormatException{

        String areaCode = "";
        String centralOfficeCode = "";
        String phoneLineCode = "";
        for (int i = 0; i < 3; i++)
            areaCode += RandomNumberFactory.createInteger(0,9);
        for (int i = 0; i < 3; i++)
            centralOfficeCode += RandomNumberFactory.createInteger(0,9);
        for (int i = 0; i < 4; i++)
            phoneLineCode += RandomNumberFactory.createInteger(0,9);


        return createPhoneNumberSafely(Integer.parseInt(areaCode), Integer.parseInt(centralOfficeCode), Integer.parseInt(phoneLineCode));
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) throws InvalidPhoneNumberFormatException{
        Boolean newphone1= false;

        String areaCode1= String.valueOf(areaCode);
        String centralOfficeCode1=String.valueOf(centralOfficeCode);
        String phoneLineCode1=String.valueOf(phoneLineCode);

        String newString = areaCode1 + centralOfficeCode1 + phoneLineCode1;

        if (areaCode1.length() == 1 && centralOfficeCode1.length() == 1 && phoneLineCode1.length() == 1) {
            newphone1 = true;

         while(areaCode1.length() < 3) {
             String x = "0";
             areaCode1 = x + areaCode1;
         }

         while(centralOfficeCode1.length() <3) {
             String x = "0";
             centralOfficeCode1 = x + centralOfficeCode1;
         }
         while(phoneLineCode1.length() <4) {
             String x = "0";
             phoneLineCode1 = x + phoneLineCode1;
         }

            try{
                if(newphone1)
                    throw new InvalidPhoneNumberFormatException();

                return new PhoneNumber("(" + areaCode1 + ")-" + centralOfficeCode1 + "-" + phoneLineCode1);
            }catch(InvalidPhoneNumberFormatException e) {
                newString += ": is not a valid phone number";
                logger.log(Level.WARNING, newString);
            }

            }
        return null;
    }



    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString)  throws InvalidPhoneNumberFormatException {

        String statement = "Create new number with value :" + phoneNumberString;

        logger.log(Level.INFO, statement);
        PhoneNumber num = new PhoneNumber(phoneNumberString);
        return num;

    }
}
