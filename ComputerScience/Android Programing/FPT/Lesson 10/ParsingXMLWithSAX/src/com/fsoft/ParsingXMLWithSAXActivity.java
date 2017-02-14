package com.fsoft;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ParsingXMLWithSAXActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        try {
            /**
            * Create a new instance of the SAX parser
            **/
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();
         
            URL url = new URL("http://www.xmlfiles.com/examples/cd_catalog.xml"); // URL of the XML
         
            /**
            * Create the Handler to handle each of the XML tags.
            **/
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(new InputSource(url.openStream()));
         
        } catch (Exception e) {
            System.out.println(e);
        }
        
//        TextView example[];
//        example = new TextView[data.getExample().size()];
//        View layout = findViewById(R.id.layout);
        setContentView(R.layout.main);
        
    }
}