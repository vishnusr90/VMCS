package sg.edu.nus.iss.vmcs.store;


public class NoteAttribute implements MoneyAttribute {
	private final double length;
	private final double width;
        
        public NoteAttribute(double length, double width){
            this.length = length;
            this.width = width;
        }
        
	public double getLength(){
		return width;
	}
	public double getWidth(){
		return length;
	}

        @Override
	public boolean equals(MoneyAttribute moneyAttribute){
		if (moneyAttribute == null) return false;
		if(moneyAttribute instanceof NoteAttribute){
			NoteAttribute noteAttribute = (NoteAttribute) moneyAttribute;
			return (this.getLength() == noteAttribute.getLength() && this.getWidth() == noteAttribute.getWidth());
		}
		return false;
	}
}