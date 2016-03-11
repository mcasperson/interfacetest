package interfaces.services.impl;

import interfaces.exceptions.HttpException;
import interfaces.services.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * An implementation of the HttpService using the Apache HTTP Client
 */
public class HttpServiceImpl implements HttpService {
    @Override
    public String callHttpEndpoint(final String endpoint) throws HttpException {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(endpoint);
            try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                final HttpEntity entity1 = response1.getEntity();
                final String serviceXml = EntityUtils.toString(entity1);
                return serviceXml;
            }
        } catch (final IOException ex) {
            throw new HttpException("HTTP-ERR-0001: An exception was raised while contacting the HTTP url " + endpoint, ex);
        }
    }
}
