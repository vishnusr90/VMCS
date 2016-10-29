package sg.edu.nus.iss.vmcs.customer;

import sg.edu.nus.iss.vmcs.store.NoteStore;
import sg.edu.nus.iss.vmcs.store.MoneyAttribute;
import sg.edu.nus.iss.vmcs.store.NoteAttribute;

public class NoteReceiver extends MoneyReceiver{

	public final static double minLength = 5;
	public final static double maxLength = 100;

	public final static double minWidth = 5;
	public final static double maxWidth = 100;
	
	public NoteReceiver(NoteStore noteStore){
		super(noteStore);
	}

        @Override
	public boolean validateMoney(MoneyAttribute moneyAttr){
		NoteAttribute noteAttributes = null;
		try{
			noteAttributes = (NoteAttribute) moneyAttr;
		}catch(Exception e){

		}
		if(noteAttributes == null) return false;

		if(noteAttributes.getWidth() < minWidth || noteAttributes.getWidth() > maxWidth) return false;
		if(noteAttributes.getLength() < minLength || noteAttributes.getLength() > maxLength) return false;
		
		return true;
	}
}


