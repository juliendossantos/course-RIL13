package v1.tests;

import static org.junit.Assert.*;
import v1.beans.Fact;

import org.junit.Test;

public class FactTest {

	@Test
	public void factConstruct()
	{
		Fact fact=new Fact("test",true);
		assertEquals("test", fact.getLabel());
		assertEquals(true, fact.getValue());
	}
	
	@Test
	public void factSetteurs()
	{
		Fact fact=new Fact("test",true);
		fact.setLabel("Test1");
		fact.setValue(false);
		assertEquals("Test1", fact.getLabel());
		assertEquals(false, fact.getValue());
	}
}
