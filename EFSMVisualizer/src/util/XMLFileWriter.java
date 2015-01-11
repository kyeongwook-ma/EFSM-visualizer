package util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

public class XMLFileWriter {
	public static void writeXMLFile(String str) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(docBuilder.parse(str));
		StreamResult result = new StreamResult(new File("D:\\workspace2\\RedJava\\WebContent\\doc\\com\\redjava\\java\\xml\\dom\\XmlDomCreate.xml"));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		 
		transformer.transform(source, result);	
	}
}
