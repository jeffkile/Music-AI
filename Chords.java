import java.util.ArrayList;
import java.util.List;


public class Chords extends Scales{
	
	List myChords = new ArrayList();  //of type aScale

	Chords(){
			try{
			myChords.add( new AScale("A", "MajChord") );
			myChords.add( new AScale("Bb", "MajChord") );
			myChords.add( new AScale("B", "MajChord") );
			myChords.add( new AScale("C", "MajChord") );
			myChords.add( new AScale("C#", "MajChord") );
			myChords.add( new AScale("D", "MajChord") );
			myChords.add( new AScale("Eb", "MajChord") );
			myChords.add( new AScale("E", "MajChord") );
			myChords.add( new AScale("F", "MajChord") );
			myChords.add( new AScale("F#", "MajChord") );
			myChords.add( new AScale("G", "MajChord") );
			myChords.add( new AScale("Ab", "MajChord") );
			
			myChords.add( new AScale("A", "MinChord") );
			myChords.add( new AScale("Bb", "MinChord") );
			myChords.add( new AScale("B", "MinChord") );
			myChords.add( new AScale("C", "MinChord") );
			myChords.add( new AScale("C#", "MinChord") );
			myChords.add( new AScale("D", "MinChord") );
			myChords.add( new AScale("Eb", "MinChord") );
			myChords.add( new AScale("E", "MinChord") );
			myChords.add( new AScale("F", "MinChord") );
			myChords.add( new AScale("F#", "MinChord") );
			myChords.add( new AScale("G", "MinChord") );
			myChords.add( new AScale("Ab", "MinChord") );
			
		}
		catch(Exception e){
			System.out.println("wrong scale type" + e.toString());
		}
	}
	

	public void addNote(int addMe){
		
		for(int i=0; i<myChords.size(); i++){
			((AScale)myChords.get(i)).updateCount(addMe);
		}
	}
	
	public void addSubNote(int addMe){ //subNote is te same as addNote in this case

		for(int i=0; i<myChords.size(); i++){
			((AScale)myChords.get(i)).updateCount(addMe);
		}
	}
	
	public AScale getChord(String scaleKey, int chordPos, String chordType) throws BadScaleException{
		
		int init = 0;
		String chordKey = "";
		int chordNumber = 0;
		List notes;
		AScale retMe = null;
		
		//0 is low C
		if(scaleKey == "A"){
			init = 10;
		}
		else if(scaleKey == "A#" || scaleKey == "Bb"){
			init = 11;
		}
		else if(scaleKey == "B"){
			init = 12;
		}
		else if(scaleKey == "B#" || scaleKey == "Cb"){
			init = 13;
		}
		else if(scaleKey == "C"){
			init = 0;
		}
		else if(scaleKey == "C#" || scaleKey == "Db"){
			init = 1;
		}
		else if(scaleKey == "D"){
			init = 2;
		}
		else if(scaleKey == "D#" || scaleKey == "Eb"){
			init = 3;
		}
		else if(scaleKey == "E"){
			init = 4;
		}
		else if(scaleKey == "E#" || scaleKey == "Fb"){
			init = 5;
		}
		else if(scaleKey == "F"){
			init = 6;
		}
		else if(scaleKey == "F#" || scaleKey == "Gb"){
			init = 7;
		}
		else if(scaleKey == "G"){
			init = 8;
		}
		else if(scaleKey == "G#" || scaleKey == "Ab"){
			init = 9;
		}
		
		//get all the notes of that major scale for that key
		try{
			notes = new AScale(scaleKey, "Major").getNotes();
			chordNumber = ((Integer)(notes.get( init + chordPos ))).intValue();
		}
		catch(Exception e){
			System.out.println("Bad Chord Type");
		}
		
		switch ( chordNumber%12 ){
			case 0:
				chordKey = "C";
				break;
			case 1:
				chordKey = "C#";
				break;
			case 2:
				chordKey = "D";
				break;
			case 3:
				chordKey = "Eb";
				break;
			case 4:
				chordKey = "E";
				break;
			case 5:
				chordKey = "F";
				break;
			case 6:
				chordKey = "F#";
				break;
			case 7:
				chordKey = "G";
				break;
			case 8:
				chordKey = "Ab";
				break;
			case 9:
				chordKey = "A";
				break;
			case 10:
				chordKey = "Bb";
				break;
			case 11:
				chordKey = "B";
				break;
		}
		
		
		try{
			if(chordType == "Major"){
				 retMe = new AScale(chordKey, "MajChord");
			}
		}
		catch(BadScaleException e){
			System.out.println(e.toString() + "Bad Parameters");
			throw e;
		}
		
		return retMe;
		
	}

	public AScale getHighestCount(){
		
		double temp = 0, highest = 0;
		int pos=0;
		List posList = new ArrayList();
		
		for(int i=0; i<myChords.size(); i++){
			temp = ((AScale)myChords.get(i)).getScore();
			if (temp>highest){
				highest = temp;
				pos = i;
			}
		}

		//Get all the scales with a tie for highest
		for(int i=0; i<myChords.size(); i++){
			if(((AScale)myChords.get(i)).getScore() == highest)
				posList.add(new Integer(i));
		}
		
		//select a scale randomly from the highest ties
		pos = ((Integer)posList.get( ((int)(posList.size()*Math.random())) )).intValue();
	
		return (AScale)myChords.get(pos);
	}

	public AScale getSecondHighestCount(){
		
		double temp = 0, highest = 0, secondHighest = 0;
		int secondPos=0, highPos = 0;
		List posList = new ArrayList();
		
		//get highest first
		for(int i=0; i<myChords.size(); i++){			
			temp = ((AScale)myChords.get(i)).getScore();
			if (temp>highest){
				highest = temp;
			}
		}
		//then get second highest
		for(int i=0; i<myChords.size(); i++){			
			temp = ((AScale)myChords.get(i)).getScore();
			if (temp>secondHighest && temp<highest){
				secondHighest = temp;
			}
		}
		
		//	Get all the scales with a tie for highest
		System.out.print("Highest scores: " + highest + " - "); //debugging
		//this whole for loop is debugging
			for(int i=0; i<myChords.size(); i++){
				if(((AScale)myChords.get(i)).getScore() == highest){
					System.out.print(((AScale)myChords.get(i)).getScaleKey() + " " +((AScale)myChords.get(i)).getScaleType() + " ");
				}
			}

		System.out.println("\n"); //debugging
		//Get all the scales with a tie for Second highest
		System.out.print("Second highest scores: " + secondHighest + " - "); //debugging
		for(int i=0; i<myChords.size(); i++){
			if(((AScale)myChords.get(i)).getScore() == secondHighest){
				posList.add(new Integer(i));
				System.out.print(((AScale)myChords.get(i)).getScaleKey() + " " +((AScale)myChords.get(i)).getScaleType() + " ");
			}
		}
		System.out.println("\n"); //debugging
		
		//select a scale randomly from the highest ties
		secondPos = ((Integer)posList.get( ((int)(posList.size()*Math.random())) )).intValue();
	
		return (AScale)myChords.get(secondPos);
	}

	
}
