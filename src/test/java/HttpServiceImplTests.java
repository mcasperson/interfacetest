import interfaces.exceptions.HttpException;
import interfaces.services.HttpService;
import interfaces.services.impl.HttpServiceImpl;
import org.junit.Test;

/**
 * Test the HTTP service
 */
public class HttpServiceImplTests {
    private static final HttpService HTTP_SERVICE = new HttpServiceImpl();
    private static final String SOURCE_URL = "https://localhost/xmlEndpoint";

    /**
     * We expect an exception to be thrown for a HTTP address that is not contactable
     * or is unavailable
     */
    @Test(expected = HttpException.class)
    public void testBadUrl() {
        HTTP_SERVICE.callHttpEndpoint("http://256.256.256.256");
    }

    /**
     * How well does this implementation scale?
     */
    @Test
    public void testScale() {
        for (int i = 0; i < 100000; ++i) {
            HTTP_SERVICE.callHttpEndpoint(SOURCE_URL);
        }
    }
}
