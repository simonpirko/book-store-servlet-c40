package by.book.exception;

public class DuplicateDataException extends Exception{
    private String dataName;

    public DuplicateDataException(String dataName) {
        this.dataName = dataName;
    }

    public DuplicateDataException() {
    }

    public String getDataName() {
        return dataName;
    }

    @Override
    public String getMessage() {
        if (dataName == null) return "Duplicate data";
        return String.format("Duplicate data: %s", dataName);
    }
}
