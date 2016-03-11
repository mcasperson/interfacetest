import interfaces.exceptions.XmlException;
import interfaces.services.XmlService;
import interfaces.services.impl.XmlServiceImpl;
import org.junit.Test;

/**
 * Tests for the XmlServiceImpl class
 */
public class XmlServiceImplTest {
    private static final XmlService XML_SERVICE = new XmlServiceImpl();

    @Test(expected = XmlException.class)
    public void testInvalidXmlConversion() {
        final String invalidXml = "<parent><child></parent>";
        XML_SERVICE.convertStringToDocument(invalidXml);
    }
}
