import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AScale {
	
	private List myNotes;
	private int count;
	private double score = 0;
	
	private int init; //the starting note
	private String myScaleType;
	private String myScaleKey;
	private int locationOfFirstRoot; //the interval number located at position 0
	
		
	AScale(String scaleKey, String scaleType) throws BadScaleException{
		
		myNotes = new ArrayList();
		myScaleType = scaleType;
		myScaleKey = scaleKey;
		
		
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
	
		
		
		if(scaleType == "Major" || scaleType == "major"){
			//W W H W W W H
			int temp=init;
			int interval=1;
			
			//First add all the notes above init
			while(temp<127){
				switch(interval){
					case 1:
						myNotes.add(new Integer(temp));
						interval++;
						temp = temp+2;
						break;
					case 2:
						myNotes.add(new Integer(temp)); //w
						interval++;
						temp = temp+2;
						break;
					case 3:
						myNotes.add(new Integer(temp)); //w
						interval++;
						temp = temp+1;
						break;
					case 4:
						myNotes.add(new Integer(temp)); //h
						interval++;
						temp = temp+2;
						break;
					case 5:
						myNotes.add(new Integer(temp)); //w
						interval++;
						temp = temp+2;
						break;
					case 6:
						myNotes.add(new Integer(temp)); //w
						interval++;
						temp = temp+2;
						break;
					case 7:
						myNotes.add(new Integer(temp)); //w
						temp = temp+1;
						interval = 1;
						break;
				}
			}

			//Then add all the notes below init
			temp = init;
			interval = 1; //going down
			
			// H W W W H W W 			
			while(temp > 0){
				
				switch(interval){
				
				case 1:
					temp=temp-1; //subtract first because we were just at the root
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 2:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 3:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 4:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 5:
					temp=temp-1;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 6:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 7:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval=1;
					break;
				}
			}

			locationOfFirstRoot = interval; 
			//sort myNotes in ascending order
		}
		if(scaleType == "Minor" || scaleType == "minor"){
			//W H W W H W W
			
			int temp=init;
			int interval=1;
			
			//First add all the notes above init
			while(temp<127){
				switch(interval){
					case 1:
						myNotes.add(new Integer(temp));
						interval++;
						temp = temp+2;
						break;
					case 2:
						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+1;
						break;
					case 3:
						myNotes.add(new Integer(temp)); //H
						interval++;
						temp = temp+2;
						break;
					case 4:
						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+2;
						break;
					case 5:
						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+1;
						break;
					case 6:
						myNotes.add(new Integer(temp)); //H
						interval++;
						temp = temp+2;
						break;
					case 7:
						myNotes.add(new Integer(temp)); //W
						temp = temp+2;
						interval = 1;
						break;							//W
				}
			}

			//Then add all the notes below init
			temp = init;
			interval = 1; //going down
			
			//W W H W W H W
			while(temp > 0){
				
				switch(interval){
				
				case 1:
					temp=temp-2; //subtract first because we were just at the root
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 2:
					temp=temp-2;					//W
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 3:
					temp=temp-1;					//H
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 4:
					temp=temp-2;					//W
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 5:
					temp=temp-2;					//W	
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 6:
					temp=temp-1;					//H
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 7:
					temp=temp-2;					//W
					myNotes.add(new Integer(temp));
					interval=1;
					break;
				}
			}

			locationOfFirstRoot = interval; 
			//sort myNotes in ascending order
		}

		if(scaleType == "MajChord" || scaleType == "majchord"){
			//W+W H+W
			//1 3 5 - Intervals
			//0 4 7 - Half steps from init
			int temp=init;
			int interval=1;
			
			//First add all the notes above init
			while(temp<127){
				switch(interval){
				case 1:
					myNotes.add(new Integer(temp));
					interval++;
					temp = temp+2;
					break;
				case 2:
					//myNotes.add(new Integer(temp)); //w
					interval++;
					temp = temp+2;
					break;
				case 3:
					myNotes.add(new Integer(temp)); //w
					interval++;
					temp = temp+1;
					break;
				case 4:
					//myNotes.add(new Integer(temp)); //h
					interval++;
					temp = temp+2;
					break;
				case 5:
					myNotes.add(new Integer(temp)); //w
					interval++;
					temp = temp+2;
					break;
				case 6:
//					myNotes.add(new Integer(temp)); //w
					interval++;
					temp = temp+2;
					break;
				case 7:
//					myNotes.add(new Integer(temp)); //w
					temp = temp+1;
					interval = 1;
					break;

				}
			}
			
//			Then add all the notes below init
			temp = init;
			interval = 1; //going down
			
			//Then add all the notes below init
			temp = init;
			interval = 1; //going down
			
			// H W W W H W W 			
			while(temp > 0){
				switch(interval){
				case 1:
					temp=temp-1; //subtract first because we were just at the root
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 2:
					temp=temp-2;
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 3:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 4:
					temp=temp-2;
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 5:
					temp=temp-1;
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 6:
					temp=temp-2;
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 7:
					temp=temp-2;
					myNotes.add(new Integer(temp));
					interval=1;
					break;
				}
			}

			locationOfFirstRoot = interval; 
		}
		
		if(scaleType == "MinChord" || scaleType == "minchord"){
			//W H W W H W W
			
			int temp=init;
			int interval=1;
			
			//First add all the notes above init
			while(temp<127){
				switch(interval){
					case 1:
						myNotes.add(new Integer(temp));
						interval++;
						temp = temp+2;
						break;
					case 2:
//						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+1;
						break;
					case 3:
						myNotes.add(new Integer(temp)); //H
						interval++;
						temp = temp+2;
						break;
					case 4:
//						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+2;
						break;
					case 5:
						myNotes.add(new Integer(temp)); //W
						interval++;
						temp = temp+1;
						break;
					case 6:
//						myNotes.add(new Integer(temp)); //H
						interval++;
						temp = temp+2;
						break;
					case 7:
//						myNotes.add(new Integer(temp)); //W
						temp = temp+2;
						interval = 1;
						break;							//W
				}
			}

			//Then add all the notes below init
			temp = init;
			interval = 1; //going down
			
			//W W H W W H W
			while(temp > 0){			
				switch(interval){
				case 1:
					temp=temp-2; //subtract first because we were just at the root
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 2:
					temp=temp-2;					//W
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 3:
					temp=temp-1;					//H
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 4:
					temp=temp-2;					//W
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 5:
					temp=temp-2;					//W	
					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 6:
					temp=temp-1;					//H
//					myNotes.add(new Integer(temp));
					interval++;
					break;
				case 7:
					temp=temp-2;					//W
					myNotes.add(new Integer(temp));
					interval=1;
					break;
				}
			}

			locationOfFirstRoot = interval; 
		}
		
		//sort myNotes in ascending order
		Collections.sort(myNotes);
	}
	
	public List getNotes(){
		return myNotes;
	}
	
	public double getScore(){
		return score;
	}
	
	public String getScaleType(){
		return myScaleType;
	}
	
	public String getScaleKey(){
		return myScaleKey;
	}
	
	public void updateCount(int noteNumber){

		int indexFound;
		int interval;
		
		//if note number is in myNotes count++
		indexFound = Collections.binarySearch(myNotes, new Integer(noteNumber));
		//System.out.println("found at:" + indexFound);
		if(indexFound != - 1){ //we found it
			interval = indexFound % locationOfFirstRoot;
		
			
			//lets weight the intervals 1 3 and 5 heaviest
			if(interval == 1 || interval == 3 || interval == 5)
				score=score+5;
			//intervals 2 4 and 6 less heavy
			if(interval == 2 || interval == 4 || interval == 6)
				score=score+4;
			//and interval 7 least heavy
			if(interval == 7)
				score=score+3;		
		}
		
		
	}
	
	public void updateSubCount(int noteNumber){

		int indexFound;
		int interval;
		
		//if note number is in myNotes count++
		indexFound = Collections.binarySearch(myNotes, new Integer(noteNumber));
		if(indexFound != - 1){ //we found it
			interval = indexFound % locationOfFirstRoot;
			
			//lets weight the intervals 1 3 and 5 heaviest
			if(interval == 1 || interval == 3 || interval == 5)
				score=score+4;
			//intervals 2 and 6 less heavy
			if(interval == 2 || interval == 6)
				score=score+3;
			//and interval 7 and 4least heavy
			if(interval == 7 || interval == 4)
				score=score+2;
			
		}
		
		//System.out.println("score:" + score);//debuggin
		
	}

}
