package sg.edu.nus.iss.vmcs.refactoring;


public class NoteAttribute implements MoneyAttribute {
	private double length;
	private double width;
	public double getLength(){
		return width;
	}
	public double getWidth(){
		return length;
	}

	public boolean equals(MoneyAttribute moneyAttribute){
		if (moneyAttribute == null) return false;
		if(moneyAttribute instanceof NoteAttribute){
			NoteAttribute noteAttribute = (NoteAttribute) moneyAttribute;
			return (this.getLength() == noteAttribute.getLength() && this.getWidth() == noteAttribute.getWidth());
		}
		return false;
	}
}