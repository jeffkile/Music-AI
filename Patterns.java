import java.util.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;

public class Patterns
{
	private static List patScale;
	public static List scale;
	public static final int	VELOCITY = 64;
	public static List musicNotePattern = new ArrayList();
	public static List musicTimePattern = new ArrayList();
	public static int time=0;
	public Sequence mySequence;

	public Patterns()
	{
		patScale = new ArrayList();
		scale = new ArrayList();
	}
	public Patterns(Sequence seq)
	{
		patScale = new ArrayList();
		scale = new ArrayList();
		mySequence = seq;
	}
	public int getTimer()
	{
		return time;
	}
	public void changeScale(List test)
	{
		this.scale = test;
	}
	public Track getPermTrack(List notePat, List timePat)
	{
			Track track = mySequence.createTrack();
			this.musicTimePattern = timePat;
			this.musicNotePattern = notePat;
			musicNotePattern = this.getPermutation(musicNotePattern);

			System.out.println("");
			int note = 0;

			for(int i = 0; i < musicNotePattern.size(); i++)
			{
				note = (Integer)musicNotePattern.get(i);
				time = (Integer)musicTimePattern.get(i);
				track.add(createNoteOnEvent(note,time));
				track.add(createNoteOffEvent(note,time+2));
				System.out.print(note+ " ");
			}
			System.out.print(" New Permutation");
			return track;
	}
	public Track getPermTrack(List notePat, List timePat, int t)
	{
			Track track = mySequence.createTrack();
			this.musicTimePattern = timePat;
			this.musicNotePattern = notePat;
			musicNotePattern = this.getPermutation(musicNotePattern);
			System.out.println("");
			int note = 0;

			for(int i = 0; i < musicNotePattern.size(); i++)
			{
				note = (Integer)musicNotePattern.get(i);
				time = t+ (Integer)musicTimePattern.get(i);
				track.add(createNoteOnEvent(note,time));
				track.add(createNoteOffEvent(note,time+2));
				System.out.print(note + " ");
			}
			System.out.print(" New Permutation");
			return track;
	}
	public Track getPatTrack(List notePat, List timePat)
	{

		this.musicTimePattern = timePat;
		this.musicNotePattern = notePat;
		System.out.println("");
		Track track = mySequence.createTrack();
		musicNotePattern = this.getPattern(scale);

		int note = 0;

		for(int i = 0; i < musicNotePattern.size(); i++)
		{
			note = (Integer)musicNotePattern.get(i);
			time = (Integer)musicTimePattern.get(i);
			track.add(createNoteOnEvent(note,time));
			track.add(createNoteOffEvent(note,time+2));
			System.out.print(note + " ");
		}
		System.out.println(" New Pattern");
		return track;
	}
	public Track getPatTrack(List notePat, List timePat, int t)
	{
		this.musicTimePattern = timePat;
		this.musicNotePattern = notePat;
		Track track = mySequence.createTrack();
		System.out.println("");
		musicNotePattern = this.getPattern(scale);

		int note = 0;


		for(int i = 0; i < musicNotePattern.size(); i++)
		{
			note = (Integer)musicNotePattern.get(i);
			time = t +(Integer)musicTimePattern.get(i);
			track.add(createNoteOnEvent(note,time));
			track.add(createNoteOffEvent(note,time+2));
			System.out.print(note + " ");
		}
		System.out.print(" New Pattern");
		return track;
	}
	public List getPermutation(List pat)
	{
		//patScale = pat;
		int note = 0;
		int random = (int)(Math.random() * pat.size());
		int random2;
		for(int i = 0; i <=random; i++)
		{
			random2 = (int)(Math.random() * pat.size());
			note = (Integer)pat.remove(random2);
			random2 = (int)(Math.random() * pat.size());
			pat.add(random2,new Integer(note));
		}
		return pat;
	}
	public Track runRepeat(List baseNPat, List baseTPat, int t)
	{
		musicTimePattern.clear();
		Track track = mySequence.createTrack();
		int note = 0;
		System.out.println("");

		for(int i = 0; i < baseNPat.size(); i++)
		{
			note = (Integer)baseNPat.get(i);
			time = t+(Integer)baseTPat.get(i);
			musicTimePattern.add(time);
			track.add(createNoteOnEvent(note,time));
			track.add(createNoteOffEvent(note,time+2));
			System.out.print(note + " ");
		}
		System.out.print(" RunRepeat");
		time = time +2;
		musicNotePattern = baseNPat;
	//	musicTimePattern = baseTPat;
		return track;
	}
	public List getPattern(List modeScale)
	{
		patScale.clear();
		int note = 0;
		scale = modeScale;
		int size = musicNotePattern.size();
		for(int i = 0; i < size; i++)
		{
			note = (Integer)musicNotePattern.get(i);
			note = probNote(note);
			note = modifyNote(note);
			patScale.add(new Integer(note));
		}

		return patScale;
	}
	public static int probNote(int init)
	{
		int r = 0;
		int p = 0;
		int q = 0;
		int direction = 0;


		direction = (int)(Math.random() * 2);

		if(direction ==1) //reverse direction
		{
			if(init -12 > 0)
				init = init * -1;
		}
		else
		{
			if(init + 12> 127)
			{
				direction = 1;
				init = init * -1;
			}
		}

		r = (int)(Math.random() * 9);
		if(r > 6) //80 probability within scale
		{
			if(r == 7)
			{
				init = probNote(init+12);
			}
			else
			{
				init = probNote(init-12);
			}
		}
		if(r <= 6)
		{
			p = (int)(Math.random() * 3);
			if(p == 2) // 25 probability of 6,7
			{
				p = (int)(Math.random() * 3);
				if(p ==2)
				{
					q =(int)(Math.random()*3 + 9);

					init = init+ q;
				}

			}
			else // 75 probability is 0, 2, 3,4, 5 interval
			{
				q = (int)(Math.random() * 3);
				if( q >=1)
				{
					q = (int)(Math.random() * 7 + 2);
					init = init + q;
				}
				else
					init = init;
			}

		}
		if (init < 0)
			init = init * -1;

		return init;
	}

	public static int modifyNote(int note)
	{
		double p =0;

		if(!scale.contains(new Integer(note)))
		{
			p = Math.random();
			if(p%2*10 > 4 && note <=127)
			{
				note = note+1;
			}
			else
			{
				note = note-1;
			}
			note = modifyNote(note);
		}
		return note;

	}
	public List getNotes()
	{
		return musicNotePattern;
	}
	public List getTime()
	{
		return musicTimePattern;
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