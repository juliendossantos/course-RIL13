package v1.beans;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ImportXML {
	
	private String src;
	private Document doc;
	
	public ImportXML(String src,Facts baseFacts, Rules baseRules) {

		try {			
			doc = this.parseXML(src);
			this.setBaseFacts(baseFacts);
			this.setBaseRules(baseRules);

		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImportXML(String src) {
		try {
			
			doc = this.parseXML(src);
		
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Document parseXML(String src) throws SAXException, IOException, ParserConfigurationException {
		File fXmlFile = new File(src);
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	Document doc = dBuilder.parse(fXmlFile);

    	//optional, but recommended
    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    	doc.getDocumentElement().normalize();
		return doc;
	}

	public String getSrc() {
		return src;
	}

	public Document getDoc() {
		return doc;
	}
	
	private void setBaseFacts(Facts baseFacts) {
		
		NodeList nListFacts = doc.getElementsByTagName("fait");	     
    	
		for (int temp = 0; temp < nListFacts.getLength(); temp++) {

			Node nNode = nListFacts.item(temp);

    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {     
    			Element eElement = (Element) nNode;
    			baseFacts.add(new Fact(eElement.getAttribute("etiquette"),eElement.getAttribute("valeur").equals("true")));
    		}
    	
		}
		
	}
	
	private void setBaseRules(Rules baseRules) {
		NodeList nListRules = doc.getElementsByTagName("regle");	     
    	
		for (int temp = 0; temp < nListRules.getLength(); temp++) {

			Node nRule = nListRules.item(temp);

    		if (nRule.getNodeType() == Node.ELEMENT_NODE) {   
    			
    			Element eRule = (Element) nRule;
    			Rule r = new Rule(Long.parseLong(eRule.getAttribute("indice")));
    			
    			// Rajoute les fait à la rêgle
    			NodeList nListFacts = eRule.getElementsByTagName("hypothese");

    			for(int temp2 = 0; temp2 < nListFacts.getLength(); temp2++) {
    				Node nFact = nListFacts.item(temp2);
    				if (nFact.getNodeType() == Node.ELEMENT_NODE) {   
    					Element eFact = (Element) nFact;
    					Fact f = new Fact(eFact.getAttribute("etiquette"),Boolean.parseBoolean(eFact.getAttribute("valeur")));
    					r.addHypothesis(f);
    				}
    			}
    			
    			// Rajoute la conclusion à la rêgle
    			NodeList nListConclusion = eRule.getElementsByTagName("conclusion");
    			Node nConclusion = nListConclusion.item(0);
				Element eConclusion = (Element) nConclusion;
				Fact f = new Fact(eConclusion.getAttribute("etiquette"),Boolean.parseBoolean(eConclusion.getAttribute("valeur")));
				r.setConclusion(f);

    			baseRules.add(r);
    		}
    	
		}
	}
	
}
