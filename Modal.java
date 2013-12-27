
import java.util.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.InvalidMidiDataException;

public class Modal
{
	public static final int	VELOCITY = 64;
	public static List scale = new ArrayList();
	public static List musicNotePattern = new ArrayList();
	public static List musicTimePattern = new ArrayList();
	public static int time=0;
	public Sequence mySequence;
	int myNote = -1;

	public Modal()
	{
	}
	public Modal(Sequence seq)
	{
		mySequence = seq;
		time =0;
	}
	public List getNotes()
	{
		return musicNotePattern;
	}
	public List getTime()
	{
		return musicTimePattern;
	}
	public int getTimer()
	{
		return time;
	}
	public List getScale()
	{
		return scale;
	}
	public void resetTime()
	{
		time =0;
	}

	public Track getLongTrack()
	{
		musicNotePattern.clear();
		musicTimePattern.clear();
		Track track = mySequence.createTrack();
		double r = Math.random()*7;
		double p = Math.random();
	//	int init = (int)(0+(127*p));
		int init = (int)(55+(16*p));
		if(myNote < 0)
		{
			myNote = init;
			scale = this.getScale(init,(int)r);
		}
		init = myNote;
		System.out.println("Modal - "+ myNote);


		track.add(createNoteOnEvent(init,time));
		musicNotePattern.add((int) init);
		musicTimePattern.add((int) time);
		track.add(createNoteOffEvent(init,time+2));
		time = time+2;
		System.out.print(init + " ");

		while(time<256)
		{
			init = this.createMelody(init);
			track.add(createNoteOnEvent(init,time));
			musicNotePattern.add((int) init);
			musicTimePattern.add((int) time);
			track.add(createNoteOffEvent(init,time+2));
			time = time+2;
			System.out.print(init + " ");
		}
		return track;
	}
	public Track getLongTrack2()
	{
		musicNotePattern.clear();
		musicTimePattern.clear();
		Track track = mySequence.createTrack();
		double r = Math.random()*7;
		double p = Math.random();
	//	int init = (int)(0+(127*p));
		int init = (int)(55+(16*p));
		if(myNote < 0)
		{
			myNote = init;
			scale = this.getScale(init,(int)r);
		}
		init = myNote;
		System.out.println("Modal - "+ myNote);


		track.add(createNoteOnEvent(init,time));
		musicNotePattern.add((int) init);
		musicTimePattern.add((int) time);
		track.add(createNoteOffEvent(init,time+2));
		time = time+( 1+(int)(Math.random()*4) );
		System.out.print(init + " ");

		while(time<256)
		{
			init = this.createMelody(init);
			track.add(createNoteOnEvent(init,time));
			musicNotePattern.add((int) init);
			musicTimePattern.add((int) time);
			track.add(createNoteOffEvent(init,time+2));
			time = time+( 1+(int)(Math.random()*4) );
			System.out.print(init + " ");
		}
		return track;
	}
/*	public Track getTrack()
	{
		musicNotePattern.clear();
		musicTimePattern.clear();
		Track track = mySequence.createTrack();
		double r = Math.random()*7;
		double p = Math.random();
	//	int init = (int)(0+(127*p));
		int init = (int)(55+(16*p));
		if(myNote < 0)
		{
			myNote = init;
			scale = this.getScale(init,(int)r);
		}
		init = myNote;
		System.out.println("Modal - "+ myNote);


		track.add(createNoteOnEvent(init,time));
		musicNotePattern.add((int) init);
		musicTimePattern.add((int) time);
		track.add(createNoteOffEvent(init,time+2));
		//time = time+( 1+(int)(Math.random()*4) );
		time = time+2;
		System.out.println(init);

		while(time<16)
		{
			init = this.createMelody(init);
			track.add(createNoteOnEvent(init,time));
			musicNotePattern.add((int) init);
			musicTimePattern.add((int) time);
			track.add(createNoteOffEvent(init,time+2));
			//time = time+( 1+(int)(Math.random()*4) );
			time = time + 2;
			System.out.println(init);
		}
		return track;
	}*/
	public Track getTrack(int t)
	{
		musicNotePattern.clear();
		musicTimePattern.clear();
		Track track = mySequence.createTrack();
		double r = Math.random()*7;
		double p = Math.random();
	//	int init = (int)(0+(127*p));
		int init = (int)(55+(16*p));
		if(myNote < 0)
		{
			myNote = init;
			scale = this.getScale(init,(int)r);
		}
		init = myNote;
		System.out.println("Modal - "+ myNote);
		this.time = t;

		track.add(createNoteOnEvent(init,time));
		musicNotePattern.add((int) init);
		musicTimePattern.add((int) time);
		track.add(createNoteOffEvent(init,time+2));
		time = time+( 1+(int)(Math.random()*4));
	//	time = time+2;
	/*	if((int)Math.random()*2 == 0)
		{
			time = time + 1;
		}
		else
			time = time+( 1+(int)(Math.random()*4) );*/
		System.out.print(init + " ");
	//	time = time+2;
		int lame = time;
		while(time<16+lame)
		{
			init = this.createMelody(init);
			track.add(createNoteOnEvent(init,time));
			musicNotePattern.add((int) init);
			musicTimePattern.add((int) time);
			track.add(createNoteOffEvent(init,time+2));
			time = time+( 1+(int)(Math.random()*4) );
	//		time = time+2;
			System.out.print(init + " ");
		}
		return track;
	}
	public List getScale(int init, int mode)
	{

		if(mode ==0)
		{
			System.out.println("Ionian");
			genIonian(init);  //aka major
		}
		else if(mode ==1)
		{
			System.out.println("Dorian");
			genDorian(init);
		}
		else if(mode ==2)
		{
			System.out.println("Phrygian");
			genPhrygian(init);
		}
		else if(mode ==3)
		{
			System.out.println("Lydian");
			genLydian(init);
		}
		else if(mode ==4)
		{
			System.out.println("Mixolydian");
			genMixolydian(init);
		}
		else if(mode ==5)
		{
			System.out.println("Aeolian");
			genAeolian(init);  //aka minor
		}
		else if(mode ==6)
		{
			System.out.println("Locrian");
			genLocrian(init);
		}
		return scale;
	}

