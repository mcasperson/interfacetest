package interfaces.exceptions;

/**
 * Represents a failure to contact a HTTP server
 */
public class HttpException extends RuntimeException {
    public HttpException(final String message) {
        super(message);
    }

    public HttpException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
