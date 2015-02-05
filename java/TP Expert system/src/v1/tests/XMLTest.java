package v1.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import v1.beans.Rules;
import v1.beans.Facts;
import v1.beans.ImportXML;

public class XMLTest {

	@Test
	public void importBases() {
	    try {
	    	
	    	Facts baseFacts = new Facts();
	    	Rules baseRules = new Rules();
	    	new ImportXML("bases_xml/se-ville.xml",baseFacts,baseRules);

	    	assertEquals("monuments", baseFacts.get("monuments").getLabel());
	    	assertEquals(true, baseFacts.get(0).getValue());
	    	assertEquals("monuments", baseRules.get(3).getPermisses().get("monuments").getLabel());
	    	assertEquals("belle ville", baseRules.get(3).getConclusion().getLabel());
        
	    } catch (Exception e) {
        	e.printStackTrace();
        }	
	}

}
