package com.company;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlikiXML {
    public PlikiXML(){
    }

    public  void SabeToXMLFile(String name){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("root");
            doc.appendChild(root);

            Element personE = doc.createElement("Osoba");
            personE.setAttribute("nr","1");
            root.appendChild(personE);

            Element nameE = doc.createElement("Imie");
            nameE.setTextContent("Jan");
            Element lastnameE = doc.createElement("Nazwisko");
            lastnameE.setTextContent("Kowalski");
            personE.appendChild(nameE);
            personE.appendChild(lastnameE);

            Element addressE = doc.createElement("Adres");
            personE.appendChild(addressE);

            Element stateE = doc.createElement("Ulica");
            stateE.setTextContent("Kwiatkowa");
            Element buildingE = doc.createElement("Budynek");
            buildingE.setAttribute("nr"," 12");
            buildingE.setAttribute("lok_nr","1");
            Element cityE  = doc.createElement("Miasto");
            cityE.setTextContent("Krakow");
            cityE.setAttribute("code", "32-100");
            addressE.appendChild(stateE);
            addressE.appendChild(buildingE);
            addressE.appendChild(cityE);

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            t.setOutputProperty(OutputKeys.METHOD, "XML");
            t.setOutputProperty(OutputKeys.INDENT,"yes");

            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(name)));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void ReadXMLFile(String name){
        try {
            FileInputStream f = new FileInputStream(name);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(f);

            Element root = doc.getDocumentElement();

//            System.out.println(root.toString());

            NodeList temp = root.getElementsByTagName("Osoba");
//            System.out.println(temp.getLength());
            Element tempE = (Element) temp.item(0);
//            System.out.println(tempE.getTagName());
//            System.out.println(tempE.getAttribute("nr"));
//            System.out.println("--------");

//            if(temp.item(0).getChildNodes().getLength() >0){
//                NodeList tempEE = temp.item(0).getChildNodes();
//                System.out.println(tempEE.getLength());
//                for (int i = 0; i < tempEE.getLength(); i++) {
//                    if (tempEE.item(i) instanceof  Element){
//                        Node node = tempEE.item(i);
//                        Element element = (Element) tempEE.item(i);
//                        System.out.println(node.getNodeName());
//                        System.out.println(node.getTextContent());
//                    }
//                }
//            }

            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();

            XPathExpression exp = xPath.compile("//Rates");
            NodeList listPerson = (NodeList) exp.evaluate(doc, XPathConstants.NODESET);
            System.out.println(listPerson);

            XPathExpression exp1 = xPath.compile("//Rate[contains(Code,'L')]/Currency");
            NodeList codelist = (NodeList) exp1.evaluate(doc, XPathConstants.NODESET);
            for (int z = 0; z < codelist.getLength(); z++){
                Element rateE = (Element) codelist.item(z);
//                System.out.println("Nazwa: ", + rateE.getAttributeNode());
                System.out.println(codelist.item(z).getTextContent());
            }
            System.out.println("__________________");
            XPathExpression exp2 = xPath.compile("//Rate[last()]/Currency");
            NodeList codelist1 = (NodeList) exp2.evaluate(doc, XPathConstants.NODESET);
            for (int z = 0; z < codelist1.getLength(); z++){
                Element rateE = (Element) codelist1.item(z);
//                System.out.println("Nazwa: ", + rateE.getAttributeNode());
                System.out.println(codelist1.item(z).getTextContent());
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }
}
