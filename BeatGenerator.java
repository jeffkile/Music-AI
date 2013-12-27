import java.util.ArrayList;

public class BeatGenerator {
	
	ArrayList<Integer> onEvents;
	ArrayList<Integer> offEvents;
	ArrayList<Integer> values;
	BeatStates states; 
	
	public BeatGenerator(int feelChoice) {	
		onEvents = new ArrayList<Integer>();
		offEvents = new ArrayList<Integer>();
		values = new ArrayList<Integer>();
		states = new BeatStates(feelChoice);
		
		//number of ticks each note is held for
		//this is stored for when the note is actually played and
		//has no role in the heuristic
		// note - length pairs
		values.add(0, 16); //whole note
		values.add(1, 8); //half note
		values.add(2, 4); //quarter note
		values.add(3, 2); //eighth note
		values.add(4, 1); //sixteenth note
		values.add(5, 3); //dotted eighth note
	}
	
	public void setFeelState(int choice) {
		states.changeFeels(choice);
	}
	
	public void generateBeats() {	
		onEvents.clear();
		offEvents.clear();
		
		int maxCount = 16; //maximum number of units played: 16 = one measure
						    //16 units *16 measures = 256 units
		int length = 0;    //counts down how long a note has left to be played
		//boolean playing = false;  //tells noteOffEvent whether or not a note was playing before
		//int evaluateAt = 15; //after what count to evaluate beat values
		
		int time = 0; //counter for what time is currently on
		
		while(time < maxCount) {
			length = values.get(states.getCurrent()); //get length of note to play
			onEvents.add(time); //add time to ArrayList of onEvents
			
			time+= length; //add the length of the note to the time to signify the end of the note
			offEvents.add(time); //add time to ArrayList of offEvents

			states.getNextState(); //move pointer to of BeatStates to the next State
			
		}
		states.reEvaluate(); //at the end of the measure, reevaluate the rhythms
	}
	
	//returns an arrayList of onEvents for one measure - 16 ticks
	//will return values 0 - 15
	public ArrayList<Integer> getOnEvents() {
		ArrayList<Integer> tempOnEvents = onEvents;
		//onEvents.clear();
		
		return onEvents;
	}
	
	
	//returns an arrayList of offEvents for one measure - 16 ticks
	//will return values 0 - 15
	public ArrayList<Integer> getOffEvents() {
		ArrayList<Integer> tempOffEvents = offEvents;
		//offEvents.clear();
		
		return offEvents;
	}
}
