import java.util.ArrayList;
import java.util.Random;

public class BeatStates {
  private boolean DEBUG;
  
	/*****
	 * Determines the feel of the player
	 *****/
	private enum FeelState {VERY_SLOW, SLOW, MODERATE, FAST, QUICK, MIX}
	
	
	/*
	 * ArrayLists containing all possible states from each beat value
	 */
	private  ArrayList<ArrayList<Beat>> w;
	private  ArrayList<ArrayList<Beat>> h;
	private  ArrayList<ArrayList<Beat>> q;
	private  ArrayList<ArrayList<Beat>> e;
	private  ArrayList<ArrayList<Beat>> s;
	private  ArrayList<ArrayList<Beat>> de;
	
	/*
	 * Keeps an array of all the beat states for parsing through
	 */
	private  ArrayList<Beat> allW;
	private  ArrayList<Beat> allH;
	private  ArrayList<Beat> allQ;
	private  ArrayList<Beat> allE;
	private  ArrayList<Beat> allS;
	private  ArrayList<Beat> allDe;
	
	/*
	 * current state
	 */
	private  String valueState;
	private  int timeState;
	
	/*
	 * current feel state
	 */
	
	/*
	 * sequence memory
	 */
	private  ArrayList<Beat> memory;
	
	private  FeelState feelState;

  public BeatStates(int feelChoice, boolean debug) {
    this(feelChoice);
    this.DEBUG = debug;
  }
	
	public BeatStates(int feelChoice) {
    this.DEBUG = false;

		w = new ArrayList<ArrayList<Beat>>();
		h = new ArrayList<ArrayList<Beat>>();
		q = new ArrayList<ArrayList<Beat>>();
		e = new ArrayList<ArrayList<Beat>>();
		s = new ArrayList<ArrayList<Beat>>();
		de = new ArrayList<ArrayList<Beat>>();
		
		allW = new ArrayList<Beat>();
		allH = new ArrayList<Beat>();
		allQ = new ArrayList<Beat>();
		allE = new ArrayList<Beat>();
		allS = new ArrayList<Beat>();
		allDe = new ArrayList<Beat>();
		
		memory = new ArrayList<Beat>();
		
		Random random = new Random();
		 
		//should give a random number between 0 and 4
		int choice =  random.nextInt(6); 
		
		//select start state randomly
		switch(choice) {
			case 0: valueState = "w"; break;
			case 1: valueState = "h"; break;
			case 2: valueState = "q"; break;
			case 3: valueState = "e"; break;
			case 4: valueState = "s"; break;
			case 5: valueState = "de"; break;
		}
		timeState = 0;
		
    if (DEBUG) {
		  System.out.println(valueState + timeState + "\n");
    }

		//initialize rest of states
		initialize();
		
		//determine initial feel
		//Random random2 = new Random();
		//int feelChoice = random2.nextInt(4);
		//System.out.println("feel choice: " + feelChoice);
		switch(feelChoice) {
			case 0: 
        feelState = FeelState.VERY_SLOW;
        break;
			case 1: 
        feelState = FeelState.SLOW; 
        break;
			case 2:
        feelState = FeelState.MODERATE;
        break;
			case 3:
        feelState = FeelState.FAST;
        break;
			case 4:
        feelState = FeelState.QUICK;
        break;
			case 5:
        feelState = FeelState.MIX;
        break;
		}

    if (DEBUG) {
      System.out.println(feelState.name());
    }
		
		//set heuristic values of whole note states
		if(feelState.equals(FeelState.VERY_SLOW)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(30);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(25);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(10);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(4);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(2);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(10);
			}
		}
		
