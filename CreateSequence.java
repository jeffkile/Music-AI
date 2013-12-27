/*
 *	CreateSequence.java
 *
 *	This file is part of jsresources.org
 */

/*
 * Copyright (c) 2000 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
*/

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.lang.Thread;

import javax.sound.midi.Sequence;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;

//import BeatStates.FeelState;

public class CreateSequence extends Thread
{
	/*	This velocity is used for all notes.
	 */
	private static final int	VELOCITY = 64;
	private Sequence mySequence;
	//private static final List scale = new ArrayList();
	//private static int time=0;
	
	Scales myScales = new Scales();
	Scales myScales2 = new Scales();
	
	int newNote = 52+(int)(12*Math.random()); //arbitrary middle C
	int oldNote = newNote;
	int newNote2 = 52+(int)(12*Math.random());
	int oldNote2 = newNote2;
	
	int tempIndex = (((127/12)*8)/2);  //this shouldbe the middle index, arbitrary again however;
		
	public int numTicks = 4; //NUMBER OF TICKS
	public int size=4; //NUMBER OF AGENTS
	int[] newNotes;
	int[] oldNotes; 
	Scales[] myScalesArr;
	int[] tempIndexs;
	
	CreateSequence(int a, int t){
		size = a;
		numTicks = t;
		
		newNotes = new int[size];
		oldNotes = new int[size]; 
		myScalesArr = new Scales[size];
		tempIndexs = new int[size];
				
		for(int w=0; w<size; w++){
			myScalesArr[w] = new Scales();
			newNotes[w] = 52+(int)(12*Math.random()); //arbitrary near middle C
			oldNotes[w] = 52+(int)(12*Math.random()); //arbitrary near middle C
			tempIndexs[w] = (((127/12)*8)/2);  //this shouldbe the middle index, arbitrary again however;
		}
	}
	
	CreateSequence(){
		newNotes = new int[size];
		oldNotes = new int[size]; 
		myScalesArr = new Scales[size];
		tempIndexs = new int[size];
		
		
		for(int w=0; w<size; w++){
			myScalesArr[w] = new Scales();
			newNotes[w] = 52+(int)(12*Math.random()); //arbitrary near middle C
			oldNotes[w] = 52+(int)(12*Math.random()); //arbitrary near middle C
			tempIndexs[w] = (((127/12)*8)/2);  //this shouldbe the middle index, arbitrary again however;
		}
	}
	
	public Sequence getMySequence(){
		return mySequence;		
	}
	
