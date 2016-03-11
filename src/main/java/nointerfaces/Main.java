package nointerfaces;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Retrieve an XML file from a web server and parse it into an XML DOM
 */
public class Main {
    /**
     * This is a a web service that returns XML
     */
    private static final String SOURCE_URL = "https://localhost/xmlEndpoint";
    /**
     * Our xslt file
     */
    private static final String STYLE_SHEET = "style.xsl";

    /**
     * Application entry point
     * @param args Application arguments
     */
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        /*
            Contact the server and get the returned XML
         */
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(SOURCE_URL);
            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
                HttpEntity entity1 = response1.getEntity();
                String serviceXml = EntityUtils.toString(entity1);

                /*
                    Now convert into an XML dom
                 */
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;

                builder = factory.newDocumentBuilder();
                Document doc = builder.parse( new InputSource( new StringReader( serviceXml ) ) );

                /*
                    Now transform the document to include a new root element
                 */
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Source stylesheetSource = new StreamSource(new File(STYLE_SHEET).getAbsoluteFile());
                Transformer transformer = transformerFactory.newTransformer(stylesheetSource);
                DOMResult output = new DOMResult();
                transformer.transform(new DOMSource(doc), output);

                /*
                    Now dump as a string again
                 */

                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));

                System.out.println(writer.getBuffer().toString());
            }
        }
    }
}