		if(feelState.equals(FeelState.SLOW)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(20);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(17);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(15);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(7);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(4);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(15);
			}
		}
		if(feelState.equals(FeelState.MODERATE)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(3);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(7);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(30);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(20);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(20);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(30);
			}
		}
		if(feelState.equals(FeelState.FAST)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(2);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(5);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(15);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(20);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(40);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(15);
			}
		}
		
		if(feelState.equals(FeelState.QUICK)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(1);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(2);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(5);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(30);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(50);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(5);
			}
		}
		
		//Values offset because eighth notes are least likely to be played of all
		if(feelState.equals(FeelState.MIX)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).addValue(1);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).addValue(1);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).addValue(2);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).addValue(4);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).addValue(8);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(2);
			}
		}
	}
	
	//returns the current state
	public int getCurrent() {
		int note = 0;
		if(valueState.equals("w")) {
			note = 0;  //tells CreateSequence to get value at 0 (whole note)
		}
		if(valueState.equals("h")) {
			note = 1; //value at 1 (half note)
		}
		if(valueState.equals("q")) {
			note = 2;
		}
		if(valueState.equals("e")) {
			note = 3;
		}
		if(valueState.equals("s")) {
			note = 4;
		}
		if(valueState.equals("de")) {
			note = 5;
		}
		
		//the beats added to memory have no affect on heuristics
		memory.add(new Beat(valueState, timeState));
		
		return note;
	}
	
	//returns the next state
	public void getNextState() {
		ArrayList<Beat> availableStates = new ArrayList<Beat>();
		int sizeOfState;
		
		//Get a list of available states given current State
		if(valueState.equals("w")) {
			availableStates = w.get(timeState);
		}
		if(valueState.equals("h")) {
			availableStates = h.get(timeState);
		}
		if(valueState.equals("q")) {
			availableStates = q.get(timeState);
		}
		if(valueState.equals("e")) {
			availableStates = e.get(timeState);
		}
		if(valueState.equals("s")) {
			availableStates = s.get(timeState);
		}
		if(valueState.equals("de")) {
			availableStates = de.get(timeState);
		}
		sizeOfState = availableStates.size();
		int sum = 0;
		
		//find sum of all values in the list of available states
		for(int i = 0; i < sizeOfState; i++) {
			sum += availableStates.get(i).getValue();
		}
		
		//FOR TESTING PERCENTAGES
		/*
		for(int i = 0; i < sizeOfState; i++) {
			System.out.print(availableStates.get(i).getNote());
			System.out.print(availableStates.get(i).getTime() + " = ");
			System.out.println((((double)(availableStates.get(i).getValue() / (double)sum))*100.0));
		}
		*/
		
	
		//finds the next Value with a weight possibility
		Random random3 = new Random();
		int randomNum = random3.nextInt(sum); //picks a number from 0 to sum-1
		int counter = 0;  //which state it's in
		int nextState = 0;
		int percentCount = 0;
		//System.out.println("Sum: " + sum);
		//System.out.println("Random Num: " + randomNum);
		//System.out.println("Counter: " + counter);
		
		//bucket type selection, with each bucket having certain probability
		for(int i = 0; i < 100; i++) {
			//System.out.println("if " + i + " <= " + (((double)(availableStates.get(counter).getValue() / (double)sum))*100.0));
			if(percentCount >= (((double)(availableStates.get(counter).getValue() / (double)sum))*100.0)) {  //gets the heuristic value and finds the probability that will be picked
				counter++;
				percentCount = 0;
				//System.out.println("Counter: " + counter);
			}
			if(i == randomNum) {
				nextState = counter;
			}
			percentCount++;
		}
		
		valueState = availableStates.get(nextState).getNote();
		timeState = availableStates.get(nextState).getTime();
    if (DEBUG) {
		  System.out.println("Next Note: " + valueState + timeState);
    }
	}
	
	/*
	 * Adds values to notes based on previous notes played
	 */
	public void reEvaluate() {
		for(int i = 0; i < memory.size(); i++) {
			String tempNote = memory.get(i).getNote();
			int tempTime = memory.get(i).getTime();
			
			if(tempNote.equals("w")) {
				allW.get(tempTime).addValue(20);
			}
			if(tempNote.equals("h")) {
				allH.get(tempTime).addValue(25);
			}
			if(tempNote.equals("q")) {
				allQ.get(tempTime).addValue(30);
			}
			if(tempNote.equals("e")) {
				allE.get(tempTime).addValue(35);
			}
			if(tempNote.equals("s")) {
				allS.get(tempTime).addValue(50);
			}if(tempNote.equals("de")) {
				allDe.get(tempTime).addValue(50);
			}
		}
		
		//clears memory to start over
		memory.clear();
	}
	
	//this method basically wipes all heuristic values and starts over with a new feel
	public void changeFeels(int feelChoice) {
		
		switch(feelChoice) {
			case 0:
        feelState = FeelState.VERY_SLOW;
        break;
			case 1:
        feelState = FeelState.SLOW; 
        break;
			case 2:
        feelState = FeelState.MODERATE;
        break;
			case 3:
        feelState = FeelState.FAST;
        break;
			case 4:
        feelState = FeelState.QUICK; 
        break;
			case 5:
        feelState = FeelState.MIX;
        break;
		}

    if (DEBUG) {
      System.out.println(feelState.name());
    }
	
		//set heuristic values of whole note states
		if(feelState.equals(FeelState.VERY_SLOW)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(30);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(25);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(10);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(4);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(2);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(10);
			}
		}
		
		if(feelState.equals(FeelState.SLOW)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(20);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(17);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(15);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(7);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(4);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(15);
			}
		}
		if(feelState.equals(FeelState.MODERATE)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(3);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(7);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(30);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(20);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(20);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(30);
			}
		}
		if(feelState.equals(FeelState.FAST)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(2);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(5);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(15);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(20);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(40);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(15);
			}
		}
		
		if(feelState.equals(FeelState.QUICK)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(20);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(17);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(15);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(7);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(4);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(15);
			}
		}
		
		//Values offset because eighth notes are least likely to be played of all
		if(feelState.equals(FeelState.MIX)) {
			for(int i = 0; i < allW.size(); i++) {
				allW.get(i).clear();
				allW.get(i).addValue(1);
			}
			for(int i = 0; i < allH.size(); i++) {
				allH.get(i).clear();
				allH.get(i).addValue(1);
			}
			for(int i = 0; i < allQ.size(); i++) {
				allQ.get(i).clear();
				allQ.get(i).addValue(2);
			}
			for(int i = 0; i < allE.size(); i++) {
				allE.get(i).clear();
				allE.get(i).addValue(4);
			}
			for(int i = 0; i < allS.size(); i++) {
				allS.get(i).clear();
				allS.get(i).addValue(8);
			}
			for(int i = 0; i < allDe.size(); i++) {
				allDe.get(i).addValue(2);
			}
		}
	}
	
	
	
	/*
	 * Add all the the beats that can be played from the current state
	 */
	public void initialize() {
		
		//initialize each individual beat
		//each beat heuristic value starts at 0
		//add each beat to the array of beats for looping through
		Beat w0 = new Beat("w", 0);
		allW.add(w0);
		Beat h0 = new Beat("h", 0);
		allH.add(h0);
		Beat h1 = new Beat("h", 1);
		allH.add(h1);
		Beat h2 = new Beat("h", 2);
		allH.add(h2);
		Beat q0 = new Beat("q", 0);
		allQ.add(q0);
		Beat q1 = new Beat("q", 1);
		allQ.add(q1);
		Beat q2 = new Beat("q", 2);
		allQ.add(q2);
		Beat q3 = new Beat("q", 3);
		allQ.add(q3);
		Beat e0 = new Beat("e", 0);
		allE.add(e0);
		Beat e1 = new Beat("e", 1);
		allE.add(e1);
		Beat e2 = new Beat("e", 2);
		allE.add(e2);
		Beat e3 = new Beat("e", 3);
		allE.add(e3);
		Beat e4 = new Beat("e", 4);
		allE.add(e4);
		Beat e5 = new Beat("e", 5);
		allE.add(e5);
		Beat e6 = new Beat("e", 6);
		allE.add(e6);
		Beat e7 = new Beat("e", 7);
		allE.add(e7);
		Beat s0 = new Beat("s", 0);
		allS.add(s0);
		Beat s1 = new Beat("s", 1);
		allS.add(s1);
		Beat s2 = new Beat("s", 2);
		allS.add(s2);
		Beat s3 = new Beat("s", 3);
		allS.add(s3);
		Beat s4 = new Beat("s", 4);
		allS.add(s4);
		Beat s5 = new Beat("s", 5);
		allS.add(s5);
		Beat s6 = new Beat("s", 6);
		allS.add(s6);
		Beat s7 = new Beat("s", 7);
		allS.add(s7);
		Beat s8 = new Beat("s", 8);
		allS.add(s8);
		Beat s9 = new Beat("s", 9);
		allS.add(s9);
		Beat s10 = new Beat("s", 10);
		allS.add(s10);
		Beat s11 = new Beat("s", 11);
		allS.add(s11);
		Beat s12 = new Beat("s", 12);
		allS.add(s12);
		Beat s13 = new Beat("s", 13);
		allS.add(s13);
		Beat s14 = new Beat("s", 14);
		allS.add(s14);
		Beat s15 = new Beat("s", 15);
		allS.add(s15);
		Beat de0 = new Beat("de", 0);
		allDe.add(de0);
		Beat de1 = new Beat("de", 1);
		allDe.add(de1);
		Beat de2 = new Beat("de", 2);
		allDe.add(de2);
		Beat de3 = new Beat("de", 3);
		allDe.add(de3);
		
		/*
		 * Add possible beat states that can come after the current state
		 */
		//W0 - N
		w.add(0, new ArrayList<Beat>());
		w.get(0).add(w0);
		w.get(0).add(h0);
		w.get(0).add(q0);
		w.get(0).add(e0);
		w.get(0).add(s0);
		w.get(0).add(de0);
		
		//H0 - H2, Q2, E4, S8
		h.add(0, new ArrayList<Beat>());
		h.get(0).add(h2);
		h.get(0).add(q2);
		h.get(0).add(e4);
		h.get(0).add(s8);
		h.get(0).add(de2);
		
		//Q0 - H1, E2, Q1, S4
		q.add(0, new ArrayList<Beat>());
		q.get(0).add(h1);
		q.get(0).add(e2);
		q.get(0).add(q1);
		q.get(0).add(s4);
		q.get(0).add(de1);
		
		//E0 - E1, S2
		e.add(0, new ArrayList<Beat>());
		e.get(0).add(e1);
		e.get(0).add(s2);
		
		//S0 - S1
		s.add(0, new ArrayList<Beat>());
		s.get(0).add(s1);

		//H1 - Q3, E6, S12
		h.add(1, new ArrayList<Beat>());
		h.get(1).add(q3);
		h.get(1).add(de3);
		h.get(1).add(e6);
		h.get(1).add(s12);
		
		//Q1 - H2, Q2, E4, S8
		q.add(1, new ArrayList<Beat>());
		q.get(1).add(h2);
		q.get(1).add(q2);
		q.get(1).add(e4);
		q.get(1).add(s8);
		
		//E1 - H1, Q1, E2, S4
		e.add(1, new ArrayList<Beat>());
		e.get(1).add(h1);
		e.get(1).add(q1);
		e.get(1).add(de1);
		e.get(1).add(e2);
		e.get(1).add(s4);
		
		//S1 - E1, S2
		s.add(1, new ArrayList<Beat>());
		s.get(1).add(e1);
		s.get(1).add(s2);
		
		//H2 - N
		h.add(2, new ArrayList<Beat>());
		h.get(2).add(w0);
		h.get(2).add(h0);
		h.get(2).add(q0);
		h.get(2).add(e0);
		h.get(2).add(s0);
		h.get(2).add(de0);
		
		//Q2 - Q3, E6, S12
		q.add(2, new ArrayList<Beat>());
		q.get(2).add(q3);
		q.get(2).add(de3);
		q.get(2).add(e6);
		q.get(2).add(s12);
		
		//E2 - E3, S4
		e.add(2, new ArrayList<Beat>());
		e.get(2).add(e3);
		e.get(2).add(s4);
		
		//S2 - S3
		s.add(2, new ArrayList<Beat>());
		s.get(2).add(s3);
		
		//Q3 - N
		q.add(3, new ArrayList<Beat>());
		q.get(3).add(s0);
		q.get(3).add(h0);
		q.get(3).add(q0);
		q.get(3).add(e0);
		q.get(3).add(s0);
		q.get(3).add(de0);
		
		//E3 - Q2, E4, S8
		e.add(3, new ArrayList<Beat>());
		e.get(3).add(q2);
		e.get(3).add(de2);
		e.get(3).add(e4);
		e.get(3).add(s8);
		
		//S3 - H1, Q1, E2, S4, DE1
		s.add(3, new ArrayList<Beat>());
		s.get(3).add(h1);
		s.get(3).add(q1);
		s.get(3).add(de1);
		s.get(3).add(e2);
		s.get(3).add(s4);
		s.get(3).add(de1);
		
		//E4 - E5, S10
		e.add(4, new ArrayList<Beat>());
		e.get(4).add(e5);
		e.get(4).add(s10);
		
		//S4 - S5
		s.add(4, new ArrayList<Beat>());
		s.get(4).add(s5);
		
		//E5 - Q3, E6, S12
		e.add(5, new ArrayList<Beat>());
		e.get(5).add(q3);
		e.get(5).add(de3);
		e.get(5).add(e6);
		e.get(5).add(s12);
		
		//S5 - E3, S6
		s.add(5, new ArrayList<Beat>());
		s.get(5).add(e3);
		s.get(5).add(s6);
		
		//E6 - E7, S14
		e.add(6, new ArrayList<Beat>());
		e.get(6).add(e7);
		e.get(6).add(s14);
		
		//S6 - S7
		s.add(6, new ArrayList<Beat>());
		s.get(6).add(s7);
		
		//E7 - N
		e.add(7, new ArrayList<Beat>());
		e.get(7).add(w0);
		e.get(7).add(h0);
		e.get(7).add(q0);
		e.get(7).add(e0);
		e.get(7).add(s0);
		e.get(7).add(de0);
		
		//S7 - H2, Q2, E4, S8, DE2
		s.add(7, new ArrayList<Beat>());
		s.get(7).add(h2);
		s.get(7).add(q2);
		s.get(7).add(de2);
		s.get(7).add(e4);
		s.get(7).add(s8);
		s.get(7).add(de2);
		
		//S8 - S9
		s.add(8, new ArrayList<Beat>());
		s.get(8).add(s9);
		
		//S9 - E5, S10
		s.add(9, new ArrayList<Beat>());
		s.get(9).add(e5);
		s.get(9).add(s10);
		
		//S10 - S11
		s.add(10, new ArrayList<Beat>());
		s.get(10).add(s11);
		
		//S11 - Q3, E6, S12, DE3
		s.add(11, new ArrayList<Beat>());
		s.get(11).add(q3);
		s.get(11).add(de3);
		s.get(11).add(e6);
		s.get(11).add(s12);
		s.get(11).add(de3);
		
		//S12 - S13
		s.add(12, new ArrayList<Beat>());
		s.get(12).add(s13);
		
		//S13 - E7, S14
		s.add(13, new ArrayList<Beat>());
		s.get(13).add(e7);
		s.get(13).add(s14);
		
		//S14 - S15
		s.add(14, new ArrayList<Beat>());
		s.get(14).add(s15);
		
		//S15 - N
		s.add(15, new ArrayList<Beat>());
		s.get(15).add(w0);
		s.get(15).add(h0);
		s.get(15).add(q0);
		s.get(15).add(e0);
		s.get(15).add(s0);
		s.get(15).add(de0);
		
		//DE0 - S3
		de.add(0, new ArrayList<Beat>());
		de.get(0).add(s3);
		
		//DE1 - S7
		de.add(1, new ArrayList<Beat>());
		de.get(1).add(s7);
		
		//DE2 - S11
		de.add(2, new ArrayList<Beat>());
		de.get(2).add(s11);
		
		//DE3 - S15
		de.add(3, new ArrayList<Beat>());
		de.get(3).add(s15);
	}
}