	//public static void main(String[] args)
	public void run(String sequenceType)
	//public void start(String sequenceType)
	{
	
		Sequence	sequence = null;
		try
		{
			sequence = new Sequence(Sequence.PPQ, numTicks); //4 ticks per quarter note
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		/* Track objects cannot be created by invoking their constructor
		   directly. Instead, the Sequence object does the job. So we
		   obtain the Track there. This links the Track to the Sequence
		   automatically.
		*/
		Track	track = sequence.createTrack();
		Track track2 = sequence.createTrack();

		Track[] tracks = new Track[size];
		for(int y=0; y<size; y++)
			tracks[y] = sequence.createTrack();
	
		
		if(sequenceType == "Chords"){
			
			// first chord: C major
			track.add(createNoteOnEvent(60, 0)); 
			track.add(createNoteOnEvent(64, 0));
			track.add(createNoteOnEvent(67, 0));
			track.add(createNoteOnEvent(72, 0));
			
			track.add(createNoteOffEvent(60, 1));
			track.add(createNoteOffEvent(64, 1));
			track.add(createNoteOffEvent(67, 1));
			track.add(createNoteOffEvent(72, 1));
	
			// second chord: f minor N
			track.add(createNoteOnEvent(53, 1));
			track.add(createNoteOnEvent(65, 1));
			track.add(createNoteOnEvent(68, 1));
			track.add(createNoteOnEvent(73, 1));
			
			track.add(createNoteOffEvent(63, 2));
			track.add(createNoteOffEvent(65, 2));
			track.add(createNoteOffEvent(68, 2));
			track.add(createNoteOffEvent(73, 2));
	
			// third chord: C major 6-4
			track.add(createNoteOnEvent(55, 2));
			track.add(createNoteOnEvent(64, 2));
			track.add(createNoteOnEvent(67, 2));
			track.add(createNoteOnEvent(72, 2));
			track.add(createNoteOffEvent(64, 3));
			track.add(createNoteOffEvent(72, 3));
	
			// forth chord: G major 7
			track.add(createNoteOnEvent(65, 3));
			track.add(createNoteOnEvent(71, 3));
			track.add(createNoteOffEvent(55, 4));
			track.add(createNoteOffEvent(65, 4));
			track.add(createNoteOffEvent(67, 4));
			track.add(createNoteOffEvent(71, 4));
	
			// fifth chord: C major
			track.add(createNoteOnEvent(48, 4));
			track.add(createNoteOnEvent(64, 4));
			track.add(createNoteOnEvent(67, 4));
			track.add(createNoteOnEvent(72, 4));
			track.add(createNoteOffEvent(48, 8));
			track.add(createNoteOffEvent(64, 8));
			track.add(createNoteOffEvent(67, 8));
			track.add(createNoteOffEvent(72, 8));
	
		}
		else if(sequenceType == "Random"){
			
			//Added by Jeff - selects random notes and adds them to the sequence		
			double r = 0;
			track.add( createNoteOnEvent((int)(58+(24*r)), 0) ); //add a note between 58 and 82 at time 8
			for(int i=1; i<32; i=i){ //for the next 64 clicks (a click is a time unit), i is incremented inside the foor loop
				
				r = Math.random();
				  			
				System.out.println("note:" + (int)(58+(24*r)) + " time on:" + (i) ); //debugging
							
				track.add( createNoteOffEvent((int)(58+(24*r)), i) ); //turn off the last note thats playing, otherwise it will keep playing
				track.add( createNoteOnEvent((int)(58+(24*r)), i) ); //turn on the next note, choose between 58 and 82 at time 8+i 
				
				i = i +( 1+(int)(Math.random()*4) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
			}
		}
		
	
		else if(sequenceType == "Score"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score		
			
			AScale tempScale;
			AScale tempScale2;
			List notes;
			
			//myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			double r = 0;
			int i=1;
			int i2=1;
			
			track.add( createNoteOnEvent(oldNote, 0) ); //add a note between 58 and 82 at time 8
			track2.add( createNoteOnEvent(oldNote2, 0) ); //add a note between 58 and 82 at time 8
			myScales.addNote(oldNote); //add note to the count
			myScales.addNote(oldNote2); //add note2 to the count
						
			//for(i=1; i<32; i=i){ //for the next 32 clicks (a click is a time unit), i is incremented inside the foor loop
			while(i<32 && i2<32){	
				tempScale = myScales.getHighestCount();
				tempScale2 = myScales.getHighestCount();
				
				System.out.println("Scale 1:" + tempScale.getScaleKey() + " " + tempScale.getScaleType()); //debugging
				System.out.println("Scale 2:" + tempScale2.getScaleKey() + " " + tempScale2.getScaleType()); //debugging
				
				notes = tempScale.getNotes();

				r = Math.random();
				//choose a note close to the last notes
				tempIndex = (int)((tempIndex-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
				
				while(tempIndex<12 || tempIndex>tempScale.getNotes().size())
					tempIndex = (int)((tempIndex-4)+(11*r));
				
				oldNote = newNote;
				newNote = ((Integer)(tempScale.getNotes().get(tempIndex))).intValue();
				
				r = Math.random();
				//choose a note close to the last notes
				tempIndex = (int)((tempIndex-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
				
				while(tempIndex<12 || tempIndex>tempScale2.getNotes().size())
					tempIndex = (int)((tempIndex-4)+(11*r));
				
				oldNote2 = newNote2;
				newNote2 = ((Integer)(tempScale2.getNotes().get(tempIndex))).intValue();
				
				System.out.println("note:" + newNote +"index:" + tempIndex+ " time on:" + (i) ); //debugging
				System.out.println("note2:" + newNote +"index:" + tempIndex+ " time on:" + (i) ); //debugging						
				
				track.add( createNoteOffEvent(oldNote, i) ); //turn off the last note thats playing, otherwise it will keep playing
				track.add( createNoteOnEvent(newNote, i) ); //turn on the next note, choose between 58 and 82 at time 8+i
				
				track.add( createNoteOffEvent(oldNote2, i2) ); //turn off the last note thats playing, otherwise it will keep playing
				track.add( createNoteOnEvent(newNote2, i2) ); //turn on the next note, choose between 58 and 82 at time 8+i 
				
				//change this to a rythm heuristic
				i = i +( 1+(int)(Math.random()*4) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
				i2 = i2 +( 1+(int)(Math.random()*4) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
			}
			
			track.add( createNoteOffEvent(oldNote, i) ); //turn off the last note thats playing, otherwise it will keep playing
			track.add( createNoteOffEvent(oldNote2, i2) ); //turn off the last note thats playing, otherwise it will keep playing
		}

		else if(sequenceType == "Score Multiple Agents"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score		
			
			AScale[] tempScales = new AScale[size];
			
			//AScale tempScale;
			//AScale tempScale2;

			//size, myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			double r = 0;

			int[] i = new int[size];
			for(int p=0; p<size; p++)
				i[p] = 0;
			
			//for(i=1; i<32; i=i){ //for the next 32 clicks (a click is a time unit), i is incremented inside the foor loop
			
			for(int measures=1; measures<=4; measures++){ //4 "Measures"
				for(int j=0; j<size; j++){ //agent number //size is global
					//change this to a rythm heuristic
					i[j] = i[j] + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
					
					while(i[j]<(4*measures)){  //4 quarter notes in one in "Measures"
					
						tempScales[j] = myScalesArr[j].getHighestCount();
						
						if(i[j]!=0)
							tracks[j].add(createNoteOffEvent(oldNotes[j], i[j])); //turn off the last note thats playing, otherwise it will keep playing

						System.out.println("Measure: " + measures + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
		
						r = Math.random();
						//choose a note close to the last notes
						tempIndexs[j] = (int)((tempIndexs[j]-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
						
						while(tempIndexs[j]<12 || tempIndexs[j]>tempScales[j].getNotes().size())
							tempIndexs[j] = (int)((tempIndexs[j]-4)+(11*r));
						
						oldNotes[j] = newNotes[j];
						newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
						
						System.out.println("Note: " + newNotes[j] + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
						
						//maybe dont do noteOff first?

						tracks[j].add(createNoteOnEvent(newNotes[j], i[j])); //turn on the next note, choose between 58 and 82 at time 8+i
						
						myScalesArr[j].addNote(newNotes[j]);
						for(int k=0; k<size; k++)
							if(k!=j) //not this agent
								myScalesArr[k].addSubNote(newNotes[j]); //add the new note for this agent to all the other agents sub notes
						
						//change this to a rythm heuristic
						i[j] = i[j] + 1 + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length

					}
					tracks[j].add(createNoteOffEvent(oldNotes[j], 4*measures)); //turn off the last note thats playing, otherwise it will keep playing
				}
			}
		}

		else if(sequenceType == "Second Score Multiple Agents"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score		
			
			AScale[] tempScales = new AScale[size];
			
			//AScale tempScale;
			//AScale tempScale2;

			//size, myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			double r = 0;

			int[] i = new int[size];
			for(int p=0; p<size; p++)//reset all i values
				i[p] = 0;
			
			//for(i=1; i<32; i=i){ //for the next 32 clicks (a click is a time unit), i is incremented inside the foor loop
			
			for(int measures=1; measures<=4; measures++){ //4 "Measures"
				for(int j=0; j<size; j++){ //agent number //size is global
					//change this to a rythm heuristic
					i[j] = i[j] + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
					while(i[j]<(4*measures)){  //4 quarter notes in one in "Measures"
					
						System.out.println("get second highest count");
						tempScales[j] = myScalesArr[j].getSecondHighestCount();  //only real change between this and highest score
						
						if(i[j]!=0)
							tracks[j].add(createNoteOffEvent(oldNotes[j], i[j])); //turn off the last note thats playing, otherwise it will keep playing

						System.out.println("Measure: " + measures + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
		
						r = Math.random();
						//choose a note close to the last notes
						tempIndexs[j] = (int)((tempIndexs[j]-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
						
						while(tempIndexs[j]<12 || tempIndexs[j]>tempScales[j].getNotes().size())
							tempIndexs[j] = (int)((tempIndexs[j]-4)+(11*r));
						
						oldNotes[j] = newNotes[j];
						newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
						
						System.out.println("Note: " + newNotes[j]/12 + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
						
						//maybe dont do noteOff first?

						tracks[j].add(createNoteOnEvent(newNotes[j], i[j])); //turn on the next note, choose between 58 and 82 at time 8+i
						
						myScalesArr[j].addNote(newNotes[j]);
						for(int k=0; k<size; k++)
							if(k!=j) //not this agent
								myScalesArr[k].addSubNote(newNotes[j]); //add the new note for this agent to all the other agents sub notes
						
						//change this to a rythm heuristic
						i[j] = i[j] + 1 + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
						
					}
					tracks[j].add(createNoteOffEvent(oldNotes[j], 4*measures)); //turn off the last note thats playing, otherwise it will keep playing
				}
			}
		}


		else if(sequenceType == "Second Score Multiple Agents With Chords"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score
			//the last 3 agents pick notes from a chord
			
			AScale[] tempScales = new AScale[size];
			
			for(int q = size-3; q<size; q++){ //change the last 3 scales to be chords instead
				myScalesArr[q] = new Chords();
				tempIndexs[q] = (((127/12)*3)/2);  //this shouldbe the middle index, arbitrary again however;
			}
			
			//AScale tempScale;
			//AScale tempScale2;

			//size, myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			double r = 0;

			int[] i = new int[size];
			for(int p=0; p<size; p++)//reset all i values
				i[p] = 0;
			
			for(int measures=1; measures<=4; measures++){ //4 "Measures"
				for(int j=0; j<size; j++){ //agent number //size is global //-3 because not last 3
					
					//change this to a rythm heuristic
						if(j<(size-3))
							i[j] = i[j] + 2*((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
						else
							i[j] = i[j] + 4;
						
						while(i[j]<(8*measures)){  //4 quarter notes in one in "Measures"
						
							if(j<size-3){ //not the chord agents
								System.out.println("get second highest count");
								tempScales[j] = myScalesArr[j].getSecondHighestCount();  //only real change between this and highest score
								
								if(i[j]!=0)
									tracks[j].add(createNoteOffEvent(oldNotes[j], i[j])); //turn off the last note thats playing, otherwise it will keep playing
		
								System.out.println("Measure: " + measures + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
				
								r = Math.random();
								//choose a note close to the last notes
								tempIndexs[j] = (int)((tempIndexs[j]-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
								
								while(tempIndexs[j]<12 || tempIndexs[j]>tempScales[j].getNotes().size())
									tempIndexs[j] = (int)((tempIndexs[j]-4)+(11*r));
								
								oldNotes[j] = newNotes[j];
								newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
								
								System.out.println("Note: " + newNotes[j]/12 + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
								
								//maybe dont do noteOff first?
								tracks[j].add(createNoteOnEvent(newNotes[j], i[j])); //turn on the next note, choose between 58 and 82 at time 8+i
								
								myScalesArr[j].addNote(newNotes[j]);
								for(int k=0; k<size; k++) //size not minus 3 here beause we want to add notes to the chord scores as well
									if(k!=j) //not this agent
										myScalesArr[k].addSubNote(newNotes[j]); //add the new note for this agent to all the other agents sub notes
								
								//change this to a rythm heuristic
								i[j] = i[j] + 1 + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
							}
							else{ // j>size-3 so it is the chord agents
								
								if(j==size-3){ //set all chord agents to the same chord
									System.out.println("get highest chord count");
									tempScales[j] = myScalesArr[j].getHighestCount();  //these "scales" are actually chords
									tempScales[j+1] = myScalesArr[j].getHighestCount();  //these "scales" are actually chords
									tempScales[j+2] = myScalesArr[j].getHighestCount();  //these "scales" are actually chords
								}
								if(i[j]!=0)
									tracks[j].add(createNoteOffEvent(oldNotes[j], i[j])); //turn off the last note thats playing, otherwise it will keep playing
								
								System.out.println("Measure: " + measures + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
								
								r = Math.random();
								//choose a note close to the last notes
								
								tempIndexs[j] = (int)((tempIndexs[j]-1)+(3*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
								
								while(tempIndexs[j]<12){
									tempIndexs[j] += 1+(3*r);
									System.out.println("loop 1");
								}
								while( tempIndexs[j]>=tempScales[j].getNotes().size()){
									tempIndexs[j] -= 1-(3*r);
									System.out.println("loop 2");
								}
								 
								oldNotes[j] = newNotes[j];
								newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
								
								System.out.println("Note: " + newNotes[j]/12 + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
								
								tracks[j].add(createNoteOnEvent(newNotes[j], i[j])); //turn on the next note, choose between 58 and 82 at time 8+i
								
								//dont add these notes to the scales count we dont want our chords influcening scales scores

//								change this to a rythm heuristic
								i[j] = i[j] + 4; // were playing a chord every 4 ticks
								
								System.out.println("i[j] = " + i[j]);
							}
						}
						tracks[j].add(createNoteOffEvent(oldNotes[j], 4*measures)); //turn off the last note thats playing, otherwise it will keep playing
					}
				}
		}
		
		else if(sequenceType == "Modal"){
			
//			 0 Ionian
			// 1 Dorian
			// 2 Phrygian
			// 3 Lydian
			// 4 Mixolydian
			// 5 Aeolian
			// 6 Locrian
			/*
			Modal mode = new Modal();
			scale = mode.getScale(60,0); //Format: getScale(initial note, mode);

			for(int i = 0; i<scale.size();i++)
			{
				System.out.println(scale.get(i));
			}
			*/
			//set Sequence
			mySequence = sequence;
			//Thread.notifyAll(); //??
		}
		
		else if(sequenceType == "Beats"){
			
			//Sequence	sequence = null;//defined above
			/*
			 * this is set above
			 *	sequence = new Sequence(Sequence.PPQ, 4); //4 ticks per quarter note
			 */
			/* Track objects cannot be created by invoking their constructor
			   directly. Instead, the Sequence object does the job. So we
			   obtain the Track there. This links the Track to the Sequence
			   automatically.
			 */
			//Track	track = sequence.createTrack(); //already defined above
			
			ArrayList<Integer> values = new ArrayList<Integer>();
			
			track.add(setSound(118,0)); // maybe do this earlier, the second j is the channel, maybe ont change the channel?

			//number of ticks each note is held for
			//this is stored for when the note is actually played and
			//has no role in the heuristic
			// note - length pairs
			values.add(0, 15); //whole note
			values.add(1, 7); //half note
			values.add(2, 3); //quarter note
			values.add(3, 1); //eighth note
			values.add(4, 0); //sixteenth note
			values.add(5, 2); //dotted eighth note
			//RESTS
			values.add(6, 15); //whole rest
			values.add(7, 7); //half rest
			values.add(8, 3); //quarter rest
			values.add(9, 1); //eighth rest
			values.add(10, 0); //sixteenth rest
			
			int maxCount = 8; 
			
			//BeatStates states = new BeatStates();
			//determine initial feel
			Random random2 = new Random();
			//int feelChoice = random2.nextInt(4);
			int feelChoice = 5;
			/*
			 * 	case 0: VERY_SLOW; 
			 *	case 1: SLOW;
			 *	case 2: MODERATE;
			 *	case 3: FAST;
			 *	case 4: QUICK;
			 *	case 5: MIX;
			 */
			System.out.println("feel choice: " + feelChoice);
			BeatGenerator generator = new BeatGenerator(feelChoice);
			
			ArrayList<Integer> onEvents;
			ArrayList<Integer> offEvents;
			int nextOnEvent;
			int nextOffEvent;
			
			//first generateBeats for one measure
			//generator.generateBeats();
			
			//get the onEvents returned by the generator for that measure
			onEvents = null;
			
			//get the offEvents returned by the generator for that measure
			offEvents = new ArrayList<Integer>();
			
			//start from 1: one note already played

			for(int c = 0; c < maxCount; c++) { //for 8 measures

				
				generator.generateBeats(); //first generate the new measure of beats
				onEvents = generator.getOnEvents(); //get the generated measure of on and off events
				offEvents = generator.getOffEvents();
				
				for(int i = 0; i < onEvents.size(); i++) { //go through each element createNote events according to values returned by BeatGenerator
					track.add(createNoteOnEvent(67, onEvents.get(i) + c*16)); //c*16 = measure * clicks per measure
					track.add(createNoteOffEvent(67, offEvents.get(i) + c*16));
				}	
			}
		}
		
		else if(sequenceType == "Second Score Multiple Agents With Beats"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score		

			AScale[] tempScales = new AScale[size];
			
			/* Track objects cannot be created by invoking their constructor
			   directly. Instead, the Sequence object does the job. So we
			   obtain the Track there. This links the Track to the Sequence
			   automatically.
			 */
			
			ArrayList<Integer> values = new ArrayList<Integer>();

			//number of ticks each note is held for
			//this is stored for when the note is actually played and
			//has no role in the heuristic
			// note - length pairs
			values.add(0, 15); //whole note
			values.add(1, 7); //half note
			values.add(2, 3); //quarter note
			values.add(3, 1); //eighth note
			values.add(4, 0); //sixteenth note
			values.add(5, 2); //dotted eighth note
			//RESTS
			values.add(6, 15); //whole rest
			values.add(7, 7); //half rest
			values.add(8, 3); //quarter rest
			values.add(9, 1); //eighth rest
			values.add(10, 0); //sixteenth rest
			
			int maxCount = 8; 
			
			//BeatStates states = new BeatStates();
			//determine initial feel
			Random random2 = new Random();
			//int feelChoice = random2.nextInt(4);
			int[] feelChoice = new int[size];
			feelChoice[0] = 0; //bass very slow
			feelChoice[1] = 3; //melody fast
			feelChoice[2] = 5; //mixed melody
			feelChoice[3] = 5; //mixed melody 
			feelChoice[4] = 5; //mixed melody 
			//define feelChoices up here
			//= 5;
			/*
			 * 	case 0: VERY_SLOW; 
			 *	case 1: SLOW;
			 *	case 2: MODERATE;
			 *	case 3: FAST;
			 *	case 4: QUICK;
			 *	case 5: MIX;
			 */
			System.out.println("feel choice: " + feelChoice);
			BeatGenerator[] generators = new BeatGenerator[size];
			//= new BeatGenerator(feelChoice);
			
			ArrayList<Integer>[] onEvents = new ArrayList[size];
			ArrayList<Integer>[] offEvents = new ArrayList[size];
			int nextOnEvent;
			int nextOffEvent;
			
			//first generateBeats for one measure

			//initalize generators
			for(int h=0; h<size; h++){
				
				generators[h] = new BeatGenerator(feelChoice[h]);				
				
				//get the onEvents returned by the generator for that measure
				onEvents[h] = null;
				
				//get the offEvents returned by the generator for that measure
				offEvents[h] = new ArrayList<Integer>();

			}
				
			
			double r = 0;
			int[] i = new int[size];
			for(int p=0; p<size; p++)//reset all i values
				i[p] = 0;
		

			for(int c = 0; c < maxCount; c++) { //for 8 measures
				for(int j = 0; j < size; j++){
									
					generators[j].generateBeats(); //first generate the new measure of beats
					onEvents[j] = generators[j].getOnEvents(); //get the generated measure of on and off events
					offEvents[j] = generators[j].getOffEvents();
					
					for(int k = 0; k < onEvents[j].size(); k++) { //go through each element createNote events accordjng to values returned by BeatGenerator
						//track.add(createNoteOnEvent(67, onEvents[k].get(k) + c*16)); //c*16 = measure * cljcks per measure
						//track.add(createNoteOffEvent(67, offEvents[k].get(k) + c*16));

						System.out.println("get second highest count");
						tempScales[j] = myScalesArr[j].getSecondHighestCount();  //only real change between this and highest score
						
						System.out.println("Measure: " + c + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
		
						r = Math.random();
						//choose a note close to the last notes
						tempIndexs[j] = (int)((tempIndexs[j]-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
					
						//make sure its not too low or too high
						while(tempIndexs[j]<12 || tempIndexs[j]>tempScales[j].getNotes().size())
							tempIndexs[j] = (int)((tempIndexs[j]-4)+(11*r));
						
						oldNotes[j] = newNotes[j];
						newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
						
						System.out.println("Note: " + newNotes[j]/12 + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
						
						//maybe dont do noteOff first?

						tracks[j].add(createNoteOnEvent(newNotes[j], onEvents[j].get(k) + c*16)); //c*16 = measure * cljcks per measure
						tracks[j].add(createNoteOffEvent(newNotes[j], offEvents[j].get(k) + c*16)); //turn off the last note thats playing, otherwise it will keep playing
						
						myScalesArr[j].addNote(newNotes[j]);
						for(int p=0; p<size; p++)
							if(p!=j) //not this agent
								myScalesArr[p].addSubNote(newNotes[j]); //add the new note for this agent to all the other agents sub notes
						
						//change this to a rythm heuristic
						i[j] = i[j] + 1 + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
						
					}
				}
			}
			
			//AScale tempScale;
			//AScale tempScale2;

			//size, myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			
			//for(i=1; i<32; i=i){ //for the next 32 clicks (a click is a time unit), i is incremented inside the foor loop
			
		}

		else if(sequenceType == "Final"){
			
			//Added by Jeff - each scale has a score, chooses the next note from the scale with the highest score		

			System.out.println("SIZE: " + size);
			
			AScale[] tempScales = new AScale[size];
			
			/* Track objects cannot be created by invoking their constructor
			   directly. Instead, the Sequence object does the job. So we
			   obtain the Track there. This links the Track to the Sequence
			   automatically.
			 */
			
			ArrayList<Integer> values = new ArrayList<Integer>();

			//number of ticks each note is held for
			//this is stored for when the note is actually played and
			//has no role in the heuristic
			// note - length pairs
			values.add(0, 15); //whole note
			values.add(1, 7); //half note
			values.add(2, 3); //quarter note
			values.add(3, 1); //eighth note
			values.add(4, 0); //sixteenth note
			values.add(5, 2); //dotted eighth note
			//RESTS
			values.add(6, 15); //whole rest
			values.add(7, 7); //half rest
			values.add(8, 3); //quarter rest
			values.add(9, 1); //eighth rest
			values.add(10, 0); //sixteenth rest
			
			int maxCount = 8; 
			
			//BeatStates states = new BeatStates();
			//determine initial feel
			Random random2 = new Random();
			//int feelChoice = random2.nextInt(4);
			int[] feelChoice = new int[size];
			feelChoice[0] = 0; //bass very slow
			feelChoice[1] = 4; //melody fast
			feelChoice[2] = 1; //drums slow
			feelChoice[3] = 1; //chords slow 
			feelChoice[4] = 1; //chords slow
			feelChoice[5] = 1; //chords slow
			//define feelChoices up here
			//= 5;
			/*
			 * 	case 0: VERY_SLOW; 
			 *	case 1: SLOW;
			 *	case 2: MODERATE;
			 *	case 3: FAST;
			 *	case 4: QUICK;
			 *	case 5: MIX;
			 */
			System.out.println("feel choice: " + feelChoice);
			BeatGenerator[] generators = new BeatGenerator[size];
			//= new BeatGenerator(feelChoice);
			
			ArrayList<Integer>[] onEvents = new ArrayList[size];
			ArrayList<Integer>[] offEvents = new ArrayList[size];
			int nextOnEvent;
			int nextOffEvent;
			
			//first generateBeats for one measure

			//initalize generators
			for(int h=0; h<size; h++){
				
				generators[h] = new BeatGenerator(feelChoice[h]);				
				
				//get the onEvents returned by the generator for that measure
				onEvents[h] = null;
				
				//get the offEvents returned by the generator for that measure
				offEvents[h] = new ArrayList<Integer>();
				
			}
			tracks[0].add(setSound(33,0)); //bass				
			tracks[1].add(setSound(73,1)); //lead (flute)
			tracks[2].add(setSound(118,2)); //drum
			tracks[3].add(setSound(1,3)); //piano
			tracks[4].add(setSound(1,4)); //piano
			tracks[5].add(setSound(1,5)); //piano
			
		/*	for(int q = size-3; q<size; q++){ //change the last 3 scales to be chords instead
				myScalesArr[q] = new Chords();
				tempIndexs[q] = (((127/12)*3)/2);  //this shouldbe the middle index, arbitrary again however;
			}
			*/
			
			double r = 0;
			int[] i = new int[size];
			for(int p=0; p<size; p++)//reset all i values
				i[p] = 0;
		

			for(int c = 0; c < maxCount; c++) { //for 8 measures
				for(int j = 0; j < size; j++){
									
					generators[j].generateBeats(); //first generate the new measure of beats
					if(j!=4 && j!=5){
						onEvents[j] = generators[j].getOnEvents(); //get the generated measure of on and off events
						offEvents[j] = generators[j].getOffEvents();
					}
					else{ 
						onEvents[j] = onEvents[3]; 
						offEvents[j] = onEvents[3];
					}
					
					for(int k = 0; k < onEvents[j].size(); k++) { //go through each element createNote events accordjng to values returned by BeatGenerator
						//track.add(createNoteOnEvent(67, onEvents[k].get(k) + c*16)); //c*16 = measure * cljcks per measure
						//track.add(createNoteOffEvent(67, offEvents[k].get(k) + c*16));

						System.out.println("get second highest count");
						tempScales[j] = myScalesArr[j].getSecondHighestCount();  //only real change between this and highest score
						
						System.out.println("Measure: " + c + " Agent: " + j + " Scale " + j + ":" + tempScales[j].getScaleKey() + " " + tempScales[j].getScaleType()); //debugging
		
						r = Math.random();
						//choose a note close to the last notes
						tempIndexs[j] = (int)((tempIndexs[j]-2)+(5*r));  //choose a index within 4 indexes of the last index, note the last index could be from a diffrent scale then the new scale
					
						if(j==0 )//bass
							while( tempIndexs[j]>(tempScales[j].getNotes().size()/2.0))
								tempIndexs[j] = (int)((tempIndexs[j]-3));
						if(j==1 )//lead
							while( tempIndexs[j]<(tempScales[j].getNotes().size()/2.0))
								tempIndexs[j] = (int)((tempIndexs[j]+3));

							
						//make sure its not too low or too high
						while(tempIndexs[j]<12 || tempIndexs[j]>tempScales[j].getNotes().size()-10)
							tempIndexs[j] = (int)((tempIndexs[j]-5)+(11*r));
						
						oldNotes[j] = newNotes[j];
						newNotes[j] = ((Integer)(tempScales[j].getNotes().get(tempIndexs[j]))).intValue();
						
						System.out.println("Note: " + newNotes[j]/12 + " Index:" + tempIndexs[j]+ " Time on:" + i[j]); //debugging
						
						//maybe dont do noteOff first?
				
						tracks[j].add(createNoteOnEvent(newNotes[j], onEvents[j].get(k) + c*16, j)); //c*16 = measure * cljcks per measure
						tracks[j].add(createNoteOffEvent(newNotes[j], offEvents[j].get(k) + c*16, j)); //turn off the last note thats playing, otherwise it will keep playing
						
						
						if(j<size-2){
							myScalesArr[j].addNote(newNotes[j]);
							for(int p=0; p<size; p++) //dont add chord notes to sub notes or drum notes
								if(p!=j) //not this agent
									myScalesArr[p].addSubNote(newNotes[j]); //add the new note for this agent to all the other agents sub notes
						}
						//change this to a rythm heuristic
						i[j] = i[j] + 1 + ((int)(Math.random()*3) ); //increment i a random amount between 1 and 4, this means each note will not have the same length
						
					}
				}
			}
			
			//AScale tempScale;
			//AScale tempScale2;

			//size, myScales,newNote,oldNote and tempIndex are global for the class so that we keep track of the notest we've played after we've played them in the player
			
			//for(i=1; i<32; i=i){ //for the next 32 clicks (a click is a time unit), i is incremented inside the foor loop
			
		}

		
		
		else if(sequenceType == "Multiple Agents")
		{
			ModManager mm = new ModManager(sequence);
			ArrayList test = new ArrayList();

			for(int i=0; i<1; i++)
			{
				track =mm.runModal();
				test.add(track);
				mm.changeTime();
				for (int j = 0; j<2;j++)
				{
					track = mm.runRepeat();
					test.add(track);
				}
				for (int j = 0; j<3;j++)
				{
					track = mm.runPerm();
					test.add(track);
				}
				for (int j = 0; j<3;j++)
				{

					track = mm.runPattern();
				}
		/*		for (int j = 0; j<3;j++)
				{
					track = mm.runRepeat();
					test.add(track);
					mm.changeTime();
					track = mm.runPerm();
					test.add(track);
					track = mm.runPerm();
					test.add(track);
					mm.changeTime();
				}*/
			}
			System.out.println("");
		}
		else if(sequenceType == "JonMoid")
		{
			ModManager mm = new ModManager(sequence);
			ArrayList test = new ArrayList();

			track = mm.runLongModal();
			mm.runAgent(); // doesn't override for whatever reason
			mm.runAgent();
			System.out.println("");
		}
		else if(sequenceType == "JonMoid2")
		{
			ModManager mm = new ModManager(sequence);
			ArrayList test = new ArrayList();

			track = mm.runLongModal2();
			mm.runAgent2(); // doesn't override for whatever reason
			mm.runAgent2();
			System.out.println("");
		}
		else if(sequenceType == "Trance")
		{
			ModManager mm = new ModManager(sequence);
			ArrayList test = new ArrayList();

			for(int i=0; i<1; i++)
			{
				track =mm.runModal();
				test.add(track);
				mm.changeTime(); // true
				for (int j = 0; j<3;j++)
				{
					track = mm.runRepeat();
				}
				for (int j = 0; j<3;j++)
				{
					System.out.println("");
					System.out.print("New Set");
					track = mm.runRepeat();
					mm.changeTime(); // false
					mm.runAgent2();
					mm.runAgent2();
					mm.changeTime(); //true
				}
				for (int j = 0; j<3;j++)
				{
					System.out.println("");
					System.out.print("New Set");
					track = mm.runPerm();
					mm.changeTime(); //false
					mm.runAgent2();
					mm.runAgent2();
					mm.changeTime(); //true
				}
				for (int j = 0; j<3;j++)
				{
					System.out.println("");
					System.out.print("New Set");
					track = mm.runPattern();
					mm.changeTime(); //false
					mm.runAgent2();
					mm.runAgent2();
					mm.changeTime(); //true
				}
					System.out.println("");
			}
		}
		
		mySequence = sequence;
	}
	
	
	private static MidiEvent setSound(int instrument,int channel){
		
		/*
		 *  foo: 0 Instrument Grand Piano (bank 0 program 0)
	foo: 1 Instrument Bright Piano (bank 0 program 1)
	foo: 2 Instrument Electric Grand (bank 0 program 2)
	foo: 3 Instrument Honky-Tonk Piano (bank 0 program 3)
	foo: 4 Instrument E. Piano1 (bank 0 program 4)
	foo: 5 Instrument E. Piano2 (bank 0 program 5)
	foo: 6 Instrument Harpsichord (bank 0 program 6)
	foo: 7 Instrument Clavinet (bank 0 program 7)
	foo: 8 Instrument Celesta (bank 0 program 8)
	foo: 9 Instrument Glockenspiel (bank 0 program 9)
	foo: 10 Instrument Music Box (bank 0 program 10)
	foo: 11 Instrument Vibraphone (bank 0 program 11)
	foo: 12 Instrument Marimba (bank 0 program 12)
	foo: 13 Instrument Xylophone (bank 0 program 13)
	foo: 14 Instrument Tubular Bell (bank 0 program 14)
	foo: 15 Instrument Dulcimer (bank 0 program 15)
	foo: 16 Instrument Organ1 (bank 0 program 16)
	foo: 17 Instrument Organ2 (bank 0 program 17)
	foo: 18 Instrument Organ3 (bank 0 program 18)
	foo: 19 Instrument Church Organ (bank 0 program 19)
	foo: 20 Instrument Reed Organ (bank 0 program 20)
	foo: 21 Instrument Accordian (bank 0 program 21)
	foo: 22 Instrument Harmonica (bank 0 program 22)
	foo: 23 Instrument Bandoneon (bank 0 program 23)
	foo: 24 Instrument Nylon-Str Gtr (bank 0 program 24)
	foo: 25 Instrument Steel-Str Gtr (bank 0 program 25)
	foo: 26 Instrument Jazz Gtr (bank 0 program 26)
	foo: 27 Instrument Clean Gtr (bank 0 program 27)
	foo: 28 Instrument Muted Gtr (bank 0 program 28)
	foo: 29 Instrument Overdrive Gtr (bank 0 program 29)
	foo: 30 Instrument Distortion Gtr (bank 0 program 30)
	foo: 31 Instrument Gtr. Harmonics (bank 0 program 31)
	foo: 32 Instrument Acoustic Bass (bank 0 program 32)
	foo: 33 Instrument Fingered Bass (bank 0 program 33)
	foo: 34 Instrument Picked Bass (bank 0 program 34)
	foo: 35 Instrument Fretless (bank 0 program 35)
	foo: 36 Instrument Slap Bass1 (bank 0 program 36)
	foo: 37 Instrument Slap Bass2 (bank 0 program 37)
	foo: 38 Instrument Syn.Bass1 (bank 0 program 38)
	foo: 39 Instrument Syn.Bass2 (bank 0 program 39)
	foo: 40 Instrument Violin (bank 0 program 40)
	foo: 41 Instrument Viola (bank 0 program 41)
	foo: 42 Instrument Cello (bank 0 program 42)
	foo: 43 Instrument Contrabass (bank 0 program 43)
	foo: 44 Instrument Tremolo (bank 0 program 44)
	foo: 45 Instrument Pizzicato (bank 0 program 45)
	foo: 46 Instrument Harp (bank 0 program 46)
	foo: 47 Instrument Timpani (bank 0 program 47)
	foo: 48 Instrument String Ensemble (bank 0 program 48)
	foo: 49 Instrument Slow Strings (bank 0 program 49)
	foo: 50 Instrument Synth Strings1 (bank 0 program 50)
	foo: 51 Instrument Synth Strings2 (bank 0 program 51)
	foo: 52 Instrument Choir (bank 0 program 52)
	foo: 53 Instrument Voice (bank 0 program 53)
	foo: 54 Instrument SynVox (bank 0 program 54)
	foo: 55 Instrument Orchestra Hit (bank 0 program 55)
	foo: 56 Instrument Trumpet (bank 0 program 56)
	foo: 57 Instrument Trombone (bank 0 program 57)
	foo: 58 Instrument Tuba (bank 0 program 58)
	foo: 59 Instrument Muted Trumpet (bank 0 program 59)
	foo: 60 Instrument French Horn (bank 0 program 60)
	foo: 61 Instrument Brass Ensemble (bank 0 program 61)
	foo: 62 Instrument Synth Brass1 (bank 0 program 62)
	foo: 63 Instrument Synth Brass2 (bank 0 program 63)
	foo: 64 Instrument Soprano Sax (bank 0 program 64)
	foo: 65 Instrument Alto Sax (bank 0 program 65)
	foo: 66 Instrument Tenor Sax (bank 0 program 66)
	foo: 67 Instrument Baritone Sax (bank 0 program 67)
	foo: 68 Instrument Oboe (bank 0 program 68)
	foo: 69 Instrument English Horn (bank 0 program 69)
	foo: 70 Instrument Bassoon (bank 0 program 70)
	foo: 71 Instrument Clarinet (bank 0 program 71)
	foo: 72 Instrument Piccolo (bank 0 program 72)
	foo: 73 Instrument Flute (bank 0 program 73)
	foo: 74 Instrument Recorder (bank 0 program 74)
	foo: 75 Instrument Pan Pipes (bank 0 program 75)
	foo: 76 Instrument Bottle Blow (bank 0 program 76)
	foo: 77 Instrument Shakuhachi (bank 0 program 77)
	foo: 78 Instrument Whistle (bank 0 program 78)
	foo: 79 Instrument Ocarina (bank 0 program 79)
	foo: 80 Instrument Square Wave (bank 0 program 80)
	foo: 81 Instrument Sawtooth Wave (bank 0 program 81)
	foo: 82 Instrument Syn.Calliope (bank 0 program 82)
	foo: 83 Instrument Chiffer Lead (bank 0 program 83)
	foo: 84 Instrument Charang (bank 0 program 84)
	foo: 85 Instrument Solo Vox (bank 0 program 85)
	foo: 86 Instrument 5ths Saw Wave (bank 0 program 86)
	foo: 87 Instrument Bass & Lead (bank 0 program 87)
	foo: 88 Instrument Fantasia (bank 0 program 88)
	foo: 89 Instrument Warm Pad (bank 0 program 89)
	foo: 90 Instrument PolySynth (bank 0 program 90)
	foo: 91 Instrument Space Vox (bank 0 program 91)
	foo: 92 Instrument Bowed Glass (bank 0 program 92)
	foo: 93 Instrument Metal Pad (bank 0 program 93)
	foo: 94 Instrument Halo Pad (bank 0 program 94)
	foo: 95 Instrument Sweep Pad (bank 0 program 95)
	foo: 96 Instrument Ice Rain (bank 0 program 96)
	foo: 97 Instrument Soundtrack (bank 0 program 97)
	foo: 98 Instrument Crystal (bank 0 program 98)
	foo: 99 Instrument Atmosphere (bank 0 program 99)
	foo: 100 Instrument Brightness (bank 0 program 100)
	foo: 101 Instrument Goblin (bank 0 program 101)
	foo: 102 Instrument Echo Drops (bank 0 program 102)
	foo: 103 Instrument Star Theme (bank 0 program 103)
	foo: 104 Instrument Sitar (bank 0 program 104)
	foo: 105 Instrument Banjo (bank 0 program 105)
	foo: 106 Instrument Shamisen (bank 0 program 106)
	foo: 107 Instrument Koto (bank 0 program 107)
	foo: 108 Instrument Kalimba (bank 0 program 108)
	foo: 109 Instrument Bagpipes (bank 0 program 109)
	foo: 110 Instrument Fiddle (bank 0 program 110)
	foo: 111 Instrument Shanai (bank 0 program 111)
	foo: 112 Instrument Tinker Bell (bank 0 program 112)
	foo: 113 Instrument Agogo (bank 0 program 113)
	foo: 114 Instrument Steel Drum (bank 0 program 114)
	foo: 115 Instrument Woodblock (bank 0 program 115)
	foo: 116 Instrument Taiko (bank 0 program 116)
	foo: 117 Instrument Melodic Toms (bank 0 program 117)
	foo: 118 Instrument Syn. Drums (bank 0 program 118)
	foo: 119 Instrument Reverse Cymbal (bank 0 program 119)
	foo: 120 Instrument Fret Noise (bank 0 program 120)
	foo: 121 Instrument Breath Noise (bank 0 program 121)
	foo: 122 Instrument Seashore (bank 0 program 122)
	foo: 123 Instrument Birdsong (bank 0 program 123)
	foo: 124 Instrument Telephone (bank 0 program 124)
	foo: 125 Instrument Helicopter (bank 0 program 125)
	foo: 126 Instrument Applause (bank 0 program 126)
	foo: 127 Instrument Gunshot (bank 0 program 127)
	foo: 128 Instrument high-Q (bank 1 program 27)
	foo: 129 Instrument slap (bank 1 program 28)
	foo: 130 Instrument scratch push (bank 1 program 29)
	foo: 131 Instrument scratch pull (bank 1 program 30)
	foo: 132 Instrument sticks (bank 1 program 31)
	foo: 133 Instrument square click (bank 1 program 32)
	foo: 134 Instrument metronome click (bank 1 program 33)
	foo: 135 Instrument metronome bell (bank 1 program 34)
	foo: 136 Instrument kick2 (bank 1 program 35)
	foo: 137 Instrument kick1 (bank 1 program 36)
	foo: 138 Instrument side stick (bank 1 program 37)
	foo: 139 Instrument snare1 (bank 1 program 38)
	foo: 140 Instrument handclap (bank 1 program 39)
	foo: 141 Instrument snare2 (bank 1 program 40)
	foo: 142 Instrument lo tom2 (bank 1 program 41)
	foo: 143 Instrument close hihat (bank 1 program 42)
	foo: 144 Instrument lo tom1 (bank 1 program 43)
	foo: 145 Instrument pedal hihat (bank 1 program 44)
	foo: 146 Instrument mid tom2 (bank 1 program 45)
	foo: 147 Instrument open hihat (bank 1 program 46)
	foo: 148 Instrument mid tom1 (bank 1 program 47)
	foo: 149 Instrument hi tom2 (bank 1 program 48)
	foo: 150 Instrument crash cymbal1 (bank 1 program 49)
	foo: 151 Instrument hi tom1 (bank 1 program 50)
	foo: 152 Instrument ride cymbal1 (bank 1 program 51)
	foo: 153 Instrument chinese cymbal (bank 1 program 52)
	foo: 154 Instrument ride bell (bank 1 program 53)
	foo: 155 Instrument tambourine (bank 1 program 54)
	foo: 156 Instrument splash cymbal (bank 1 program 55)
	foo: 157 Instrument cowbell (bank 1 program 56)
	foo: 158 Instrument crash cymbal2 (bank 1 program 57)
	foo: 159 Instrument vibraslap (bank 1 program 58)
	foo: 160 Instrument ride cymbal2 (bank 1 program 59)
	foo: 161 Instrument hi bongo (bank 1 program 60)
	foo: 162 Instrument lo bongo (bank 1 program 61)
	foo: 163 Instrument conga slap (bank 1 program 62)
	foo: 164 Instrument hi conga (bank 1 program 63)
	foo: 165 Instrument lo conga (bank 1 program 64)
	foo: 166 Instrument hi timbale (bank 1 program 65)
	foo: 167 Instrument lo timbale (bank 1 program 66)
	foo: 168 Instrument hi agogo (bank 1 program 67)
	foo: 169 Instrument lo agogo (bank 1 program 68)
	foo: 170 Instrument cabasa (bank 1 program 69)
	foo: 171 Instrument maracas (bank 1 program 70)
	foo: 172 Instrument hi whistle (bank 1 program 71)
	foo: 173 Instrument lo whistle (bank 1 program 72)
	foo: 174 Instrument short guiro (bank 1 program 73)
	foo: 175 Instrument long guiro (bank 1 program 74)
	foo: 176 Instrument claves (bank 1 program 75)
	foo: 177 Instrument hi woodblock (bank 1 program 76)
	foo: 178 Instrument lo woodblock (bank 1 program 77)
	foo: 179 Instrument hi quica (bank 1 program 78)
	foo: 180 Instrument lo quica (bank 1 program 79)
	foo: 181 Instrument mute triangle (bank 1 program 80)
	foo: 182 Instrument open triangle (bank 1 program 81)
	foo: 183 Instrument shaker (bank 1 program 82)
	foo: 184 Instrument jingle bells (bank 1 program 83)
	foo: 185 Instrument bell tree (bank 1 program 84)
	foo: 186 Instrument castanets (bank 1 program 85)
	foo: 187 Instrument mute surdo (bank 1 program 86)
	foo: 188 Instrument open surdo (bank 1 program 87)
		 */
		
		
		ShortMessage sm = new ShortMessage( );
		
		try{
			// set the instrument on channel 0
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		
		return new MidiEvent(sm, channel);
	}

	private static MidiEvent createNoteOnEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_ON,
							   nKey,
							   VELOCITY,
							   lTick);
	}
	
	private static MidiEvent createNoteOnEvent(int nKey, long lTick, int channel)
	{
		return createNoteEvent(ShortMessage.NOTE_ON,
							   nKey,
							   VELOCITY,
							   lTick,
							   channel);
	}

	private static MidiEvent createNoteOffEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_OFF,
							   nKey,
							   0,
							   lTick);
	}
	
	private static MidiEvent createNoteOffEvent(int nKey, long lTick, int channel)
	{
		return createNoteEvent(ShortMessage.NOTE_OFF,
							   nKey,
							   0,
							   lTick,
							   channel);
	}
	
	private static MidiEvent createNoteEvent(int nCommand,
											 int nKey,
											 int nVelocity,
											 long lTick)
	{
		ShortMessage	message = new ShortMessage();
		try
		{
			message.setMessage(nCommand,
							   0, // always on channel 1
							   nKey,
							   nVelocity);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		MidiEvent event = new MidiEvent(message,
										  lTick);
		return event;
	}
	
	private static MidiEvent createNoteEvent(int nCommand,
			 int nKey,
			 int nVelocity,
			 long lTick,
			 int channel)
		{
			ShortMessage	message = new ShortMessage();
			try
			{
					message.setMessage(nCommand,
								channel,	// not always on channel 1
								nKey,
								nVelocity);
			}
			catch (InvalidMidiDataException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			MidiEvent event = new MidiEvent(message,
					lTick);
			return event;
		}
		

	private static void printUsageAndExit()
	{
			out("usage:");
			out("java CreateSequence <midifile>");
			System.exit(1);
	}

	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}
	
}



/*** CreateSequence.java ***/
