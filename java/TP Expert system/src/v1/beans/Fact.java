package v1.beans;

public class Fact {
	private String label;
	private Boolean value;
	
	public Fact(String label,Boolean value)
	{
		this.label = label;
		this.value = value;
	}

	public String getLabel() 
	{
		return label;
	}

	public void setLabel(String label) 
	{
		this.label = label;
	}

	public Boolean getValue() 
	{
		return value;
	}

	public void setValue(Boolean value) 
	{
		this.value = value;
	}
	
	public Boolean is(Fact fact) {
		return fact.getLabel().equals(label);
	}
	
	public String toString() {
		return "" + getLabel() + " = " + getValue();
	}
}
