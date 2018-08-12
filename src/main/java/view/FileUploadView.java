package view;

import beans.NewspaperInfoMock;
import controllers.XmlFileController;
import model.NewspaperInfo;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

import javax.faces.bean.ManagedBean;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;


@ManagedBean
public class FileUploadView {

    private UploadedFile xmlFile;
    private UploadedFile xsdFile;
    private XmlFileController xmlFileController ;
    private NewspaperInfoMock newspaperInfoMock = new NewspaperInfoMock();

    private String errorLabel;

    private String result;

    private File convertUpladedFileToFile(UploadedFile uploadedFile){
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            // read this file into InputStream
            inputStream = uploadedFile.getInputstream();

            // write the inputStream to a FileOutputStream
            outputStream =
                    new FileOutputStream(new File(uploadedFile.getFileName()));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return new File(uploadedFile.getFileName());
    }

    public void upload() {
        if(xmlFile != null && xsdFile != null) {
            xmlFileController = new XmlFileController(convertUpladedFileToFile(xmlFile), convertUpladedFileToFile(xsdFile));
            errorLabel = xmlFileController.getProblems();
            if(xmlFileController.isValid()) {
                try {
                    NewspaperInfo info = xmlFileController.getNewspaperInfo();
                    result = info.toString();
                    newspaperInfoMock.insertNewspaperInfo(info);
                } catch (ParserConfigurationException e) {
                    result = e.toString();
                } catch (IOException e) {
                    result = e.toString();
                } catch (SAXException e) {
                    result = e.toString();
                }
            }
        }
    }

    public UploadedFile getXmlFile() {
        return xmlFile;
    }

    public UploadedFile getXsdFile() {
        return xsdFile;
    }

    public void setXmlFile(UploadedFile xmsFile) {
        this.xmlFile = xmsFile;
    }

    public void setXsdFile(UploadedFile xsdFile) {
        this.xsdFile = xsdFile;
    }

    public String getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel = errorLabel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
