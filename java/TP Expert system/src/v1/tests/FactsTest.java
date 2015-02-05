package v1.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import v1.beans.Fact;
import v1.beans.Facts;

public class FactsTest {
	
	@Test
	public void factsAdd() 
	{
		Facts facts= new Facts();
		facts.add(new Fact("Test",true));
		assertEquals("Test", facts.get(0).getLabel());
		assertEquals(true, facts.get(0).getValue());
	}
	
	@Test
	public void factsAddAll()
	{
		Facts facts=new Facts();
		ArrayList<Fact> factsC = new ArrayList<Fact>();
		factsC.add(new Fact("Test1",true));
		factsC.add(new Fact("Test2",false));

		facts.addAll(factsC);
		assertEquals("Test1", facts.get(0).getLabel());
		assertEquals("Test2", facts.get(1).getLabel());
	}
	
	@Test 
	public void factsSize()
	{
		Facts facts=new Facts();
		ArrayList<Fact> factsC = new ArrayList<Fact>();
		factsC.add(new Fact("Test1",true));
		factsC.add(new Fact("Test2",false));
		facts.addAll(factsC);

		assertEquals(2, facts.size());
	}
	
	@Test
	public void factsGet() {
		Facts facts= new Facts();
		facts.add(new Fact("Test",true));
		facts.add(new Fact("Test1",false));

		assertEquals("Test1", facts.get("Test1").getLabel());
		assertEquals("Test", facts.get(0).getLabel());

		assertEquals(true, facts.belongTo(new Fact("Test",true)));
		assertEquals(false, facts.belongTo(new Fact("Test1",true)));
		
		facts.remove(1);
		assertEquals(false, facts.belongTo(new Fact("Test1",false)));
	}

}
