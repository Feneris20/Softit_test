package controllers;

import model.NewspaperInfo;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by ADOBNER on 2018-08-11.
 */
public class XmlFileController {

    private File xmlFile;
    private File xsdFile;

    private boolean valid = false;
    private String problems;

    public XmlFileController(File xmlFile, File xsdFile) {
        this.xmlFile = xmlFile;
        this.xsdFile = xsdFile;
        validate();
    }

    public NewspaperInfo getNewspaperInfo() throws ParserConfigurationException, IOException, SAXException {
        NewspaperInfo toReturn;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;

        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        String newspaper = document.getElementsByTagName("newspaperName").item(0).getTextContent();
        Integer dpi = Integer.parseInt(document.getElementsByTagName("screenInfo").item(0).getAttributes().getNamedItem("dpi").getTextContent());
        Integer width = Integer.parseInt(document.getElementsByTagName("screenInfo").item(0).getAttributes().getNamedItem("width").getTextContent());
        Integer height = Integer.parseInt(document.getElementsByTagName("screenInfo").item(0).getAttributes().getNamedItem("height").getTextContent());

        toReturn = new NewspaperInfo(xmlFile.getName(),newspaper,width,height,dpi);


        return toReturn;

    }

    private void validate() {
        DocumentBuilder parser = null;

        try {

            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = parser.parse(xmlFile);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Source schemaFile = new StreamSource(xsdFile);
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            validator.validate(new DOMSource(document));
            problems = xmlFile.getName() +" z plikiem wszystko OK.";
            valid = true;

        } catch (ParserConfigurationException e) {
            problems = e.toString();
        } catch (SAXException e) {
            problems = e.toString();
        } catch (IOException e) {
            problems = e.toString();
        }

    }

    public boolean isValid() {
        return valid;
    }

    public String getProblems() {
        return problems;
    }
}
