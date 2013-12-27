
public class BadScaleException extends Exception {

	String msg;
	
	BadScaleException(){
	
		msg = "";
	}
	
	BadScaleException(String s){
		
		msg = s;
		
	}
	
	public String toString(){
		return "Improper scale, parameters must be of the form (noteName, scaleType)\n"
				+"noteName must be \'Ab\', \'A\', \'A#\', \'Bb\', \'B\', \'B#\', ... \'Gb\', \'G\', or \'G#\'\n"
				+"scaleType must be \'Major,\' or \'Minor\' - other scale types are not currently supported";
		
	}
	
	
}
