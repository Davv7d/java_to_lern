package com.company;

import java.lang.management.PlatformLoggingMXBean;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
	// write your code here

        PlikiXML xml = new PlikiXML();
//        xml.SabeToXMLFile("Osoba.xml");
        xml.ReadXMLFile("a.xml");
    }
}
