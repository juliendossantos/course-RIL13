package com.rila.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadXML {

	public ReadXML(String nomFichier) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document document = sb.build(new File(nomFichier));
		
		List<Element> stagiaires = document.getRootElement().getChild("stagiaires").getChildren();
		
		for(Element e:stagiaires){
			System.out.print(e.getAttributeValue("code") + "\t");
			System.out.println(e.getChildText("nom") + "\t");
			System.out.println(e.getChildText("prenom") + "\n");
		}
	}
	
	public static void main(String[] lapause) throws JDOMException, IOException {
		new ReadXML("ril13.xml");
	}
}
