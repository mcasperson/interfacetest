package interfaces;

import interfaces.services.HttpService;
import interfaces.services.XmlService;
import interfaces.services.impl.HttpServiceImpl;
import interfaces.services.impl.XmlServiceImpl;
import org.w3c.dom.Document;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * An example that demonstrates the use of interfaces and composition to build an application
 */
public class Main {
    /**
     * Pretend this is a a web service that returns XML
     */
    private static final String SOURCE_URL = "https://localhost/xmlEndpoint";
    /**
     * Our xslt file
     */
    private static final String STYLE_SHEET = "style.xsl";
    /**
     * The service used to perform HTTP operations
     */
    private static final HttpService HTTP_SERVICE = new HttpServiceImpl();
    /**
     * The service used to perform XML operations
     */
    private static final XmlService XML_SERVICE = new XmlServiceImpl();

    /**
     * Application entry point
     * @param args Application arguments
     */
    public static void main(final String[] args) {
        /*
            Contact the HTTP web server for the XML
         */
        final String serviceXml = HTTP_SERVICE.callHttpEndpoint(SOURCE_URL);

        /*
            Convert the result to an XML Document
         */
        final Document doc = XML_SERVICE.convertStringToDocument(serviceXml);

        /*
            Transform the document to include a new root element
         */
        final DOMResult transformedDom = XML_SERVICE.transformDom(doc, new StreamSource(new File(STYLE_SHEET).getAbsoluteFile()));

        /*
            Convert the transformed result back to a string
         */
        final String output = XML_SERVICE.convertNodeToString(transformedDom.getNode());

        /*
            Write the output to the console
         */
        System.out.println(output);
    }
}
