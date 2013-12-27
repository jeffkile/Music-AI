import java.util.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;


public class ModManager
{
	public Track track;
	public static List scale = new ArrayList();
	public static List musicNotePattern = new ArrayList();
	public static List musicTimePattern = new ArrayList();
	public static List baseNotePattern = new ArrayList();
	public static List baseTimePattern = new ArrayList();
	public static List basePermPattern = new ArrayList();
	public static List basePermTimePattern = new ArrayList();
	public static List basePatPattern = new ArrayList();
	public static List basePatTimePattern = new ArrayList();

	public static List harm = new ArrayList();
	public static List harmArray = new ArrayList();

	public static Sequence mySequence;
	Modal mode;
	Patterns trial;
	Harmonize hh;
	public int time = 0;
	int time2;
	boolean flag, flag2;
	int counter =0;

	public ModManager(Sequence sequence)
	{
		mySequence = sequence;
		mode = new Modal(sequence);
		trial = new Patterns(sequence);
		hh = new Harmonize(sequence);
		flag = false;
		flag2 = false;
		time2 =0;
	}
	public void changeTime()
	{
		flag = !flag;

	}
	public Track runLongModal()
	{
		track = mode.getLongTrack();
		musicNotePattern = mode.getNotes();
		musicTimePattern = mode.getTime();
		if(flag2 == false)
		{
			baseNotePattern = musicNotePattern;
			baseTimePattern = musicTimePattern;
			flag2 =true;
		}
		scale = mode.getScale();
		trial.changeScale(scale);
		time = mode.getTimer()+2;
		System.out.print(" RunLongModal");
		return track;
	}
	public Track runLongModal2()
	{
		track = mode.getLongTrack2();
		musicNotePattern = mode.getNotes();
		musicTimePattern = mode.getTime();
		if(flag2 == false)
		{
			baseNotePattern = musicNotePattern;
			baseTimePattern = musicTimePattern;
			flag2 =true;
		}
		scale = mode.getScale();
		trial.changeScale(scale);
		time = mode.getTimer()+2;
		System.out.print(" RunLongModal2");
		return track;
	}
	public void runAgent()
	{
		hh.resetPattern();
		System.out.println("");
		int t = 0;
		int p = 0;
		int p2;
		ArrayList test = new ArrayList();
		ArrayList pass = new ArrayList();
		int drive;

		if(flag == true)
		{
			t = time;
		}
		if(flag == false)
		{
			t = time2;
		}
		track = hh.getInitTrack(baseNotePattern,scale,t);
		t = hh.getTimer();
		p = hh.getNote();
		harm.add(p);
		for(int i =1; i < baseNotePattern.size(); i++)
		{
			drive = (Integer)baseNotePattern.get(i);

			for(int j=0;j<harmArray.size();j++)
			{

				test = (ArrayList)harmArray.get(j);
				p2 = (Integer)test.get(i);
				pass.add(p2);
			}
			track = hh.getTrack(baseNotePattern,scale,t,p,drive, pass);
			t = hh.getTimer();
			p = hh.getNote();
			harm.add(p);
			pass.clear();
		}
		System.out.print(" RunAgent");
		harmArray.add(harm);
	}

	public void runAgent2()
	{
		hh.resetPattern();
		System.out.println("");
		int t = 0;
		int p = 0;
		int p2;
		ArrayList test = new ArrayList();
		ArrayList pass = new ArrayList();
		int drive;

		if(flag == true)
		{
			t = time;
		}
		if(flag == false)
		{
			t = time2;
		}
		track = hh.getInitTrack(baseNotePattern,scale,t);
		t = hh.getTimer();
		p = hh.getNote();
		harm.add(p);
		for(int i =1; i < baseNotePattern.size(); i++)
		{
			drive = (Integer)baseNotePattern.get(i);

			for(int j=0;j<harmArray.size();j++)
			{

				test = (ArrayList)harmArray.get(j);
				p2 = (Integer)test.get(i);
				pass.add(p2);
			}
			track = hh.getTrack2(baseNotePattern,scale,t,p,drive, pass);
			t = hh.getTimer();
			p = hh.getNote();
			harm.add(p);
			pass.clear();
		}
		System.out.print(" RunAgent");
		harmArray.add(harm);
	}
	public void runAgent3()
	{
		hh.resetPattern();
		System.out.println("");
		int t = 0;
		int p = 0;
		int p2;
		ArrayList test = new ArrayList();
		ArrayList pass = new ArrayList();
		int drive;

		if(flag == true)
		{
			t = time;
		}
		if(flag == false)
		{
			t = time2;
		}
		track = hh.getInitTrack(basePermPattern,scale,t);
		t = hh.getTimer();
		p = hh.getNote();
		harm.add(p);
		for(int i =1; i < basePermPattern.size(); i++)
		{
			drive = (Integer)basePermPattern.get(i);

			for(int j=0;j<harmArray.size();j++)
			{

				test = (ArrayList)harmArray.get(j);
				p2 = (Integer)test.get(i);
				pass.add(p2);
			}
			track = hh.getTrack(basePermPattern,scale,t,p,drive, pass);
			t = hh.getTimer();
			p = hh.getNote();
			harm.add(p);
			pass.clear();
		}
		System.out.print(" RunAgent");
		harmArray.add(harm);
	}
//	public ArrayList addAgent()
/*	public void repeatAgent()
	{
		System.out.println("AGENT REPEAT");
		int note;
		int temp = time;
		basePatPattern = hh.getNotes();
		basePatTimePattern = hh.getTime();
		Patterns trial2 = new Patterns(mySequence);
		track = trial2.runRepeat(basePatPattern, basePatTimePattern, temp);

		//if(flag != false)

		time = trial.getTimer() + 2;
		//time2 = time -temp;
		/*
		for(int i =0; i < basePatPattern.size(); i++)
		{
			note = (Integer)basePatPattern.get(i);
			track.add(createNoteOnEvent(note,temp));
			track.add(createNoteOffEvent(note,temp+2));
			temp= temp+2;
		}
	}*/

	public Track runModal()
	{

		track = mode.getTrack(time);

		musicNotePattern = mode.getNotes();
		musicTimePattern = mode.getTime();
		if(flag2 == false)
		{
			baseNotePattern = musicNotePattern;
			baseTimePattern = musicTimePattern;
			flag2 =true; //possible problem.
		}
		scale = mode.getScale();
		trial.changeScale(scale);
		time = mode.getTimer()+2;
		System.out.print(" RunModal");
		return track;
	}
	public Track runRepeat()
	{
		time2 = time;
		track = trial.runRepeat(baseNotePattern, baseTimePattern, time);
		musicNotePattern = trial.getNotes();
		time = trial.getTimer() + 2;
		return track;
	}
	public Track runPattern()
	{
		time2 = time;
		if(flag == false)
		{
			track = trial.getPatTrack(musicNotePattern, musicTimePattern);
		}
		else
		{
			track = trial.getPatTrack(musicNotePattern, musicTimePattern,  time);
		}
		basePatPattern = trial.getNotes();
		time = trial.getTimer()+4;
		return track;
	}
	public Track runPerm()
	{
		time2 = time;
		if(flag == false)
			track = trial.getPermTrack(musicNotePattern, musicTimePattern);
		else
			track = trial.getPermTrack(musicNotePattern, musicTimePattern,time);
		basePermPattern = trial.getNotes();
		time = trial.getTimer()+4;
		return track;
	}

}