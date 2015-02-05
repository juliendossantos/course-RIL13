package v1.beans;

import java.util.Random;

public class ForwardChaining {
	
	private Fact fact;
	private Facts baseFacts;
	private Rules baseRules;
	private static Random rand = null;
	
	public ForwardChaining(Fact fact, Facts baseFacts, Rules baseRules) {
		this.fact = fact;
		this.baseFacts = baseFacts;
		this.baseRules = baseRules;
		if (rand==null)
			rand = new Random();
	}
	
	public boolean establishFact() {
		if(baseFacts.belongTo(fact))
			return true;
		
		return executeCycle(baseRules);
	}
	
	private boolean executeCycle(Rules baseRules) {
		
		if(baseRules.size() == 0)
			return false;

		Rules rules = new Rules(baseRules);
		
		int nb = rand.nextInt(rules.size());
		
		Rule rule = rules.get(nb);
		rules.remove(rule);
		
		System.out.println("Choix de la rêgle " + rule.getIndex());

		if(rule.isApplicable(baseFacts)) {
			
			System.out.println("Rêgle applicable");
			
			if(rule.getConclusion().is(fact)) {
				System.out.println(rule.getConclusion().getLabel());
				return true;
			}
			
			baseFacts.add(rule.getConclusion());
			System.out.println("Nouvelle BDF : "+baseFacts);
			this.baseRules.remove(rule.getIndex());
			
			return executeCycle(this.baseRules);
			
		}
		
		System.out.println("La rêgle n'est pas applicable");
		
		return executeCycle(rules);
	}
}
