package v1.beans;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Rules extends ArrayList<Rule> {
	
	public Rules() {}
	
	public Rules(Rules rules)
	{
		for(int i = rules.size()-1; i >= 0; i--)
			this.add(rules.get(i));
	}
	public Rule get(Long index) 
	{
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			Rule rule = this.get(i);
			if(rule.getIndex() == index)
				return rule;
		}
		
		return null;
	}
	
	public Rules remove(Long index)
	{
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			Rule rule = this.get(i);
			if(rule.getIndex() == index) {
				this.remove(i);
				return this;
			}
		}
		
		return this;
	}
	
	public String toString() {
		String s = "";
		
		for(int i = this.size()-1;i >= 0; i--)
		{
			s += this.get(i) + "\n";
		}
				
		return s;
	}
}
