package v1.beans;

import java.util.Random;

public class BackwardChaining {

	private Fact fact;
	private Facts baseFacts;
	private Rules baseRules;
	private static Random rand = null;
	
	public BackwardChaining(Fact fact, Facts baseFacts, Rules baseRules) {
		this.fact = fact;
		this.baseFacts = baseFacts;
		this.baseRules = baseRules;
		if (rand==null)
			rand = new Random();
	}
	
	public boolean run() {
		return establishFact(this.fact);
	}
	
	public boolean establishFact(Fact fact) {
		System.out.println("Etablir un fait : " + fact);
		
		if(baseFacts.belongTo(fact)) {
			System.out.println("		La base de faits contients le faits... succés !");
			return true;
		}
		
		return establish1(baseRules, fact);
	}
	
	public boolean establish1(Rules zeRules, Fact fact) {
		
		if(zeRules.size() == 0)
			return false;
		
		Rules rulesDup = new Rules(zeRules);
		
		int nb = rand.nextInt(rulesDup.size());
		
		Rule rule = rulesDup.get(nb);
		rulesDup.remove(rule.getIndex());

		System.out.println("Etablir 1 pour " + fact + " : chois de la rêgle (R" + rule.getIndex() + ") " + rule.getPermisses());

		if(rule.hasConclusion(fact))
			if(establish2(rule)) {
				System.out.println("		La base de faits contients le faits... succés !");
				return true;
			}
		
		System.out.println("	Cette rêgle n'as pas de conjonction avec le fait... echec !");
		return establish1(rulesDup, fact);
	}
	
	public boolean establish2(Rule rule) {
		Facts facts = rule.getPermisses();
		return establishConjunctionFacts(facts);
	}
	
	public boolean establishConjunctionFacts(Facts baseFacts) {
		
		System.out.println("Etablir conjonction des faits pour " + baseFacts);
		
		if(baseFacts.size() == 0) {
			return true;
		}
		
		int nb = rand.nextInt(baseFacts.size());
		
		Fact fact = baseFacts.get(nb);
		baseFacts.remove(fact);
		
		if(establishFact(fact) == false) {
			System.out.println("	Etablir conjonction des faits : Impossible d'établir le fait " + fact);
			return false;
		}
		
		return establishConjunctionFacts(baseFacts);
		
	}
}
