package v1.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import v1.beans.Fact;
import v1.beans.Facts;
import v1.beans.ForwardChaining;
import v1.beans.ImportXML;
import v1.beans.Rules;

public class ForwardChainingTest
{

	public static void main(String[] args)
	{
		Facts baseDeFaits=new Facts();
		Rules baseDeRegles=new Rules();
		new ImportXML("bases_xml/se-ville.xml",baseDeFaits,baseDeRegles);
		System.out.println("Base de Faits : \n"+baseDeFaits);
		System.out.println("Base de Règles : \n"+baseDeRegles);
		Fact faitAProuver = new Fact("ville méritant le voyage", true);
		System.out.println("Fait à prouver : " + faitAProuver);
		boolean res = new ForwardChaining(faitAProuver, baseDeFaits, baseDeRegles).establishFact();
		System.out.println("Chaînage avant : "+res);
	}

}