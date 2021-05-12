package by.book.exception;

public class ServerErrorException extends Exception{
    @Override
    public String getMessage() {
        return "Server error";
    }
}
