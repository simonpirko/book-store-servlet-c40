package by.book.service;

import by.book.exception.InvalidRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ValidationService {


    public static int validAndTransformStringToInt(String param) throws InvalidRequestException {
        validationParam(param);
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
    }

    public static long validAndTransformStringToLong(String param) throws InvalidRequestException {
        validationParam(param);
        try {
            return Long.parseLong(param);
        }catch (NumberFormatException e){
            throw new InvalidRequestException();
        }
    }

    public static String validationTrimString(String param) throws InvalidRequestException {
        validationParam(param);
        return param.trim();
    }

    public static LocalDate validAndTransformStringToLocalDate(String param) throws InvalidRequestException {
        validationParam(param);
        try {
            return LocalDate.parse(param);
        }catch (DateTimeParseException e){
            throw new InvalidRequestException();
        }
    }

    public static List<Long> validTransformArrayStringToListLong(String [] param) throws InvalidRequestException {
        if(param != null) {
            List<Long> authorsId = new ArrayList<>();
            try {
                for(String id : param){
                    validationParam(id);
                    authorsId.add(Long.parseLong(id));
                }
                return authorsId;
            }catch (NumberFormatException e){
                throw new NumberFormatException();
            }
        }else{
            throw new InvalidRequestException();
        }


    }

    private static void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim().equals("")) throw new InvalidRequestException();
    }
}
