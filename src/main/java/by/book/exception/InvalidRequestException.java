package by.book.exception;

public class InvalidRequestException extends Exception{
    private String parameter;

    public InvalidRequestException(String parameter) {
        this.parameter = parameter;
    }

    public InvalidRequestException() {
    }

    public String getParameter() {
        return parameter;
    }

    @Override
    public String getMessage() {
        if(parameter == null) return "Invalid request parameters";
        return String.format("Invalid request parameters: %s", parameter);
    }
}
