
import java.util.*;

public class Scales {

  private boolean DEBUG;
	List myScales = new ArrayList();  //of type aScale

  Scales(boolean debug) {
    this();
    this.DEBUG = debug;
  }
	
	Scales(){
    this.DEBUG = false;
		try{
			myScales.add( new AScale("A", "Major") );
			myScales.add( new AScale("Bb", "Major") );
			myScales.add( new AScale("B", "Major") );
			myScales.add( new AScale("C", "Major") );
			myScales.add( new AScale("C#", "Major") );
			myScales.add( new AScale("D", "Major") );
			myScales.add( new AScale("Eb", "Major") );
			myScales.add( new AScale("E", "Major") );
			myScales.add( new AScale("F", "Major") );
			myScales.add( new AScale("F#", "Major") );
			myScales.add( new AScale("G", "Major") );
			myScales.add( new AScale("Ab", "Major") );
	
			myScales.add( new AScale("A", "Minor") );
			myScales.add( new AScale("Bb", "Minor") );
			myScales.add( new AScale("B", "Minor") );
			myScales.add( new AScale("C", "Minor") );
			myScales.add( new AScale("C#", "Minor") );
			myScales.add( new AScale("D", "Minor") );
			myScales.add( new AScale("Eb", "Minor") );
			myScales.add( new AScale("E", "Minor") );
			myScales.add( new AScale("F", "Minor") );
			myScales.add( new AScale("F#", "Minor") );
			myScales.add( new AScale("G", "Minor") );
			myScales.add( new AScale("Ab", "Minor") );
			
		}
		catch(Exception e){
			System.out.println("wrong scale type" + e.toString());
		}
	}
	
	public void addNote(int addMe){
		
		if (DEBUG) {
      System.out.println("adding:" + addMe);
    }
		
		for(int i=0; i<myScales.size(); i++){
			((AScale)myScales.get(i)).updateCount(addMe);
		}
	}
	
	public void addSubNote(int addMe){

		for(int i=0; i<myScales.size(); i++){
			((AScale)myScales.get(i)).updateCount(addMe);
		}
	}

	public AScale getHighestCount(){
		
		double temp = 0, highest = 0;
		int pos=0;
		List posList = new ArrayList();
		
		for(int i=0; i<myScales.size(); i++){
			temp = ((AScale)myScales.get(i)).getScore();
			if (temp>highest){
				highest = temp;
				pos = i;
			}
		}

		//Get all the scales with a tie for highest
		for(int i=0; i<myScales.size(); i++){
			if(((AScale)myScales.get(i)).getScore() == highest)
				posList.add(new Integer(i));
		}
		
		if (DEBUG) {
      System.out.print("Highest scores: " + highest + " - "); //debugging

		  //this whole for loop is debugging
			for(int i=0; i<myScales.size(); i++){
				if(((AScale)myScales.get(i)).getScore() == highest){
					System.out.print(((AScale)myScales.get(i)).getScaleKey() + " " +((AScale)myScales.get(i)).getScaleType() + " ");
				}
			}
    }

		
		
		//select a scale randomly from the highest ties
		pos = ((Integer)posList.get( ((int)(posList.size()*Math.random())) )).intValue();
	
		return (AScale)myScales.get(pos);
	}

	public AScale getSecondHighestCount(){
		
		double temp = 0, highest = 0, secondHighest = 0;
		int secondPos=0, highPos = 0;
		List posList = new ArrayList();
		
		//get highest first
		for(int i=0; i<myScales.size(); i++){			
			temp = ((AScale)myScales.get(i)).getScore();
			if (temp>highest){
				highest = temp;
			}
		}
		//then get second highest
		for(int i=0; i<myScales.size(); i++){			
			temp = ((AScale)myScales.get(i)).getScore();
			if (temp>secondHighest && temp<highest){
				secondHighest = temp;
			}
		}
		
    if (DEBUG) {
      //	Get all the scales with a tie for highest
        System.out.print("Highest scores: " + highest + " - "); //debugging
      //this whole for loop is debugging
        for(int i=0; i<myScales.size(); i++){
          if(((AScale)myScales.get(i)).getScore() == highest){
            System.out.print(((AScale)myScales.get(i)).getScaleKey() + " " +((AScale)myScales.get(i)).getScaleType() + " ");
          }
        }
      System.out.println("\n"); //debugging
    }
      //Get all the scales with a tie for Second highest
    if (DEBUG) {
      System.out.print("Second highest scores: " + secondHighest + " - "); //debugging
    }
    for(int i=0; i<myScales.size(); i++){
      if(((AScale)myScales.get(i)).getScore() == secondHighest){
        posList.add(new Integer(i));
        if (DEBUG) {
          System.out.print(((AScale)myScales.get(i)).getScaleKey() + " " +((AScale)myScales.get(i)).getScaleType() + " ");
        }
      }
    }
    if (DEBUG) {
      System.out.println("\n"); //debugging
    }
		
		//select a scale randomly from the highest ties
		secondPos = ((Integer)posList.get( ((int)(posList.size()*Math.random())) )).intValue();
	
		return (AScale)myScales.get(secondPos);
	}
}


/*
public class MajorScale Extends Scale{
	
	MajorScale
	
}*/
