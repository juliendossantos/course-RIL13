package com.rila.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class WriteXML {

	private Document document;
	
	public WriteXML(String nomFichier) throws IOException {
		Element root = new Element("RIL13");
		document = new Document(root);

		Element stagiaires = new Element("stagiaires");
		root.addContent(stagiaires);
		
		Element s1 = new Element("stagiaire");
		Attribute atr = new Attribute("code", "BU01");
		
		s1.setAttribute(atr)
			.setAttribute(new Attribute("sexe", "Petit"))
			.addContent(new Element("nom").setText("BUR"))
			.addContent(new Element("prenom").setText("Jean-Vincent"));
		stagiaires.addContent(s1);
		
		stagiaires.addContent(new Element("stagiaire")
			.setAttribute(new Attribute("code", "CA01"))
			.setAttribute(new Attribute("sexe", "M"))
			.addContent(new Element("nom").setText("CARLESSO"))
			.addContent(new Element("prenom").setText("Mathieu"))
		);

		stagiaires.addContent(new Element("stagiaire")
		.setAttribute(new Attribute("code", "DS01"))
		.setAttribute(new Attribute("sexe", "M"))
		.addContent(new Element("nom").setText("DOS SANTOS"))
		.addContent(new Element("prenom").setText("Julien"))
		);

		stagiaires.addContent(new Element("stagiaire")
		.setAttribute(new Attribute("code", "HE01"))
		.setAttribute(new Attribute("sexe", "M"))
		.addContent(new Element("nom").setText("HERMEL"))
		.addContent(new Element("prenom").setText("Nicolas"))
		);
		
		stagiaires.addContent(new Element("stagiaire")
		.setAttribute(new Attribute("code", "MO01"))
		.setAttribute(new Attribute("sexe", "M"))
		.addContent(new Element("nom").setText("MOIZEAU"))
		.addContent(new Element("prenom").setText("Cedric"))
		);
		
		stagiaires.addContent(new Element("stagiaire")
		.setAttribute(new Attribute("code", "TR01"))
		.setAttribute(new Attribute("sexe", "M"))
		.addContent(new Element("nom").setText("TROQUEREAU"))
		.addContent(new Element("prenom").setText("Benjamin"))
		);

		affiche(System.out);
		affiche(new FileOutputStream(nomFichier));
	}

	private void affiche(OutputStream out) throws IOException {
		XMLOutputter sortie=new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, out);
	}
	
	public static void main(String[] tamere) throws IOException {
		new WriteXML("ril13.xml");
	}
}
