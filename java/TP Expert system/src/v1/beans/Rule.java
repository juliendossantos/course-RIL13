package v1.beans;

public class Rule {
	
	private Facts hypothesis;
	private Fact conclusion;
	private long index;

	public Rule(long index) 
	{
		this.index = index;
		this.hypothesis = new Facts();
	}

	public Facts getPermisses()
	{
		return this.hypothesis;
	}

	public Fact getConclusion() 
	{
		return conclusion;
	}


	public long getIndex() 
	{
		return index;
	}

	public void addHypothesis(Fact hypothesis) 
	{
		this.hypothesis.add(hypothesis);
	}
	
	public void setHypothesis(Facts hypothesis) {
		this.hypothesis = hypothesis;
	}
	
	public void setConclusion(Fact conclusion) 
	{
		this.conclusion = conclusion;
	}

	public void apply(Facts baseFacts) 
	{
		baseFacts.add(this.conclusion);
	}
	
	public Boolean isApplicable(Facts baseFacts)
	{
		for(int i = hypothesis.size()-1;i >= 0; i--) {
			//System.out.println("R"+this.getIndex()+" confrontée au fait "+baseDesFaits.get(i));
			if(!baseFacts.belongTo(hypothesis.get(i)))
				return false;
		}
		return true;
	}
	
	public Boolean hasConclusion(Fact fact)
	{
		return (conclusion.getLabel().equals(fact.getLabel()) && conclusion.getValue() == fact.getValue());
	}
	
	public String toString() {
		String s = "SI ";
		for(int i = this.hypothesis.size()-1;i >= 0; i--) {
			s += this.hypothesis.get(i) + (i == 0 ? "":" & ");
		}
		
		s += " -> " + this.getConclusion();
		
		return s;
	}
	
}
