package by.book.exception;

public class NotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "No data found";
    }
}
