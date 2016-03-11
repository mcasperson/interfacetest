package interfaces.services;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;

/**
 * A service that manipulates XML
 */
public interface XmlService {
    /**
     * Converts a XML string into a XML DOM
     * @param input The XML String
     * @return The XML DOM converted from the string
     */
    Document convertStringToDocument(final String input);

    /**
     * Converts an XML DOM into a String
     * @param input The XML DOM
     * @return The String version of the DOM
     */
    String convertNodeToString(final Node input);

    /**
     * Transforms a document with XSLT
     * @param input The document to be transformed
     * @param xsltFile The XSLT that is to be applied to the document
     * @return The transformed result
     */
    DOMResult transformDom(final Document input, final Source xsltFile);
}
