package by.book.exception;

public class IncorrectData extends Exception{
    public IncorrectData() {
    }

    public IncorrectData(String message) {
        super(message);
    }

    public IncorrectData(String message, Throwable cause) {
        super(message, cause);
    }
}
