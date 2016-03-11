package interfaces.services;

/**
 * A service that provides a way to interact with HTTP servers
 */
public interface HttpService {
    String callHttpEndpoint(final String endpoint);
}
