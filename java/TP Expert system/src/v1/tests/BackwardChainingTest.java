package v1.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import v1.beans.BackwardChaining;
import v1.beans.Fact;
import v1.beans.Facts;
import v1.beans.ForwardChaining;
import v1.beans.ImportXML;
import v1.beans.Rules;

public class BackwardChainingTest
{

	public static void main(String[] args)
	{
		Facts baseFacts=new Facts();
		Rules baseRules=new Rules();
		new ImportXML("bases_xml/se-ville.xml",baseFacts,baseRules);
		System.out.println("Base de Faits : \n"+baseFacts);
		System.out.println("Base de Règles : \n"+baseRules);
		Fact faitAProuver=new Fact("ville méritant le voyage", true);
		System.out.println("Fait à prouver : "+faitAProuver);
		boolean res=new BackwardChaining(faitAProuver, baseFacts, baseRules).run();
		System.out.println("Chaînage arrière : "+res);
	}

}