package sg.edu.nus.iss.vmcs.store;


public class Note extends Money {
	
    public Note(int value,String name, NoteAttribute attributes){
            super(value, name, attributes);
    }

    public static Note createInvalid(){
        return new Note(-1, "Invalid", new NoteAttribute(-1, -1));
    }
}

