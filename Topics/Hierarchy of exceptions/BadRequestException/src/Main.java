import java.io.UncheckedIOException;

// update the class
class BadRequestException extends java.lang.Exception {
    public BadRequestException(String message) {
        super(message);
    }
}