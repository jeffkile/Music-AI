import java.util.ArrayList;
import java.util.List;

import java.util.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;

public class Harmonize
{
	public static final int	VELOCITY = 64;
	public static List scale = new ArrayList();
	public static List neighbors = new ArrayList();

	public static List driver = new ArrayList();
	public static List musicNotePattern = new ArrayList();
	public static List musicTimePattern = new ArrayList();
	int time;
	public Sequence mySequence;
	int prevNote;

	public Harmonize(Sequence seq)
	{
		time = 0;//initial time to start
		mySequence = seq;
		prevNote = (int)(0+(127*Math.random()));
	}
	public Track getInitTrack(List notePat, List sc, int t)
	{
		time = t;
		scale = sc;

		Track track = mySequence.createTrack();
		prevNote = modifyNote(prevNote);
		neighbors.add(prevNote);
		track.add(createNoteOnEvent(prevNote,time));
		track.add(createNoteOffEvent(prevNote,time+2));
		System.out.print(prevNote + " ");
	//	time = time+( 1+(int)(Math.random()*4) );
		time = time+2;
		return track;
	}
	public void resetPattern()
	{
		musicNotePattern.clear();
		musicTimePattern.clear();
	}
	public List getNotes()
	{
		return musicNotePattern;
	}
	public List getTime()
	{
		return musicTimePattern;
	}
	public void resetNote()
	{
		prevNote = (int)(0+(127*Math.random()));
	}


	public Track getTrack(List notePat, List sc, int t, int note, int driver ,List pass)
	{
		int interval;
		int nextNote = 999;
		int index;
		int anotherNote;
		int r, p;
		time = t;
		scale = sc;
		prevNote = note;
		neighbors.add(driver);
		for(int k =0; k<pass.size();k++)
		{
			anotherNote = (Integer)pass.get(k);
			neighbors.add(anotherNote);
		}
		Track track = mySequence.createTrack();

		for(int i =0; i<neighbors.size();i++)
		{
			interval = prevNote - (Integer)(neighbors.get(i));
			if (interval <0)
			{
				interval = interval * -1;
			}
			if(nextNote > interval)
			{
				nextNote = i;
			}
		}

		index = nextNote;

		if(prevNote == (Integer)neighbors.get(index))
		{
			nextNote = (Integer)neighbors.get(nextNote)-2;
			if(nextNote < 0)
				nextNote = 0;
	/*		r = (int)(Math.random() *2);
			p = (int)(Math.random() *13);
			if(r == 0)
			{
				nextNote = (Integer)neighbors.get(nextNote) - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
				nextNote = (Integer)neighbors.get(nextNote) + p;
				if(nextNote>127)
					nextNote = 127;*/
		}



		nextNote = (int)((Integer)neighbors.get(index) + prevNote)/2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(nextNote))
		{
		//	p = 1+(int)(Math.random() *12);
			nextNote = nextNote - 2;
				if(nextNote < 0)
					nextNote = 0;
	/*		r = (int)(Math.random() *2);
			p = 1+(int)(Math.random() *12);

			if(r == 0)
			{
				nextNote = nextNote - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + p;
				if(nextNote>127)
					nextNote = 127;
			}*/
		}
		//driver
		nextNote = (int)(nextNote + driver) /2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(new Integer(nextNote)))
		{
			nextNote = nextNote - 2;
			if(nextNote < 0)
					nextNote = 0;

	/*		r = (int)(Math.random() *2);
			p = 1+(int)(Math.random() *12);
			if(r == 0)
			{
				nextNote = nextNote - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + p;
				if(nextNote>127)
					nextNote = 127;
			}*/

		}

		int blah = prevNote -nextNote;
		while(blah <=3 && blah >= -3)
		{
			nextNote = nextNote -2;
			if(nextNote <= 3)
				nextNote = 0;
			blah = prevNote -nextNote;
		//	System.out.println(prevNote + " " + nextNote);
	/*		r = (int)Math.random() *2;
			nextNote = nextNote - 2;
			if(nextNote < 0)
					nextNote = 0;
			if(r == 0)
			{
				nextNote = nextNote - 2;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + 2;
				if(nextNote>127)
					nextNote = 127;
			}*/
		}
	//	nextNote = modifyNote(nextNote);
