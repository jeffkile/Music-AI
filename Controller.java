
import javax.sound.midi.Sequence;
import java.io.*;

public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Loading sound bank...");
		//!!!!!!!!!!!!!! CHANGE THIS PATH !!!!!!!!!!!!!!!//
		//LoadSoundBank.load("C:\\Documents and Settings\\Jeff\\workspace\\musicAI\\MusicAI\\soundbank-deluxe.gm");  //THIS NEEDS TO BE THE PATH WHERE THE MUSIC AI FILES ARE LOCATED
		LoadSoundBank.load("/Users/jeff/Desktop/Stuff/Programming Projects/musicAI/MusicAI/soundbank-deluxe.gm");  //THIS NEEDS TO BE THE PATH WHERE THE MUSIC AI FILES ARE LOCATED
		//!!!!!!!!!!!!!! CHANGE THIS PATH !!!!!!!!!!!!!!!//
		
	    // 1. Create an InputStreamReader using the standard input stream.
	    InputStreamReader isr = new InputStreamReader( System.in );
	    // 2. Create a BufferedReader using the InputStreamReader created.
	    BufferedReader stdin = new BufferedReader( isr );
		
		Player myPlayer = new Player();
		
		Sequence seq1 = null;
		String choiceName="";

		System.out.println("Choose an algorithm to play: ");
		System.out.println("\t1. Random");
		System.out.println("\t2. Score");
		System.out.println("\t3. Score Multiple Agents");
		System.out.println("\t4. Second Score Multiple Agents");
		System.out.println("\t5. Second Score Multiple Agents With Chords");
		System.out.println("\t6. Second Score Multiple Agents With Beats");
		System.out.println("\t7. Final");
		System.out.println("\t8. Modal");
		System.out.println("\t9. Beats");
		System.out.println("\t10. Agent");
		System.out.println("\t11. JonMoid");
		System.out.println("\t12. JonMoid2");
		System.out.println("\t13. Trance");
		
		
		try{
			String temp = stdin.readLine(); 
			int choice = Integer.parseInt(temp); 
			int numAgents = 1;
			int numTicks = 4;
//			choice = choice-48;
		
			System.out.println("choice:" + choice);
			
		switch(choice){
			case 1: 
				choiceName = "Random";
				break;
			case 2:	
				choiceName ="Score";
				break;
			case 3:
				choiceName ="Score Multiple Agents";
				System.out.println("Enter the number of agents to use:");
				temp = stdin.readLine(); 
				numAgents = Integer.parseInt(temp);
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
	
				break;
			case 4:
				choiceName ="Second Score Multiple Agents";
				System.out.println("Enter the number of agents to use:");
				temp = stdin.readLine(); 
				numAgents = Integer.parseInt(temp);
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
				break;
			case 5:
				choiceName ="Second Score Multiple Agents With Chords";
				System.out.println("Enter the number of agents to use:");
				temp = stdin.readLine(); 
				numAgents = Integer.parseInt(temp);
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
				break;
			case 6:
				choiceName ="Second Score Multiple Agents With Beats";
				System.out.println("Enter the number of agents to use (at least 5):");
				temp = stdin.readLine(); 
				numAgents = Integer.parseInt(temp);
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
				break;
			case 7:
				choiceName ="Final";
				System.out.println("Enter the number of agents to use:");
				temp = stdin.readLine(); 
				numAgents = Integer.parseInt(temp);
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
				break;
			case 8:
				choiceName ="Modal";
				break;
			case 9:
				choiceName ="Beats";
				System.out.println("Enter the number of Ticks Per Measure (PPQ):");
				temp = stdin.readLine(); 
				numTicks = Integer.parseInt(temp);
				break;
			case 10:
				choiceName ="Multiple Agents";
				break;
			case 11:
				choiceName ="JonMoid";
				break;
			case 12:
				choiceName ="JonMoid2";
				break;
			case 13:
				choiceName ="Trance";
				break;
		}
		
		System.out.println(choiceName + " " + numAgents + " " + numTicks);

		CreateSequence seqGetter = new CreateSequence(numAgents, numTicks);
		
		seqGetter.run(choiceName); //start seqGetter //CHANGE IT BELOW TOO!
		seq1 = seqGetter.getMySequence();
		myPlayer.run(seq1);
		
		if(choice < 10 ){
			for(int i=0; i<10; i++){ //play 10 sequences
				
				System.out.println("Computing Next Sequence " + i);
	
				try{
					
					seqGetter.run(choiceName); //start seqGetter
					System.out.println("Computing Next Sequence " + i);
			
					seqGetter.join();
				
					seq1 = seqGetter.getMySequence();
					myPlayer.join();
					myPlayer.run(seq1);
					
				}catch(Exception e){
					System.out.println("Thread Exception\n"+e.toString());
				}
			}//end for
		}//end if
		
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
}