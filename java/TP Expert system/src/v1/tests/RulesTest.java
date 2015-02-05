package v1.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import v1.beans.Rule;
import v1.beans.Rules;

public class RulesTest {

	@Test
	public void test() {

		Rules rulesBDD = new Rules();
		rulesBDD.add(new Rule(123L));
		rulesBDD.add(new Rule(1234L));
		
		Rules rules=new Rules(rulesBDD);
		rules.remove(123L);
		
		assertEquals(1, rules.size());
		assertEquals(2, rulesBDD.size());
		
	}

}
