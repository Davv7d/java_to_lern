package com.company;

import org.json.JSONArray;

import java.lang.management.PlatformLoggingMXBean;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        PlikiXML xml = new PlikiXML();
//////        xml.SabeToXMLFile("Osoba.xml");
////        xml.ReadXMLFile("a.xml");

        WebPage wp = new WebPage();
        String temp = wp.GetDataFromWebPage("http://ux.up.krakow.pl/~pmazurek/java/read.php");
        JSON jsonObj = new JSON();
        jsonObj.GetDataFromJSON(temp);

        JSONArray tempArray = jsonObj.SetdateToJSONArray();
         wp.SendDataToWebPage(tempArray.toString(),"http://ux.up.krakow.pl/~pmazurek/java/add.php");



    }
}
