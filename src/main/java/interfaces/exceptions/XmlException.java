package interfaces.exceptions;

/**
 * Represents an error working with XML
 */
public class XmlException extends RuntimeException {
    public XmlException(final String message) {
        super(message);
    }

    public XmlException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