/*		//driver
		nextNote = (int)(nextNote + driver) /2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(new Integer(nextNote)))
		{
			int temp2 = nextNote;
			int temp = nextNote - driver;
			nextNote = nextNote + (temp *-1);
			if (temp2 == nextNote)
			{
				break;
			}
			if(nextNote< 0)
			{
				nextNote= 0;
			}
			else if(nextNote > 127)
			{
				nextNote = 127;
			}
			System.out.println("Does it ever do this?");
		}*/
		track.add(createNoteOnEvent(nextNote,time));
		track.add(createNoteOffEvent(nextNote,time+2));
	//	time = time+( 1+(int)(Math.random()*4) );
		time = time+2;
		System.out.print(nextNote + " ");
		musicNotePattern.add(nextNote);
		musicTimePattern.add(time);
		prevNote = nextNote;
		neighbors.clear();
		return track;
	}
	public Track getTrack2(List notePat, List sc, int t, int note, int driver ,List pass)
	{
		int interval;
		int nextNote = 999;
		int index;
		int anotherNote;
		int r, p;
		time = t;
		scale = sc;
		prevNote = note;
		neighbors.add(driver);
		for(int k =0; k<pass.size();k++)
		{
			anotherNote = (Integer)pass.get(k);
			neighbors.add(anotherNote);
		}
		Track track = mySequence.createTrack();

		for(int i =0; i<neighbors.size();i++)
		{
			interval = prevNote - (Integer)(neighbors.get(i));
			if (interval <0)
			{
				interval = interval * -1;
			}
			if(nextNote > interval)
			{
				nextNote = i;
			}
		}

		index = nextNote;

		if(prevNote == (Integer)neighbors.get(index))
		{
			nextNote = (Integer)neighbors.get(nextNote)-2;
			if(nextNote < 0)
				nextNote = 0;
	/*		r = (int)(Math.random() *2);
			p = (int)(Math.random() *13);
			if(r == 0)
			{
				nextNote = (Integer)neighbors.get(nextNote) - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
				nextNote = (Integer)neighbors.get(nextNote) + p;
				if(nextNote>127)
					nextNote = 127;*/
		}



		nextNote = (int)((Integer)neighbors.get(index) + prevNote)/2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(nextNote))
		{
		//	p = 1+(int)(Math.random() *12);
			nextNote = nextNote - 2;
				if(nextNote < 0)
					nextNote = 0;
	/*		r = (int)(Math.random() *2);
			p = 1+(int)(Math.random() *12);

			if(r == 0)
			{
				nextNote = nextNote - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + p;
				if(nextNote>127)
					nextNote = 127;
			}*/
		}
		//driver
		nextNote = (int)(nextNote + driver) /2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(new Integer(nextNote)))
		{
			nextNote = nextNote - 2;
			if(nextNote < 0)
					nextNote = 0;

	/*		r = (int)(Math.random() *2);
			p = 1+(int)(Math.random() *12);
			if(r == 0)
			{
				nextNote = nextNote - p;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + p;
				if(nextNote>127)
					nextNote = 127;
			}*/

		}

		int blah = prevNote -nextNote;
		while(blah <=3 && blah >= -3)
		{
			nextNote = nextNote -2;
			if(nextNote <=3)
			{
				nextNote = 0;
				break;
			}
			blah = prevNote -nextNote;
		//	System.out.println(prevNote + " " + nextNote);
	/*		r = (int)Math.random() *2;
			nextNote = nextNote - 2;
			if(nextNote < 0)
					nextNote = 0;
			if(r == 0)
			{
				nextNote = nextNote - 2;
				if(nextNote < 0)
					nextNote = 0;
			}
			else
			{
				nextNote = nextNote + 2;
				if(nextNote>127)
					nextNote = 127;
			}*/
		}
	//	nextNote = modifyNote(nextNote);
/*		//driver
		nextNote = (int)(nextNote + driver) /2;
		nextNote = modifyNote(nextNote);
		while(neighbors.contains(new Integer(nextNote)))
		{
			int temp2 = nextNote;
			int temp = nextNote - driver;
			nextNote = nextNote + (temp *-1);
			if (temp2 == nextNote)
			{
				break;
			}
			if(nextNote< 0)
			{
				nextNote= 0;
			}
			else if(nextNote > 127)
			{
				nextNote = 127;
			}
			System.out.println("Does it ever do this?");
		}*/
		track.add(createNoteOnEvent(nextNote,time));
		track.add(createNoteOffEvent(nextNote,time+2));
		time = time+( 1+(int)(Math.random()*4) );
		System.out.print(nextNote + " ");
		musicNotePattern.add(nextNote);
		musicTimePattern.add(time);
		prevNote = nextNote;
		neighbors.clear();
		return track;
	}
	public int getTimer()
	{
		return time;
	}
	public int getNote()
	{
		return prevNote;
	}
	public static int modifyNote(int note)
	{
		if(!scale.contains(new Integer(note)))
		{
			if(note<127)
			{
				note = note+1;
			}
			else if(note == 127)
			{
				note = note-1;
			}
			note = modifyNote(note);
		}
		return note;
	}
	private static MidiEvent createNoteOnEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_ON,
							   nKey,
							   VELOCITY,
							   lTick);
	}

	private static MidiEvent createNoteOffEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_OFF,
							   nKey,
							   0,
							   lTick);
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
							   0,	// always on channel 1
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
}