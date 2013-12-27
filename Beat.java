
/* Beat.java
 * 
 * keeps a single beat state and it's heuristic value
 */
public class Beat {
	//define state by note at a certain time
	private String note;
	private int time;
	
	private int heuristicValue;
	
	public Beat(String note, int time) {
		this.note = note;
		this.time = time;
		
		this.heuristicValue = 0;
	}
	
	//add n value to heuristic Value
	public void addValue(int n) {
		heuristicValue += n;
	}
	
	public void clear() {
		heuristicValue = 0;
	}
	
	public int getValue() {
		return heuristicValue;
	}
	
	public String getNote() {
		return note;
	}
	
	public int getTime() {
		return time;
	}
	
	public Beat getThisBeat() {
		return this;
	}
}
