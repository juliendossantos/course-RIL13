package v1.beans;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Facts extends ArrayList<Fact> {
	
	public Facts() {}

	public Facts(Facts facts) {
		for(int i = facts.size()-1; i >= 0; i--)
			this.add(facts.get(i));
	}

	public Fact get(String label) {
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			Fact fact = this.get(i);
			if(fact.getLabel().equals(label))
				return fact;
		}
		
		return null;
	}
	
	public Boolean belongTo(Fact factToTest) {
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			Fact fact = this.get(i);

			if(fact.getLabel().equals(factToTest.getLabel()) && fact.getValue()==factToTest.getValue())
				return true;
		}
		
		return false;
	}
	
	public String toString() {
		String s = "";
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			s += this.get(i) + (i == 0 ? "": " & ");
		
		}
		return s;
	}
	
//	public boolean add(Fact fact) {
//		if(isBelong(fact))
//			return false;
//		this.add(fact);
//		return true;
//	}
}
