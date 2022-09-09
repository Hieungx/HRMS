package com.spring.XMLConfiguration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUtils {

	public static void getPropertiesXML() throws TransformerException, ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("company");
		doc.appendChild(rootElement);

		doc.createElement("staff");
		rootElement.appendChild(doc.createElement("staff"));

		// ...create XML elements, and others...

		// write dom document to a file
		try (FileOutputStream output = new FileOutputStream("C:\\Users\\nvvanh1\\Documents\\workspace-spring-tool-suite-4-4.12.0.RELEASE\\SpringJobSchedule\\src\\main\\resources\\variable.xml")) {
			writeXml(doc, output);
			System.out.println("WRITE OK");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeXml(Document doc, OutputStream output) throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		transformer.transform(source, result);

	}

	public static void main(String[] args) throws TransformerException, ParserConfigurationException {
		XMLUtils.getPropertiesXML();
	}

}
