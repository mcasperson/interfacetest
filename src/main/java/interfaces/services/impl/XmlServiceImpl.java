package interfaces.services.impl;

import interfaces.exceptions.XmlException;
import interfaces.services.XmlService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * An implementation of XmlService that uses the standard Java XML libraries to
 * manipulate XML strings and documents.
 */
public class XmlServiceImpl implements XmlService {
    @Override
    public Document convertStringToDocument(final String input) {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new InputSource(new StringReader(input)));
            return doc;
        } catch (final SAXException | ParserConfigurationException | IOException ex) {
            throw new XmlException("XML-ERR-0001: An exception was raised converting the following string to a document:\n " + input, ex);
        }
    }

    @Override
    public String convertNodeToString(final Node input) {
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final  Transformer transformer = transformerFactory.newTransformer();
            final StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(input), new StreamResult(writer));
            return writer.toString();
        } catch (final TransformerException ex) {
            throw new XmlException("XML-ERR-0002: An exception was raised converting a XML Document to a String", ex);
        }
    }

    @Override
    public DOMResult transformDom(final Document input, final Source xsltFile) {
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer(xsltFile);
            final DOMResult output = new DOMResult();
            transformer.transform(new DOMSource(input), output);
            return output;
        } catch (final TransformerException ex) {
            throw new XmlException("XML-ERR-0003: An exception was raised transforming a XML Document", ex);
        }
    }
}
