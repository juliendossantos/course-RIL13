package v1.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import v1.beans.Fact;
import v1.beans.Rule;

public class RuleTest {

	@Test
	public void tesRuleConstruct() {
		Rule rule = new Rule(1);
		assertEquals(1, rule.getIndex());
	}

	@Test
	public void tesRuleAddFact() {
		Rule rule = new Rule(1);
		Fact f = new Fact("test",true);
		rule.addHypothesis(f);
		assertEquals(true, rule.getPermisses().belongTo(f));
	}

}