	public static void genLocrian(int init)
	{
		//H-W-W-H-W-W-W
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-2;
			if (temp >0)
			{
				g=temp;
				temp = temp -2;
				if(temp>0)
				{
					f=temp;
					temp = temp-2;
					if(temp>0)
					{
						e = temp;
						temp = temp -1;
						if(temp>0)
						{
							d = temp;
							temp = temp-2;
							if(temp>0)
							{
								c=temp;
								temp = temp-2;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//H-W-W-H-W-W-W
		if(temp <13)
		{
			temp = temp+1;
			if(temp<13)
			{
				b=temp;
				temp = temp+2;
				if(temp<13)
				{
					c =temp;
					temp = temp+2;
					if(temp<13)
					{
						d=temp;
						temp = temp +1;
						if(temp<13)
						{
							e= temp;
							temp = temp+2;
							if(temp<13)
							{
								f = temp;
								temp = temp+2;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}
		//H-W-W-H-W-W-W
		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;

		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
		}
		//H-W-W-H-W-W-W
		if(temp <= 127)
		{
			temp = temp+1;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+2;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+2;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +1;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+2;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+2;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}
	private static void genMixolydian(int init)
		{
			//W-W-H-W-W-H-W
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-2;
			if (temp >0)
			{
				g=temp;
				temp = temp -1;
				if(temp>0)
				{
					f=temp;
					temp = temp-2;
					if(temp>0)
					{
						e = temp;
						temp = temp -2;
						if(temp>0)
						{
							d = temp;
							temp = temp-1;
							if(temp>0)
							{
								c=temp;
								temp = temp-2;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//W-W-H-W-W-H-W
		if(temp <13)
		{
			temp = temp+2;
			if(temp<13)
			{
				b=temp;
				temp = temp+2;
				if(temp<13)
				{
					c =temp;
					temp = temp+1;
					if(temp<13)
					{
						d=temp;
						temp = temp +2;
						if(temp<13)
						{
							e= temp;
							temp = temp+2;
							if(temp<13)
							{
								f = temp;
								temp = temp+1;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}

		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;
//W-W-H-W-W-H-W
		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
		}
		//W-W-H-W-W-H-W
		if(temp <= 127)
		{
			temp = temp+2;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+2;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+1;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +2;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+2;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+1;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}
	public static void genLydian(int init)
	{
		//W-W-W-H-W-W-H
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-1;
			if (temp >0)
			{
				g=temp;
				temp = temp -2;
				if(temp>0)
				{
					f=temp;
					temp = temp-2;
					if(temp>0)
					{
						e = temp;
						temp = temp -1;
						if(temp>0)
						{
							d = temp;
							temp = temp-2;
							if(temp>0)
							{
								c=temp;
								temp = temp-2;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//W-W-W-H-W-W-H
		if(temp <13)
		{
			temp = temp+2;
			if(temp<13)
			{
				b=temp;
				temp = temp+2;
				if(temp<13)
				{
					c =temp;
					temp = temp+2;
					if(temp<13)
					{
						d=temp;
						temp = temp +1;
						if(temp<13)
						{
							e= temp;
							temp = temp+2;
							if(temp<13)
							{
								f = temp;
								temp = temp+2;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}

		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;
//W-W-W-H-W-W-H
		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
		}
		//W-W-W-H-W-W-H
		if(temp <= 127)
		{
			temp = temp+2;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+2;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+2;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +1;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+2;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+2;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}
	public static void genPhrygian(int init)
	{
		//H-W-W-W-H-W-W
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-2;
			if (temp >0)
			{
				g=temp;
				temp = temp -2;
				if(temp>0)
				{
					f=temp;
					temp = temp-1;
					if(temp>0)
					{
						e = temp;
						temp = temp -2;
						if(temp>0)
						{
							d = temp;
							temp = temp-2;
							if(temp>0)
							{
								c=temp;
								temp = temp-2;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//H-W-W-W-H-W-W
		if(temp <13)
		{
			temp = temp+1;
			if(temp<13)
			{
				b=temp;
				temp = temp+2;
				if(temp<13)
				{
					c =temp;
					temp = temp+2;
					if(temp<13)
					{
						d=temp;
						temp = temp +2;
						if(temp<13)
						{
							e= temp;
							temp = temp+1;
							if(temp<13)
							{
								f = temp;
								temp = temp+2;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}

		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;
//H-W-W-W-H-W-W
		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
		}
		//H-W-W-W-H-W-W
		if(temp <= 127)
		{
			temp = temp+1;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+2;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+2;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +2;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+1;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+2;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}
	public static void genDorian(int init)
	{

		//W-H-W-W-W-H-W
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-2;
			if (temp >0)
			{
				g=temp;
				temp = temp -1;
				if(temp>0)
				{
					f=temp;
					temp = temp-2;
					if(temp>0)
					{
						e = temp;
						temp = temp -2;
						if(temp>0)
						{
							d = temp;
							temp = temp-2;
							if(temp>0)
							{
								c=temp;
								temp = temp-1;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//W-H-W-W-W-H-W
		if(temp <13)
		{
			temp = temp+2;
			if(temp<13)
			{
				b=temp;
				temp = temp+1;
				if(temp<13)
				{
					c =temp;
					temp = temp+2;
					if(temp<13)
					{
						d=temp;
						temp = temp +2;
						if(temp<13)
						{
							e= temp;
							temp = temp+2;
							if(temp<13)
							{
								f = temp;
								temp = temp+1;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}
		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;
//W-H-W-W-W-H-W
		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
		}
		if(temp <= 127)
		{
			temp = temp+2;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+1;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+2;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +2;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+2;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+1;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}
	public static void genAeolian(int init)
	{

		//W - H - W - W - H - W - W
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-2;
			if (temp >0)
			{
				g=temp;
				temp = temp -2;
				if(temp>0)
				{
					f=temp;
					temp = temp-1;
					if(temp>0)
					{
						e = temp;
						temp = temp -2;
						if(temp>0)
						{
							d = temp;
							temp = temp-2;
							if(temp>0)
							{
								c=temp;
								temp = temp-1;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//W - H - W - W - H - W - W
		if(temp <13)
		{
			temp = temp+2;
			if(temp<13)
			{
				b=temp;
				temp = temp+1;
				if(temp<13)
				{
					c =temp;
					temp = temp+2;
					if(temp<13)
					{
						d=temp;
						temp = temp +2;
						if(temp<13)
						{
							e= temp;
							temp = temp+1;
							if(temp<13)
							{
								f = temp;
								temp = temp+2;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}
//W - H - W - W - H - W - W
		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;

		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
		}
		//W - H - W - W - H - W - W
		if(temp <= 127)
		{
			temp = temp+2;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+1;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+2;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +2;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+1;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+2;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}

	public static void genIonian(int init)
	{

		//W-W-H-W-W-W-H
		int a=0;
		int b=0;
		int c=0;
		int d=0;
		int e=0;
		int f=0;
		int g=0;
		int start = init;
		a = init%12;
		int temp = a;
		if(temp>0)
		{
			temp = temp-1;
			if (temp >0)
			{
				g=temp;
				temp = temp -2;
				if(temp>0)
				{
					f=temp;
					temp = temp-2;
					if(temp>0)
					{
						e = temp;
						temp = temp -2;
						if(temp>0)
						{
							d = temp;
							temp = temp-1;
							if(temp>0)
							{
								c=temp;
								temp = temp-2;
								if(temp>0)
								{
									b= temp;
								}
							}
						}
					}
				}

			}
		}
		temp = a;
		//W-W-H-W-W-W-H
		if(temp <13)
		{
			temp = temp+2;
			if(temp<13)
			{
				b=temp;
				temp = temp+2;
				if(temp<13)
				{
					c =temp;
					temp = temp+1;
					if(temp<13)
					{
						d=temp;
						temp = temp +2;
						if(temp<13)
						{
							e= temp;
							temp = temp+2;
							if(temp<13)
							{
								f = temp;
								temp = temp+2;
								if(temp<13)
									g = temp;
							}
						}
					}
				}
			}
		}
		scale.add(new Integer(a));
		if(b<a)
			scale.add(new Integer(b));
		if(c<a)
			scale.add(new Integer(c));
		if(d<a)
			scale.add(new Integer(d));
		if(e<a)
			scale.add(new Integer(e));
		if(f<a)
			scale.add(new Integer(f));
		if(g <a)
			scale.add(new Integer(g));

		temp = a;

		for(int i = temp; i<= 115;i= i+12)
		{

			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+2;
			scale.add(new Integer(temp));
			temp = temp+1;
			scale.add(new Integer(temp));
		}
		if(temp <= 127)
		{
			temp = temp+2;
			if(temp<=127)
			{
				scale.add(new Integer(temp));
				temp = temp+2;
				if(temp<=127)
				{
					scale.add(new Integer(temp));
					temp = temp+1;
					if(temp<=127)
					{
						scale.add(new Integer(temp));
						temp = temp +2;
						if(temp<=127)
						{
							scale.add(new Integer(temp));
							temp = temp+2;
							if(temp<=127)
							{
								scale.add(new Integer(temp));
								temp = temp+2;
								if(temp<=127)
									scale.add(new Integer(temp));
							}
						}
					}
				}
			}
		}
	}

	public int createMelody(int init)
	{

/*		double r = 0;
		double p =0;
		double q =0;

		r = Math.random();
		p = Math.random()*2;
		q = Math.random()*3;

		if(init > 55 && init < 79)
		{
			if(p == 1)
			{
				init = (int)((init)+(12*r));
			}
			else
			{
				init = (int)((init-12)+(12*r));
			}
		}
		else if (init <=55)
		{
			if((int)q == 3 && (init > 12))
			{
				init = (int)((init-12)+(12*r));
			}
			init = (int)((init)+(12*r));
		}
		else if (init >= 79)
		{
			if((int)q == 3 && (init < 115))
			{
				init = (int)((init)+(12*r));
			}
			init = (int)((init-12)+(12*r));
		}
*/
		init = probNote(init);
		init = modifyNote(init);
		return init;
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

		r = (int)(Math.random() * 3);
		if(r !=2)
		{
			r = (int)(Math.random()*3);
			if(r!=2)
			{
				init = init + 2;
			}
			else
				init = init;
		}
		else
		{
			r = (int)(Math.random() *6);
			if(r < 4)
			{
				q = (int)(Math.random() * 9);
				init = init + q;
			}
			else
			{
				p = (int)(Math.random() * 6);
				if(p < 4)
				{
					p = (int)(Math.random() * 3);
					if ( p ==2)
					{
						init = init + 10;
					}
					else
						init = init + 8;
				}
				else
				{
					q = (int)(Math.random() *2);
					if( q == 0)
					{
						init = probNote(init+12);
					}
					else
						init = probNote(init-12);
				}

			}
		}

/*
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
				q= (int)(Math.random() * 2);
				if(q == 0)
				{
					init = init + 2;
				}
				q = (int)(Math.random() * 9);
				init = init + q;
			}

		}*/
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