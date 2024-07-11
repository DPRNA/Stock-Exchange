package p1;

public class InvalidPriceOperation extends RuntimeException {
    InvalidPriceOperation(String message) {
        super(message);
    }
}
